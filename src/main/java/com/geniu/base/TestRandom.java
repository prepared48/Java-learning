package com.geniu.base;

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
        float Max = 2, Min = 0;
        float sum = 0;
        for (int i = 0; i < 24; i++) {
            BigDecimal db = new BigDecimal(Math.random() * (Max - Min) + Min);
            System.out.print(db.setScale(2, BigDecimal.ROUND_HALF_UP)// 保留30位小数并四舍五入
                    .toString() + ",");
//            sum += Float.parseFloat(db.setScale(2, BigDecimal.ROUND_HALF_UP)// 保留30位小数并四舍五入
//                    .toString());
        }
//        System.out.println(sum / 10.0);
    }
}
