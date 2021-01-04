package com.geniu.base.sort;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
public class YellowPersion implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("----YellowPersion----");
    }
}