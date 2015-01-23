package com.lumanmed.activemq.core.impl;

import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;

import com.lumanmed.activemq.core.IJmsInvoker;
import com.lumanmed.activemq.core.ISubscriberService;

public class ConsumerLinster implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try{
			if(message!=null){
				Map<String,Object> map = (Map<String, Object>) message;
			}
			System.out.println("recive message:"+message);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
