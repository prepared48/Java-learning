package com.geniu.base.string;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @Author: zhongshibo
 * @Date: 2023/5/15 21:57
 */
public class Test1 {
	public static void main(String[] args) {
		String html4 = StringEscapeUtils.escapeHtml4("秦伟");
		System.out.println(html4);
	}
}
