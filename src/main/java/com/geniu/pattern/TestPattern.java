package com.geniu.pattern;

import java.util.Arrays;

/**
 * @Author: zhongshibo
 * @Date: 2023/3/15 10:45
 */
public class TestPattern {

	private static String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";

	public static void main(String[] args) {
//		String pattern = ATOM + "+(\\." + ATOM + "+)*";
//		System.out.println("pattern = " + pattern);
//		Pattern compile = Pattern.compile(pattern, 2);
//		Matcher matcher = compile.matcher("636363qqqqqqqqqqqqqqqqqqqqqqqkkkkkkkkkkkqqwwwwwwwwrrrrrrettttttt");
//		boolean matches = matcher.matches();
//		System.out.println("matches = " + matches);

//		String lat = "39.052911";
//		String lon = "115.907651";
//		DecimalFormat df = new DecimalFormat("#.00000");
//		lat = df.format(NumberUtils.toDouble(lat));
//		lon = df.format(NumberUtils.toDouble(lon));
//		String key = lat + "_" + lon + "_location_get";
//		System.out.println(key);

		String value = "JD";
		String[] split = value.split(",");
		System.out.println(Arrays.asList(split));

	}
}
