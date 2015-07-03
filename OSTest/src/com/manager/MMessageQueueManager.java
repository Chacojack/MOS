package com.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
		System.out.println("��Ϣ�������յ�һ��ע����Ϣ��");
		recieverRegistry.put(reciever.getTag(), reciever);
		printRecieverRegistry();
		return true;
	}
	
	public boolean addMessage(MMessage message){
		synchronized (messageQueue) {
			System.out.println("��Ϣ���н��յ�һ����Ϣ��");
			return messageQueue.addMessage(message);
		}
	}

	@Override
	public void run() {
		System.out.println("��Ϣ���п�ʼ��ת��");
		for(;;){
			synchronized (messageQueue) {
				if(messageQueue.length()>0){
					System.out.println("��Ϣ���д���һ����Ϣ��");
					printRecieverRegistry();
					System.out.println();
					MMessage message = messageQueue.deleteMessage(0);
					System.out.println(message.toString());
					MMessageReciever reciever = recieverRegistry.get(message.getTargetTag());
					reciever.recieverMessage(message);
				}
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {}
		}
	}
	
	public void printRecieverRegistry(){
		Set<Entry<String, MMessageReciever>> set = recieverRegistry.entrySet();
		for(Entry< String, MMessageReciever> entry : set) {
			System.out.println(entry.getValue().getTag());
		}
	}
}
