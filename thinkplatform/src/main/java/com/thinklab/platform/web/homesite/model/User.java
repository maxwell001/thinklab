package com.thinklab.platform.web.homesite.model;

import java.util.Date;

public class User {
	private Long id;
	private String userName;
	private String loginName;
	private String passWard;
	private String status;
	private String type;
	private Integer onlineTimeById;
	private Date lastLoginDate;
	private Date registeDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassWard() {
		return passWard;
	}
	public void setPassWard(String passWard) {
		this.passWard = passWard;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getOnlineTimeById() {
		return onlineTimeById;
	}
	public void setOnlineTimeById(Integer onlineTimeById) {
		this.onlineTimeById = onlineTimeById;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Date getRegisteDate() {
		return registeDate;
	}
	public void setRegisteDate(Date registeDate) {
		this.registeDate = registeDate;
	}
	
}
