package com.manager;

public class MMemoryManager {
     private static final MMemoryManager mmemoryManager=new MMemoryManager();
     private MMemoryManager(){}
     public static MMemoryManager getMemoryManager(){
    	 return mmemoryManager;
     }
     //申请内存
     public boolean apply(MProcess process){
		return false;
    	 
     }
     //回收内存
     public double recovery(int level){
		return level;
    	 
     }
     //压缩内存，返回剩余内存
     public double compress(){
		return 0;
	 }
     //清理内存，返回剩余内存
     public double cleanUp(){
		return 0;
	 }
}
