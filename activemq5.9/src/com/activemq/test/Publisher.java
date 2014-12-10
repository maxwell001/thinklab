/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.activemq.test;


import java.util.Hashtable;
import java.util.Map;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;
  
public class Publisher {  
      
    protected int MAX_DELTA_PERCENT = 1;  
    protected Map<String, Double> LAST_PRICES = new Hashtable<String, Double>();  
    protected static int count = 10;  
    protected static int total;  
      
    protected static String brokerURL = "tcp://localhost:61616";  
    protected static ConnectionFactory factory;  
    protected Connection connection;  
    protected Session session;  
    protected MessageProducer producer;  
      
    public Publisher(){  
        try { 
        	factory = new ActiveMQConnectionFactory(brokerURL);  
            connection = factory.createConnection();  
            connection.start(); 
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
            producer = session.createProducer(null);  
        } catch (Exception jmse) {  
            try {
				connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
            jmse.printStackTrace();
        }  
        
    }  
      
    public void close() throws JMSException {  
        if (connection != null) {  
            connection.close();  
        }  
    }  
      
    public static void main(String[] args) throws JMSException {  
        Publisher publisher = new Publisher(); 
        publisher.sendMessage("tesfdfdaf");  
        System.out.println("Published '" + count + "' of '" + total + "' price messages");  
        try {  
              Thread.sleep(1000);  
            } catch (InterruptedException x) {  
            }  
        publisher.close();  
    }  
  
    protected void sendMessage(String str) { 
    	try{
	        String stock = "test";  
	        Destination destination = session.createTopic("STOCKS." + stock);  
	        Message message = createStockMessage(str, session);  
	        System.out.println("Sending: " + ((ActiveMQMapMessage)message).getContentMap() + " on destination: " + destination);  
	        producer.send(destination, message); 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }  
  
    protected Message createStockMessage(String str, Session session) throws JMSException {  
        MapMessage message = session.createMapMessage();  
        message.setString("sql", str);  
        return message;  
    }  
  
    protected double mutatePrice(double price) {  
        double percentChange = (2 * Math.random() * MAX_DELTA_PERCENT) - MAX_DELTA_PERCENT;  
  
        return price * (100 + percentChange) / 100;  
    }  
  
}  