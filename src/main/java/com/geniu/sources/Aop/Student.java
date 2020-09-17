package com.geniu.sources.Aop;

/**
 *
 */
public class Student implements IPerson {

	private String name;

	public Student(String name) {
		this.name = name;
	}

	public void giveTask() {
		System.out.println(name + "交语文作业");
	}
}