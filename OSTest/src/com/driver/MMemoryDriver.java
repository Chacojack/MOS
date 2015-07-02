package com.driver;


import java.util.ArrayList;

import com.hardware.MMemory;
import com.hardware.MMemory.MemoryState;
import com.hardware.MMemoryItem;
import com.manager.MProcess;


/** 
 * 内存  
 * @param map 
 * @return 
 */
public class MMemoryDriver {
	static MMemory memory = MMemory.getMemory();
	
	/** 
    * 申请内存 
    * @param map 
    * @return 返回是否成功
    */ 
	public static boolean apply(double pid,double size){
		memory.setMemoryState(MemoryState.reading);
		if(getAvailableSize()>=size){
			if(getMaxAvailableBlock()[0]>=size){
				memory.setMemoryState(MemoryState.avaliable);
				return true;
			}
		}
			memory.setMemoryState(MemoryState.avaliable);
			return false;
	}
	
	
	public static boolean distribute(double pid,double size,double blocklow){
		if(memory.getMemoryState()==MemoryState.avaliable){
			MMemoryItem memoryItem=new MMemoryItem();
			memoryItem.setPid(pid);
			memoryItem.setLow(blocklow);
			memoryItem.setSize(size);
			memory.setMemoryUsed(memoryItem);
			return true;
		}
		else{
			return false;
		}
		
	}
	
	
	public static boolean release(double pid){
		memory.setMemoryState(MemoryState.writing);
		for(int i=0;i<memory.getMemoryUsed().size();i++){
			if(memory.getMemoryUsed().get(i).getPid()==pid){
				memory.getMemoryUsed().remove(i);
				memory.setMemoryState(MemoryState.avaliable);
			}
		}
		if(memory.getMemoryState()==MemoryState.reading){
			return false;
		}
		else {
			return true;
		}
		
	}
	
    //压缩内存，返回剩余内存
    public static double compress(){
    	double available;
    	ArrayList<MMemoryItem> itemList=memory.getMemoryUsed();
    	double lowLast=0;
    	double highNext=0;
    	for(int i=0;i<itemList.size();i++){
    		if(itemList.get(i).getLow()>highNext+1){
    			itemList.get(i).setLow(highNext+1);
    			highNext=itemList.get(i).getHigh();
    		}
    	}
    	available=memory.getMemorySize()-highNext;
   	    return available;
	}
	
	
	public static float getUsedRate(){
		return (float) ((memory.getMemorySize()-getAvailableSize())/memory.getMemorySize());
	}
	
	public static ArrayList<MMemoryItem> getMemoryUsedList(){
		return memory.getMemoryUsed();
	}
	
	
	private static double getAvailableSize(){
		double unavailableSize=0;
		for(int i=0;i<memory.getMemoryUsed().size();i++){
			unavailableSize=unavailableSize+memory.getMemoryUsed().get(i).getSize();
		}
		return memory.getMemorySize()-unavailableSize;
	}
	
	//返回最坏适应块
	public  static double[] getMaxAvailableBlock(){
		double maxAvailableBlock=0;
		double blockLow = 0;
		double low_this=0;
		double high_last=0;
		double temp;
		double block[] = null;
		for(int i=0;i<memory.getMemoryUsed().size();i++){
			low_this=memory.getMemoryUsed().get(i).getLow();
			temp=low_this-high_last;
			if(temp>maxAvailableBlock){
				maxAvailableBlock=temp;
				blockLow=high_last+1;
			}
			high_last=memory.getMemoryUsed().get(i).getHigh();
		}
		temp=memory.getMemorySize()-high_last;
		if(temp>maxAvailableBlock){
			maxAvailableBlock=temp;
			blockLow=high_last+1;
		}
		block[0]=maxAvailableBlock;
		block[1]=blockLow;
		return block;
	}
	//返回最好适应块
	public  static double[] getBestAvailableBlock(MProcess process){
		double bestAvailableBlock=0;
		double fitLevel=-1;
		double blockLow = 0;
		double low_this=0;
		double high_last=0;
		double temp;
		double block[] = null;
		for(int i=0;i<memory.getMemoryUsed().size();i++){
			low_this=memory.getMemoryUsed().get(i).getLow();
			temp=low_this-high_last;
			if(temp-process.getMemorySize()>=0&&temp-process.getMemorySize()<fitLevel){
				fitLevel=temp-process.getMemorySize();
				bestAvailableBlock=temp;
				blockLow=high_last+1;
			}
		}
		temp=memory.getMemorySize()-high_last;
		if(temp-process.getMemorySize()>=0&&temp-process.getMemorySize()<fitLevel){
			fitLevel=memory.getMemorySize()-high_last;
			bestAvailableBlock=temp;
			blockLow=high_last+1;
		}
		block[0]=bestAvailableBlock;
		block[1]=blockLow;
		return block;
	}
	//返回最先适应块
	public  static double[] getFirstAvailableBlock(MProcess process){
		double firstAvailableBlock=0;
		double blockLow = 0;
		double low_this=0;
		double high_last=0;
		double temp;
		double block[] = null;
		for(int i=0;i<memory.getMemoryUsed().size();i++){
			low_this=memory.getMemoryUsed().get(i).getLow();
			temp=low_this-high_last;
			if(temp>=process.getMemorySize()){
				firstAvailableBlock=temp;
				block[0]=firstAvailableBlock;
				block[1]=high_last+1;
				return block;
			}
		}
		return block;
	}
	
}
