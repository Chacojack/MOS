package com.manager;

import java.util.ArrayList;
import java.util.List;


public class MUserProcess extends MApplicationProcess {
	
	private List<MMessage> messageList = new ArrayList<>();
	
	public MUserProcess(MUser user) {
		super();
		this.setUser(user);
		this.setTag(user.getUserId());
		this.registerMessageReciever();
	}
	
	public void sendMessage(MMessage message){
		MMessagePoster messagePoster = new MMessagePoster();
		message.setSourceTag(this.getUser().getUserId());
		messagePoster.sendMessage(message);
	}

	@Override
	public void onRecieveMessage(MMessage message) {
		super.onRecieveMessage(message);
		messageList.add(message);
	}

	public List<MMessage> getMessageList() {
		return messageList;
	}
	
	public void removeMessage(MMessage message ){
		messageList.remove(message);
	}
	
	

}
