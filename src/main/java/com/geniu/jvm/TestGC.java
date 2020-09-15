package com.geniu.jvm;

import java.util.ArrayList;

/**
 * @Author: zhongshibo
 * @Date: 2020/9/15 17:39
 */
public class TestGC {

	public static void main(String[] args) {
		ArrayList<OOMBean> arrayList = new ArrayList<>();
		while (true) {
			arrayList.add(new OOMBean());
		}
	}
}
