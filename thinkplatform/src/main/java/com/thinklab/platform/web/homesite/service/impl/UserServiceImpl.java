package com.thinklab.platform.web.homesite.service.impl;

import com.thinklab.platform.web.homesite.dao.IUserDao;
import com.thinklab.platform.web.homesite.model.User;
import com.thinklab.platform.web.homesite.service.IUserService;

public class UserServiceImpl implements IUserService{

	private IUserDao userDao;
	
	public User getUserInfoById(Long id) {
		User user = userDao.getUserInfoById(id);
		return user;
	}

	public User getUserInfoByLoginName(String loginName) {
		User user = userDao.getUserInfoByLoginName(loginName);
		return user;
	}

	public Long saveUserInfo(User user) {
		Long id = userDao.saveUserInfo(user);
		return id;
	}

	public void deleteUserInfoById(Long id) {
		userDao.deleteUserInfoById(id);
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

}
