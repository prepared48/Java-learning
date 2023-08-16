package com.geniu.reactor.webclient;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HttpReturn {
    private int httpCode;
    private String body;
    private Throwable t;

    public boolean isOk() {
        return t == null && (httpCode == 200 || httpCode == 204 || httpCode == 304);
    }

    public String getErrMsg() {
        if (t != null) {
            return t.getMessage();
        } else {
            return "ERROR";
        }
    }
}
