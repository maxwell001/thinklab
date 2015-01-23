package com.lumanmed.activemq.core.impl;

import java.util.Map;

import com.lumanmed.activemq.core.IJmsInvoker;
import com.lumanmed.activemq.core.ISubscriberService;

public class SubscriberServiceImpl implements ISubscriberService{

	private IJmsInvoker jmsInvoker;
	
	@Override
	public void reciveMessage(Object message) {
		try{
			if(message!=null){
				Map<String,Object> map = (Map<String, Object>) message;
				//处理接受到的消息
//				consumeMessage(map);
			}
			System.out.println("recive message:"+message);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void consumeMessage(Map<String, Object> message) throws Exception {
		if(jmsInvoker==null){
			throw new Exception("property jmsInvoker is null,you must set the value.");
		}
		if(message!=null && message.size()>0){
			if(message.get("sql")!=null){
				String sql = message.get("sql").toString();
				//执行sql语句
				jmsInvoker.executeSql(sql);
			}
		}
	}

	public IJmsInvoker getJmsInvoker() {
		return jmsInvoker;
	}

	public void setJmsInvoker(IJmsInvoker jmsInvoker) {
		this.jmsInvoker = jmsInvoker;
	}

}
