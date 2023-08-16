package com.geniu.reactor.webclient;

import com.geniu.utils.JsonUtil;
import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.resources.LoopResources;
import reactor.netty.tcp.SslProvider;
import reactor.netty.tcp.TcpClient;

import java.net.URI;
import java.time.Duration;
import java.util.function.Function;

/**
 * @Author: prepared
 * @Date: 2023/8/15 11:05
 */
@Slf4j
public class CustomerWebClient {

	public static final CustomerWebClient instance = new CustomerWebClient();

	/**
	 * 限制并发数 100
	 */
	Scheduler customScheduler = Schedulers.newParallel("CustomScheduler", 100);


	private final WebClient webClient;

	private CustomerWebClient() {

		final SslContextBuilder sslBuilder = SslContextBuilder.forClient()
				.trustManager(InsecureTrustManagerFactory.INSTANCE);

		final SslProvider ssl = SslProvider.builder().sslContext(sslBuilder)
				.defaultConfiguration(SslProvider.DefaultConfigurationType.TCP).build();

		final int cpuCores = Runtime.getRuntime().availableProcessors();
		final int selectorCount = Math.max(cpuCores / 2, 4);
		final int workerCount = Math.max(cpuCores * 2, 8);
		final LoopResources pool = LoopResources.create("HCofSWC", selectorCount, workerCount, true);

		final Function<? super TcpClient, ? extends TcpClient> tcpMapper = tcp -> tcp
				.option(ChannelOption.TCP_NODELAY, true)
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
				.option(ChannelOption.SO_TIMEOUT, 10000)
				.secure(ssl)
				.runOn(pool);

		ConnectionProvider.Builder httpClientOfSWC = ConnectionProvider
				.builder("HttpClientOfSWC")
				.maxConnections(100_000)
				.pendingAcquireTimeout(Duration.ofSeconds(6));
		final ConnectionProvider connectionProvider = httpClientOfSWC.build();

		final HttpClient hc = HttpClient.create(connectionProvider)
				.tcpConfiguration(tcpMapper);

		final Function<HttpClient, HttpClient> hcMapper = rhc -> rhc
				.compress(true);

		final WebClient.Builder wcb = WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(hcMapper.apply(hc)));
//				.filter(new TraceRequestFilter()); 可以通过Filter 增加trace追踪

		this.webClient = wcb.build();
	}

	public <T> Mono<T> get(String url, Class<T> clazz, T defaultClass) {
		long start = System.currentTimeMillis();
		return webClient.get()
				.uri(url)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatus::isError, response -> Mono.error(new RuntimeException("Request failed with status code: " + response.statusCode())))
				.bodyToMono(clazz)
				.doOnSuccess(response-> {
					log.info("get.success, url={}, response={}, param={}", url, response);
				})
				.doOnError(error-> {
					log.info("get.param.error, url={}", url, error);
				})
				.onErrorReturn(defaultClass)
				.doFinally(res-> {
				})
				.publishOn(customScheduler);
	}

	public <T> Mono<T> getParam(String url, MultiValueMap<String, String> param, Class<T> clazz, T defaultClass) {
		long start = System.currentTimeMillis();
		URI uri = UriComponentsBuilder.fromUriString(url)
				.queryParams(param)
				.build()
				.toUri();

		return webClient.get()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(clazz)
				.doOnSuccess(response-> {
					log.info("get.param.success, url={}, response={}, param={}", url, response, JsonUtil.toJson(param));
				})
				.doOnError(error-> {
					log.error("get.param.error, url={}, param={}", url, JsonUtil.toJson(param), error);
				})
				.onErrorReturn(defaultClass)
				.doFinally(res-> {
				})
				.publishOn(customScheduler);
	}



	public <T> Mono<T> postJson(String url, final HttpParameter4Json parameter, Class<T> clazz, T defaultClass) {
		final long start = System.currentTimeMillis();
		return webClient.post()
				.uri(url)
				.contentType(MediaType.APPLICATION_JSON)
				.cookies(cookies -> cookies.setAll(parameter.getCookies()))
				.body(Mono.just(parameter.getJsonBody()), ParameterizedTypeReference.forType(parameter.getBodyType()))
				.headers(headers -> headers.setAll(parameter.getHeaders()))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(clazz)
				.doOnSuccess(response-> {
					log.info("post.json.success, url={}, response={}, param={}", url, response, parameter.getJsonBody());
				})
				.doOnError(error-> {
					log.error("get.param.error, url={}, param={}", url, parameter.getJsonBody(), error);
				})
				.onErrorReturn(defaultClass)
				.doFinally(res-> {
				})
				.publishOn(customScheduler);

	}


	public <T> Mono<T> postFormData(String url, HttpParameter parameter, Class<T> clazz, T defaultClass) {
		final long start = System.currentTimeMillis();
		return webClient.post()
				.uri(url)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.cookies(cookies -> cookies.setAll(parameter.getCookies()))
				.body(BodyInserters.fromFormData(parameter.getMultiValueMapParam()))
				.headers(headers -> headers.setAll(parameter.getMapHeaders()))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(clazz)
				.doOnSuccess(response-> {
					log.info("post.fromData.success, url={}, response={}, param={}", url, response, JsonUtil.toJson(parameter));
				})
				.doOnError(error-> {
					log.info("get.param.error, url={}, param={}", url, JsonUtil.toJson(parameter), error);
				})
				.onErrorReturn(defaultClass)
				.doFinally(res-> {
				})
				.publishOn(customScheduler);
	}

}