package com.activemq.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.activemq.jpa.model.AccessionNo;
import com.activemq.jpa.service.AccessionService;
import com.activemq.log.SpySqlLogUtil;

public class Test001 {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AccessionService accessionService = (AccessionService) context.getBean("accessionService");
		AccessionNo no = new AccessionNo();
		no.setAccessionNo("111111");
		no.setDelFlag("1");
		no.setSaved("1");
		no.setType("1");
		accessionService.save(no);
		String sql = SpySqlLogUtil.getPeriodSql();
		System.out.println(sql);
		Publisher pub = new Publisher();
		pub.sendMessage(sql);
	}
}
