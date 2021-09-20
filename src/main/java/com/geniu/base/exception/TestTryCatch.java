package com.geniu.base.exception;

public class TestTryCatch {

    public static void main(String[] args) {
        System.out.println(test());
    }

    public static int test() {
        int i = 0;
        try {
            i = 1;
        }catch (Exception e) {

        }finally {
            i++;
        }
        return i;
    }
}
