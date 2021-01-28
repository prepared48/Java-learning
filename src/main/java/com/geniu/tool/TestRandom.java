package com.geniu.tool;

import java.math.BigDecimal;

/**
 * 生成随机小数
 * 范围 Min 到 Max
 *
 * @Author: zhongshibo
 * @Date: 2020/11/12 22:07
 */
public class TestRandom {

    public static void main(String[] args) {
//        generateRandom(20, 60);
//        System.out.println(sum / 10.0);
        System.out.println(generateSixRandomNum());
    }

    /**
     * 生成6位随机整数
     *
     * @return
     */
    public static int generateSixRandomNum() {
        return (int) ((Math.random() * 9 + 1) * 100000);
    }

    /**
     * 生成范围内随机消暑
     *
     * @return
     */
    public static void generateRandom(float Max, float Min) {
        float sum = 0;
        for (int i = 0; i < 24; i++) {
            BigDecimal db = new BigDecimal(Math.random() * (Max - Min) + Min);
            System.out.print(db.setScale(2, BigDecimal.ROUND_HALF_UP)// 保留30位小数并四舍五入
                    .toString() + ",");
//            sum += Float.parseFloat(db.setScale(2, BigDecimal.ROUND_HALF_UP)// 保留30位小数并四舍五入
//                    .toString());
        }
    }
}
