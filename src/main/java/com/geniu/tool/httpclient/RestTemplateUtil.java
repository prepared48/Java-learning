package com.geniu.tool.httpclient;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: prepared
 * @Date: 2022/1/7 15:09
 */
public class RestTemplateUtil {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://open.douyin.com/passport/open/client_token";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        System.out.println(response.getBody());
        System.out.println("response = " + response);
    }

    public String get(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        System.out.println(response.getBody());
        System.out.println("response = " + response);
        return response.getBody();
    }


}
