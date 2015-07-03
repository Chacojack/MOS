package com.manager;

import java.io.Serializable;
import java.util.Map;

public class MUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public enum UserType{
		administrator,user
	}
	
	private String userId;
	private String userName;
	private Map<String,Object> userAttributeMap;
	private UserType level;
	private String password;
	
	
	
	public MUser(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}
	
	
	
	public MUser(String userId, String userName, UserType level, String password) {
		this(userId, userName,null,level,password);
	}

	/**
	 * 
	 * @param userId
	 * @param userName
	 * @param userAttributeMap
	 * @param level
	 * @param password
	 */
	public MUser(String userId, String userName,
			Map<String, Object> userAttributeMap, UserType level,
			String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userAttributeMap = userAttributeMap;
		this.level = level;
		this.password = password;
	}

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
	public UserType getLevel() {
		return level;
	}
	public void setLevel(UserType level) {
		this.level = level;
	}
	public boolean verify(String password){
		return this.password.equals(password);
	}
	

}
