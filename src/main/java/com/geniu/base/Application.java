package com.geniu.base;

public class Application {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        b.call(a);
        a.log();
    }

}

