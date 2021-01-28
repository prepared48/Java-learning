package com.geniu.base.sources;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringSourcesApplication {

	public static void main(String[] args) {
		// debug bean加载过程
		ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("A.xml");//加载单个配置文件

	}

}
