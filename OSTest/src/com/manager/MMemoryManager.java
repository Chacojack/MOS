package com.manager;

import java.awt.List;
import java.util.ArrayList;

import com.driver.MMemoryDriver;
import com.hardware.MMemoryItem;

public class MMemoryManager {
     private static final MMemoryManager mmemoryManager=new MMemoryManager();
     private MMemoryManager(){}
     public static MMemoryManager getMemoryManager(){
    	 return mmemoryManager;
     }
     public enum DistributeType{
    	 worstFit,bestFit,firstFit;
     }
     private DistributeType distributeType=DistributeType.worstFit;
//     MMemoryDriver mMemoryDriver=new MMemoryDriver();
     
     //�����ڴ�
     public boolean apply(MProcess process){
    	MMemoryDriver.apply(process.getPid(), process.getMemorySize());
		return MMemoryDriver.apply(process.getPid(), process.getMemorySize());
    	 
     }
     //�����ڴ�,���ڴ�ķ��������͸����
     public boolean distibuteMemory(MProcess process){
    	 switch (distributeType) {
			case worstFit:
				worstFit(process);
				break;
			case bestFit:
				bestFit(process);
				break;
			case firstFit:
				firstFit(process);
				break;
	     }
    	 return true;
     } 
     private void worstFit(MProcess process){
    	 double[] fitBlock=MMemoryDriver.getMaxAvailableBlock();
    	 MMemoryDriver.distribute(process.getPid(), process.getMemorySize(), fitBlock[1]);
     }
     private void bestFit(MProcess process){
    	 double[] fitBlock=MMemoryDriver.getBestAvailableBlock(process);
    	 MMemoryDriver.distribute(process.getPid(), process.getMemorySize(), fitBlock[1]);
     }
     private void firstFit(MProcess process){
    	 double[] fitBlock=MMemoryDriver.getFirstAvailableBlock(process);
    	 MMemoryDriver.distribute(process.getPid(), process.getMemorySize(), fitBlock[1]);
     }
     //�����ڴ�
     public double recovery(int level,double completedPid){
    	 double availableMemory=0;
        if(level==0){
            boolean isReleased= MMemoryDriver.release(completedPid);
            if(isReleased){
            	availableMemory=MMemoryDriver.compress();
            	return availableMemory;
            }
            else{
            	System.out.println("�ͷ��ڴ�û�гɹ���δ����ѹ���ڴ�ռ�Ĳ���");
            	return -1;
            }
           
        }
        else{
        	return -1;
        }
     }
 
     //�����ڴ棬����ʣ���ڴ�
     public double cleanUp(double completedPid){
    	double availableMemory=0;
    	double isRecovered=recovery(0,completedPid);
    	if(isRecovered>0){
    		return availableMemory;
    	}
    	else{
    		return -1;
    	}
    	
	 }
     public float getUsedRate(){
    	 return MMemoryDriver.getUsedRate();
     }
     //�鿴�ڴ�ռ�����Ƿ񳬹���������������ͷŽ���ռ�ã�����ռ䣬��������
     public double checkMemoryState(){
    	 return 0;
     }
     
	 public DistributeType getDistributeType() {
		 return distributeType;
	 }
	 public void setDistributeType(DistributeType distributeType) {
		 this.distributeType = distributeType;
	 }
}
