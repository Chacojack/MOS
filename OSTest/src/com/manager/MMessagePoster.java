package com.manager;

public class MMessagePoster {
	
	public void sendMessage(MMessage message){
		MMessageQueueManager.getMessageQueueManager().addMessage(message);
	}

}
