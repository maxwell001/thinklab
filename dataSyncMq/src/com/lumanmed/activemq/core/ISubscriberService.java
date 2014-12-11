package com.lumanmed.activemq.core;

import java.util.Map;

public interface ISubscriberService {
	
	public void reciveMessage(Object message);
	
	public void consumeMessage(Map<String,Object> message)throws Exception;
}
