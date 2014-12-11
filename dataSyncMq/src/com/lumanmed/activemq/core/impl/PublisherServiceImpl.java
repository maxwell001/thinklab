package com.lumanmed.activemq.core.impl;

import javax.jms.Destination;
import org.springframework.jms.core.JmsTemplate;
import com.lumanmed.activemq.core.IPublisherService;

public class PublisherServiceImpl implements IPublisherService{

	private JmsTemplate jmsTemplate;
	private Destination destination;
	
	@Override
	public void sendMessage(String str) {
		try{
			jmsTemplate.convertAndSend(destination, str);
			System.out.println("message send...");
		}catch(Exception e){
			e.printStackTrace();
		} 
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

}
