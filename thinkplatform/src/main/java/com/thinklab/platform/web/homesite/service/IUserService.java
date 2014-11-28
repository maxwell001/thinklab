package com.thinklab.platform.web.homesite.service;

import com.thinklab.platform.web.homesite.model.User;

public interface IUserService {

	public User getUserInfoById(Long id);
	
	public User getUserInfoByLoginName(String loginName);
	
	public Long saveUserInfo(User user);
	
	public void deleteUserInfoById(Long id);
}
