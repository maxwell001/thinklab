package com.thinklab.platform.web.homesite.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.thinklab.platform.util.StringUtil;
import com.thinklab.platform.web.homesite.dao.IUserDao;
import com.thinklab.platform.web.homesite.model.User;

@Repository
public class UserDaoImpl implements IUserDao{
	@Autowired
	private SqlSession sqlSession;

	public User getUserInfoById(Long id) {
		String idStr = StringUtil.longToString(id);
		User user = sqlSession.selectOne("getUserInfoById", idStr);
		return user;
	}

	public User getUserInfoByLoginName(String loginName) {
		User user = sqlSession.selectOne("getUserInfoByLoginName", loginName);
		return user;
	}

	public Long saveUserInfo(User user) {
		Long id = (long) sqlSession.insert("saveUserInfo", user);
		return id;
	}

	public void deleteUserInfoById(Long id) {
		sqlSession.delete("deleteUserInfoById", id);
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}
