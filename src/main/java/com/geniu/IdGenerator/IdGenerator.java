package com.geniu.IdGenerator;

/**
 * @Author: zhongshibo
 * @Date: 2021/1/11 14:01
 */
public interface IdGenerator {

    String generate() throws IdGenerationFailureException;
}
