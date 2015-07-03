package com.manager;

public class MMessagePoster {
	
	public void sendMessage(MMessage message){
		if(message.getSourceTag().equals(message.getTargetTag())){
			return ;
		}
		MMessageQueueManager.getMessageQueueManager().addMessage(message);
	}

}
