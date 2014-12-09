package com.activemq.jpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.activemq.jpa.dao.AccessionNoDao;
import com.activemq.jpa.model.AccessionNo;

/**
 * 登记Service
 * 
 * @author 王俊
 * @version 2013-11-01
 * 
 *          modify:
 * @author Amis
 * @version 2013-11-07
 */
@Component
@Transactional(readOnly = true)
@Qualifier("accessionService")
public class AccessionService {

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory
            .getLogger(AccessionService.class);

    @Autowired
    private AccessionNoDao accessionNoDao;


    public void save(AccessionNo accessionNo) {
    	accessionNoDao.save(accessionNo);
    }


	public AccessionNoDao getAccessionNoDao() {
		return accessionNoDao;
	}


	public void setAccessionNoDao(AccessionNoDao accessionNoDao) {
		this.accessionNoDao = accessionNoDao;
	}

}
