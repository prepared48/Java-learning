package com.geniu.base;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Huawei1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Set<String> set = new TreeSet<>();
        while(sc.hasNext()) {
            int n = sc.nextInt();
            for(int i = 0; i < n; i++) {
                String str = sc.next();
                int num = str.length() / 8;
                String last = str.substring(num * 8);

                for(int j = 0; j < num; j++) {
                    set.add(str.substring(8 * j, 8 * (j + 1) ));
                }
                if(last.length() > 1) {
                    set.add(last.concat(getZero(last)));
                }
            }

            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
        }
    }

    public static String getZero(String last) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < (8 - last.length()); i++) {
            sb.append("0");
        }
        return sb.toString();
    }
}
