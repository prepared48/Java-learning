package com.geniu.base.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @Auther: shibo.zhong
 * @Date: 2021/12/30 17:50
 */
public class TestLastDay {

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        // 下一年的当月的最后一天0点
        LocalDate expireDate1 = now.plusYears(1).with(TemporalAdjusters.lastDayOfMonth()).toLocalDate();
        LocalDateTime expireDateTime1 = LocalDateTime.of(expireDate1, LocalTime.MAX);
        // 下一年的最后一天24点
        LocalDate expireDate2 = now.plusYears(1).with(TemporalAdjusters.lastDayOfYear()).toLocalDate();
        LocalDateTime expireDateTime2 = LocalDateTime.of(expireDate2, LocalTime.MAX);
        System.out.println(expireDateTime1);
        System.out.println(expireDateTime2);
    }
}
