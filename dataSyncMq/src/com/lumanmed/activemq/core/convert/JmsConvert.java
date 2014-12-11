package com.lumanmed.activemq.core.convert;

import java.util.HashMap;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.springframework.jms.support.converter.MessageConverter;

public class JmsConvert implements MessageConverter{

	@Override
	public Object fromMessage(Message message) throws JMSException {
		Map<String,Object> map = new HashMap<String, Object>();
		if(message instanceof TextMessage){
			TextMessage tm = (TextMessage)message;
			String sql = tm.getStringProperty("sql");
			map.put("sql", sql);
		}
		return map;
	}

	@Override
	public Message toMessage(Object obj, Session session) throws JMSException {
		Message message = null;
		if(obj instanceof String){
			message = session.createTextMessage();
			message.setStringProperty("sql", obj.toString());
		}
		System.out.println("message:"+message);
		return message;
	}
	
	
}
