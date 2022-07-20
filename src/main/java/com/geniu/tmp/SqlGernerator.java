package com.geniu.tmp;

/**
 * @Auther: shibo.zhong
 * @Date: 2022/2/9 15:23
 */
public class SqlGernerator {

    public static void main(String[] args) {
        String tableStr = "CREATE TABLE `user_pay_way_#TABLEINDEX`( `id` BIGINT(11) NOT NULL AUTO_INCREMENT, `uid` BIGINT(11) NOT NULL, `pay_way` VARCHAR(20) DEFAULT NULL COMMENT '支付方式', `tied_card` TINYINT(4) DEFAULT '0' COMMENT '绑卡信息', `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, `create_time` TIMESTAMP NOT NULL DEFAULT '2022-02-08 00:00:00', PRIMARY KEY (`id`), UNIQUE KEY `uid_UNIQUE` (`uid`)) ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=UTF8MB4; ";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 1000; i++) {
            sb.append(tableStr.replace("#TABLEINDEX", String.valueOf(i))).append("\n");
        }
        System.out.println(sb);
    }
}
