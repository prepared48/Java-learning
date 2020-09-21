package com.geniu.base.clone;

/**
 * @Author: zhongshibo
 * @Date: 2020/9/22 07:22
 */
public class TestDeepCopy {

	public static void main(String[] args) {
		// 原始对象
		DeepStudent stud = new DeepStudent("杨充", "潇湘剑雨");
		System.out.println("原始对象: " + stud.getName() + " - " + stud.getSubj().getName());
		// 浅拷贝对象
		DeepStudent clonedStud = (DeepStudent) stud.clone();
		System.out.println("拷贝对象: " + clonedStud.getName() + " - " + clonedStud.getSubj().getName());
		// 原始对象和拷贝对象是否一样：
		System.out.println("原始对象和拷贝对象是否一样: " + (stud == clonedStud));
		// 原始对象和拷贝对象的name属性是否一样
		System.out.println("原始对象和拷贝对象的name属性是否一样: " + (stud.getName() == clonedStud.getName()));
		// 原始对象和拷贝对象的subj属性是否一样
		System.out.println("原始对象和拷贝对象的subj属性是否一样: " + (stud.getSubj() == clonedStud.getSubj()));
		stud.setName("小杨逗比");
		stud.getSubj().setName("潇湘剑雨大侠");
		System.out.println("更新后的原始对象: " + stud.getName() + " - " + stud.getSubj().getName());
		System.out.println("更新原始对象后的克隆对象: " + clonedStud.getName() + " - " + clonedStud.getSubj().getName());
	}
}
