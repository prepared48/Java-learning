package com.geniu.tool;

import java.math.BigDecimal;

/**
 * @Auther: shibo.zhong
 * @Date: 2021/12/30 14:35
 */
public class MoneyUtil {


    /**
     * 元转分，确保price保留两位有效数字
     *
     * @return
     */
    public static int changeY2F(String amount) {
        BigDecimal bigDecimal = new BigDecimal(amount).setScale(2);
        return bigDecimal.multiply(new BigDecimal(100)).intValue();
    }

    /**
     * 分转元，转换为bigDecimal在toString
     *
     * @return
     */
    public static String changeF2Y(int price) {
        return BigDecimal.valueOf(Long.valueOf(price)).divide(new BigDecimal(100)).toString();
    }

}
