package com.manager;

public class MMemoryManager {
     private static final MMemoryManager mmemoryManager=new MMemoryManager();
     private MMemoryManager(){}
     public static MMemoryManager getMemoryManager(){
    	 return mmemoryManager;
     }
     //�����ڴ�
     public boolean apply(MProcess process){
		return false;
    	 
     }
     //�����ڴ�
     public double recovery(int level){
		return level;
    	 
     }
     //ѹ���ڴ棬����ʣ���ڴ�
     public double compress(){
		return 0;
	 }
     //�����ڴ棬����ʣ���ڴ�
     public double cleanUp(){
		return 0;
	 }
}
