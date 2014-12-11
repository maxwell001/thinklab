package com.activemq.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test002 {
	
	public static void main(String[] args) {
//		Consumer c = new Consumer();
//		c.getMessage();
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("ready...");
	}
}
