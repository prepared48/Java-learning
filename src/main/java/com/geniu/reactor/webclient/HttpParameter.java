package com.geniu.reactor.webclient;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.Map;

/**
 * User: liuxiaowei Date: create in 19:18 2019-07-30
 */
@Getter
@ToString
public class HttpParameter {

    private final Map<String, String> params = Maps.newLinkedHashMap();
    protected final Map<String, String> headers = Maps.newLinkedHashMap();

    private String log;
    /**
     * cookies.
     */
    protected final Map<String, String> cookies = Maps.newLinkedHashMap();
    /**
     * template形式的变量.
     */
    protected final Map<String, Object> uriVariables = Maps.newHashMap();

    protected HttpParameter() {

    }

    public static HttpParameter create() {
        return new HttpParameter();
    }

    public HttpParameter add(final String key, final String value) {
        this.params.put(key, value);
        return this;
    }

    public HttpParameter addAll(final Map<String, String> params) {
        this.params.putAll(params);
        return this;
    }

    public HttpParameter addCookie(final String key, final String value) {
        this.cookies.put(key, value);
        return this;
    }

    public HttpParameter addAllCookies(final Map<String, String> cookies) {
        this.cookies.putAll(cookies);
        return this;
    }

    public Map<String, String> getCookies() {
        return this.cookies;
    }


    public HttpParameter addHeader(final String key, final String value) {
        this.headers.put(key, value);
        return this;
    }

    public HttpParameter addUriVariable(final String name, final Object value) {
        this.uriVariables.put(name, value);
        return this;
    }

    public MultiValueMap<String, String> getMultiValueMapParam() {
        final MultiValueMap<String, String> multiMap = new LinkedMultiValueMap<>();
        if (MapUtils.isEmpty(this.params)) {
            return multiMap;
        }
        multiMap.setAll(this.params);
        return multiMap;
    }

    public Map<String, String> getMapHeaders() {
        return this.headers;
    }

    public MultiValueMap<String, String> getMultiValueMapHeaders() {
        if (MapUtils.isEmpty(this.headers)) {
            return new LinkedMultiValueMap<>();
        }

        final MultiValueMap<String, String> multiMap = new LinkedMultiValueMap(this.headers);
        return multiMap;
    }

    public URI toURI(final String uri) throws Exception {
        // template格式中替换{}内容
        this.uriVariables.forEach((k, v) -> uri.replace(k, v.toString()));
        final URIBuilder uriBuilder = new URIBuilder(uri);
        this.params.entrySet().forEach(
                e -> uriBuilder.addParameter(e.getKey(), e.getValue()));

        final URI tmpUri = uriBuilder.build();
        this.log = tmpUri.toString();
        return tmpUri;
    }


    public String log(final String url) {
        if (StringUtils.isNotEmpty(this.log)) {
            return this.log;
        }

        final String tmpUrl = url == null ? "null" : url;
        final StringBuilder queryStrBuilder = new StringBuilder();
        this.params.forEach((key, value) -> {
            if (queryStrBuilder.length() != 0) {
                queryStrBuilder.append("&").append(key).append("=").append(value);
            } else {
                queryStrBuilder.append(key).append("=").append(value);
            }
        });

        if (queryStrBuilder.length() == 0) {
            return url;
        }

        if (tmpUrl.contains("?")) {
            return tmpUrl + "&" + queryStrBuilder.toString();
        } else {
            return tmpUrl + "?" + queryStrBuilder.toString();
        }
    }


}
