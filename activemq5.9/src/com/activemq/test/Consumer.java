package com.activemq.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
	private static String brokerURL = "tcp://localhost:61616";  
	private static ConnectionFactory factory;  
	private Connection connection;  
	private Session session;  
	  
	public Consumer(){  
		try{
		    factory = new ActiveMQConnectionFactory(brokerURL);  
		    connection = factory.createConnection();  
		    connection.start();  
		    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
		}catch(Exception e){
			e.printStackTrace();
		}
	}  
	  
	public void close() throws JMSException {  
	    if (connection != null) {  
	        connection.close();  
	    }  
	}      
	  
	public static void main(String[] args) throws JMSException {  
	    Consumer consumer = new Consumer(); 
	    String[] message = {"test"};
	    for (String stock : message) {  
	        Destination destination = consumer.getSession().createTopic("STOCKS." + stock);  
	        MessageConsumer messageConsumer = consumer.getSession().createConsumer(destination);  
	        messageConsumer.setMessageListener(new Listener());  
	    }  
	}  
	
	public void getMessage(){
		try{
			Consumer consumer = new Consumer(); 
			Destination destination = consumer.getSession().createTopic("STOCKS.test");  
		    MessageConsumer messageConsumer = consumer.getSession().createConsumer(destination);  
		    messageConsumer.setMessageListener(new Listener());  
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Session getSession() {  
	    return session;  
	}  
}
