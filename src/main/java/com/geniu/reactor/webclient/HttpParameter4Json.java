package com.geniu.reactor.webclient;

import com.geniu.utils.JsonUtil;
import lombok.Getter;
import lombok.ToString;

import java.util.StringJoiner;

/**
 * json请求参数.
 */
@Getter
@ToString
public class HttpParameter4Json extends HttpParameter {

    private final Object jsonBody;

    private HttpParameter4Json(final Object o) {
        super();
        this.jsonBody = o;
    }

    public static HttpParameter4Json create(final Object o) {
        return new HttpParameter4Json(o);
    }

    public Object getJsonBody() {
        return this.jsonBody;
    }

    public Class getBodyType() {
        return this.jsonBody.getClass();
    }

    @Override
    public String log(final String url) {
        final String tmpUrl = url == null ? "null" : url;
        final StringJoiner joiner = new StringJoiner(",");
        final String body = JsonUtil.toJson(this.jsonBody);
        return joiner.add("url:" + tmpUrl).add("body:" + body).toString();
    }
}
