package com.manager;

import java.util.ArrayList;
import java.util.List;

public class MMessageQueue {
	
	private List<MMessage> messageList = new ArrayList<MMessage>();
	
	public boolean addMessage(MMessage message){
		messageList.add(message);
		return true;
	}
	
	public MMessage deleteMessage(int index){
		return messageList.remove(index);
	}
	
	public MMessage getMMessage(int position){
		return messageList.get(position);
	}
	
	public int length(){
		return messageList.size();
	}

}
