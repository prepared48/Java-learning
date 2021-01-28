package com.geniu.base.string;

import java.util.Arrays;

/**
 * substring 在jdk1.6中是使用修改offset实现的，引用的还是原来的字符串，如果很长的话，会造成内存泄漏
 * 在jdk1.7中，解决了这个bug。jdk1.7中，使用新建的字符串实现该功能。
 *
 * @Author: zhongshibo
 * @Date: 2020/10/29 11:18
 */
public class TestSubString {

	private char value[];

	//JDK 7
	class String {
		private char value[];

		public String(char value[], int offset, int count) {
			//check boundary
			this.value = Arrays.copyOfRange(value, offset, offset + count);
		}
	}


	public String substring(int beginIndex, int endIndex) {
		//check boundary
		int subLen = endIndex - beginIndex;
		return new String(value, beginIndex, subLen);
	}

}
