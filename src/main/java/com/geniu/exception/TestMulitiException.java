package com.geniu.exception;

import com.geniu.base.operation.DefinedException;

/**
 * @Author: zhongshibo
 * @Date: 2021/6/23 17:22
 */
public class TestMulitiException {

    public static void main(String[] args) {
        TestMulitiException mulitiException = new TestMulitiException();
        mulitiException.test1();
    }

    /**
     * {@link #test2()}
     *
     * @throws DefinedException
     */
    public void test1() throws DefinedException {
        test2();
    }

    public void test2() {
        test3();
        throw new DefinedException("test2 exception");
    }

    public void test3() {
        try {
            System.out.println(1 / 0);
        } catch (DefinedException e) {
            e.printStackTrace();
        }

    }
}
