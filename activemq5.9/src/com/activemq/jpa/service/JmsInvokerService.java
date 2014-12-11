package com.activemq.jpa.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lumanmed.activemq.core.IJmsInvoker;

@Component
@Transactional(readOnly = true)
@Qualifier("accessionService")
public class JmsInvokerService implements IJmsInvoker{

	@Autowired
	private EntityManagerFactory emf;
	
	@Override
	public void executeSql(String sql) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNativeQuery(sql);
		query.executeUpdate();
	}

}
