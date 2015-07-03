package com.manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.manager.MUser.UserType;
import com.tool.security.md5.MD5;

public class MUserManager {
	
	private MUser superUser = new MUser("administrator", "MOS-User", null, UserType.administrator, MD5.getMD5Str("123")); 
	
	private HashMap<String,MUser> userRegistry = new HashMap<String,MUser>();
	
	private HashMap<String, MUserProcess > onLineUsers = new HashMap<String, MUserProcess>();
	
	private MUserProcess foregroundUser ;
	
	private static MUserManager userManager = new MUserManager();
	
	private MUserManager(){
//		userRegistry.put(superUser.getUserId(), superUser);
//		saveUserRegistry();
		initUserRegistry();
	}
	
	public MUser login(String userId,String password){
		System.out.println("login: userid: "+ userId +" password: "+password);
		if(userRegistry.get(userId).verify(password)){
			MUserProcess mProcess = new MUserProcess(userRegistry.get(userId)) ;
			onLineUsers.put(userId, mProcess);
			foregroundUser = mProcess;
			return userRegistry.get(userId);
		}
		return null;
	}
	
	public boolean createUser(String userId, String name,MUser.UserType level,String password){
		MUser user = new MUser(userId, name, level, MD5.getMD5Str(password));
		userRegistry.put(userId, user);
		saveUserRegistry();
		return true;
	}

	

	public void saveUserRegistry(){
		FileOutputStream fileOutputStream = null ;
		ObjectOutputStream objectOutputStream = null ;
		try {
			fileOutputStream = new FileOutputStream("system\\user\\user.ini");
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(userRegistry);
			System.out.println("save user registry over!");
		} catch (IOException e1) {
			System.out.println("save user registry faild!");
		} finally {
			try {
				fileOutputStream.close();
				objectOutputStream.close();
			} catch (IOException e1) {
				System.out.println("save user registry iostream close faild!");
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void initUserRegistry() {
		FileInputStream fileInputStream = null ;
		ObjectInputStream objectInputStream = null ;
		try {
			fileInputStream = new FileInputStream("system\\user\\user.ini");
			objectInputStream = new ObjectInputStream(fileInputStream);
			userRegistry = (HashMap<String, MUser>) objectInputStream.readObject();
			System.out.println("init user registry over!");
		} catch (IOException e1) {
			System.out.println("init user registry faild!");
		} catch (ClassNotFoundException e) {
			System.out.println("init user registry faild! because class not found!");
		} finally {
			try {
				fileInputStream.close();
				objectInputStream.close();
			} catch (IOException e1) {
				System.out.println("init user registry iostream close faild!");
			}
		}
	}

	public static MUserManager getUserManager() {
		if(userManager==null){
			userManager = new MUserManager();
		}
		return userManager;
	}

	
	
	public void updateName(String id,String name){
		userRegistry.get(id).setUserName(name);
	}
	
	public void updateType(String id,MUser.UserType type){
		userRegistry.get(id).setLevel(type);
	}

	public MUserProcess getForegroundUser() {
		return foregroundUser;
	}
	
	public void setForegroundUser(String userId){
		foregroundUser = onLineUsers.get(userId);
	}

	public HashMap<String, MUserProcess> getOnLineUsers() {
		return onLineUsers;
	}
	
	public String getName(String userId){
		return this.userRegistry.get(userId).getUserName();
	}

	
}







