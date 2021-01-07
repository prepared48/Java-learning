package com.geniu.concurrent.threadlocal;

import java.text.SimpleDateFormat;

/**
 * 测试使用线程安全的SimpleDateFormat和不安全的SimpleDateFormat 转换日期的例子
 *
 * @author shibo_zhong
 * @date 2021/01/07
 */
public class SimpleDateFormatTest {

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = "2016-12-30 15:35:34";
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        System.out.println(Thread.currentThread().getName() + "\t"
                                + TimeUtil.parse(dateTime, TimeUtil.YEAR_MONTH_DAY_SECOND));
//                        try {
//                            System.out.println(Thread.currentThread().getName() + "\t"
//                                    + dateFormat.parse(dateTime));
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
            }).start();
        }
    }
}