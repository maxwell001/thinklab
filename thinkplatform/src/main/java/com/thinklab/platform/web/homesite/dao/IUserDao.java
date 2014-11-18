package com.thinklab.platform.web.homesite.dao;

import com.thinklab.platform.web.homesite.model.User;

public interface IUserDao {
	
	public User getUserInfoById(Long id);
	
	public User getUserInfoByLoginName(String loginName);
	
	public Long saveUserInfo(User user);
	
	public void deleteUserInfoById(Long id);
}
