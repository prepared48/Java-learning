package com.geniu.base;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Set<String> sort = new HashSet<>();
        while(sc.hasNext()) {
            String str = (String)sc.next();
            for(int i = 0; i < str.length(); i++) {
                sort.add(String.valueOf(str.charAt(i)));
            }
            System.out.println(sort.size());
        }
    }
}