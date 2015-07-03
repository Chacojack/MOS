package com.manager;


public class MApplicationProcess extends MProcess {
	
	private IMessageRecieveListener messageRecieveListener;
	
	private MMessageReciever messageReciever ;

	public MApplicationProcess() {
		super();
	}
	
	public void setTag(String tag){
		messageReciever = new MMessageReciever(tag){
			@Override
			public void recieverMessage(MMessage message) {
				super.recieverMessage(message);
				onRecieveMessage(message);
				if(messageRecieveListener!=null){
					messageRecieveListener.onRecieveMessage(message);
				}
			}
		};
	}
	
	public void registerMessageReciever(){
		MMessageQueueManager.getMessageQueueManager().registerReciecer(messageReciever);
	}
	
	public void onRecieveMessage(MMessage message){
		
	}
	
	public void addMessageRecieveListener(IMessageRecieveListener iListener){
		messageRecieveListener = iListener;
	}
	
	
	
	

}
