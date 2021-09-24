package com.geniu.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Huawei2 {

    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String VERTIAL = "|";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // 列数
        int colCount = 0;

        // 行数
        int rowCount = 0;
        List<String[]> orignalData = new ArrayList<>();
        while (sc.hasNext()) {
            String next = sc.next();
            if("exit".equals(next)) {
                break;
            }
            String[] strArray = next.trim().split(",");
            orignalData.add(strArray);
            int colNum = strArray.length;

            if (colNum > colCount) {
                colCount = colNum;
            }
            rowCount++;
        }
        System.out.println("rowCount= " + rowCount + ";  colCount = " + colCount);
        String[][] data = new String[rowCount][colCount];
        // 求列最大宽度
        int[] maxColWidth = new int[colCount];
        maxColWidth = new int[]{4,2,2};
        for (int i = 0; i < rowCount; i++) {
            for(int j = 0; j < colCount; j++) {
                data[i][j] = orignalData.get(i)[j];
                if(data[i][j].length() > maxColWidth[j]) {
                    maxColWidth[j] = data[i][j].length();
                }
            }
        }
        printExcel(rowCount, colCount, maxColWidth, data);

    }


    public static String getStr(String signal, int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(signal);
        }
        return sb.toString();
    }

    public static void printExcel(int rowCount, int colCount, int[] maxColWidth, String[][] data) {
        // 打印表格
        for (int i = 0; i < 2 * rowCount + 1; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 2 * colCount + 1; j++) {
                if (i % 2 == 0 && j % 2 == 1) {
                    sb.append(PLUS.concat(getStr(MINUS, maxColWidth[(j - 1) / 2])));
                } else if (i % 2 == 1 && j % 2 == 1) {
                    if(data[(i - 1) / 2][(j - 1) / 2].length() < maxColWidth[ (j - 1) / 2]) {
                        sb.append(VERTIAL.concat(getStr(" ", (maxColWidth[ (j - 1) / 2] - data[(i - 1) / 2][(j - 1) / 2].length())))
                                .concat(data[(i - 1) / 2][(j - 1) / 2]));
                    }else {
                        sb.append(VERTIAL.concat(data[(i - 1) / 2][(j - 1) / 2]));
                    }
                }
            }
            if (i % 2 == 0) {
                sb.append(PLUS);
            } else {
                sb.append(VERTIAL);
            }
            System.out.println(sb.toString());
        }
    }
}
