package com.geniu.SPEL;

import lombok.Data;

import java.util.Date;

/**
 * @Author: zhongshibo
 * @Date: 2022/7/20 15:39
 */
@Data
public class Inventor {

	private String name;

	private Date date;

	private String value;

	public Inventor(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public Inventor(String name, Date date, String value) {
		this.name = name;
		this.date = date;
		this.value = value;
	}
}
