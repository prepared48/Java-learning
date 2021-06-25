package com.geniu.base.operation;

/**
 * @Author: zhongshibo
 * @Date: 2021/6/23 17:23
 */
public class DefinedException extends RuntimeException {

    private String errorCode;

    public String getErrorCode() {
        return this.errorCode;
    }

    public DefinedException(String errorCode) {
        this.errorCode = errorCode;
    }

    public DefinedException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public DefinedException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public DefinedException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public DefinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}
