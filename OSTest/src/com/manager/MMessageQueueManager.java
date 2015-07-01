package com.manager;

import java.util.HashMap;
import java.util.Map;

public class MMessageQueueManager extends MProcess{
	
	private MMessageQueue messageQueue = new MMessageQueue();
	
	private Map<String,MMessageReciever> recieverRegistry = new HashMap<>();
	
	private static MMessageQueueManager messageQueueManager = new MMessageQueueManager();
	
	private MMessageQueueManager() {}
	
	public static MMessageQueueManager getMessageQueueManager(){
		if(messageQueueManager==null){
			messageQueueManager = new MMessageQueueManager();
		}
		return messageQueueManager;
	}
	
	public boolean registerReciecer(MMessageReciever reciever){
		recieverRegistry.put(reciever.getTag(), reciever);
		return true;
	}
	
	public boolean addMessage(MMessage message){
		return messageQueue.addMessage(message);
	}

	@Override
	public void run() {
		for(;;){
			if(messageQueue.length()>0){
				MMessage message = messageQueue.deleteMessage(0);
				MMessageReciever reciever = recieverRegistry.get(message.getTargetTag());
				reciever.recieverMessage(message);
			}
		}
	}
}
