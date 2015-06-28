package com.manager;

import java.util.Map;

public class MUser {
	private String userId;
	private String userName;
	private Map<String,Object> userAttributeMap;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Map<String,Object> getUserAttributeMap() {
		return userAttributeMap;
	}
	public void setUserAttributeMap(Map<String,Object> userAttributeMap) {
		this.userAttributeMap = userAttributeMap;
	}

}
