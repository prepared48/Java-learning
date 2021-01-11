package com.geniu.IdGenerator;

/**
 * 自定义异常
 *
 * @Author: zhongshibo
 * @Date: 2021/1/11 14:00
 */
public class IdGenerationFailureException extends Exception {

    public IdGenerationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
