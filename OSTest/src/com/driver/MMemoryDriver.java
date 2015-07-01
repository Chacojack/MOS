package com.driver;


import java.sql.Array;
import java.util.ArrayList;

import com.hardware.MMemory;
import com.hardware.MMemory.MemoryState;
import com.hardware.MMemoryItem;


/** 
 * 内存  
 * @param map 
 * @return 
 */
public class MMemoryDriver {
	MMemory memory = MMemory.getMemory();
	
	/** 
    * 申请内存 
    * @param map 
    * @return 返回是否成功
    */ 
	public  boolean apply(double pid,double size){
		memory.setMemoryState(MemoryState.reading);
		if(getAvailableSize()>=size){
			if(getMaxAvailableBlock()[0]>=size){
				boolean isComplete=distribute(pid,size,getMaxAvailableBlock()[1]);
				memory.setMemoryState(MemoryState.avaliable);
			    return isComplete;
			}
			else {
				memory.setMemoryState(MemoryState.avaliable);
				return false;
			}
		}
		else{
			memory.setMemoryState(MemoryState.avaliable);
			return false;
		}
	}
	
	
	public boolean distribute(double pid,double size,double blocklow){
		if(memory.getMemoryState()==MemoryState.avaliable){
			MMemoryItem memoryItem=new MMemoryItem();
			memoryItem.setPid(pid);
			memoryItem.setLow(blocklow);
			memoryItem.setHigh(blocklow+size);
			memoryItem.setSize(size);
			memory.setMemoryUsed(memoryItem);
			return true;
		}
		else{
			return false;
		}
		
	}
	
	
	public boolean release(double pid){
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
	
	
	public float getUsedRate(){
		return (float) ((memory.getMemorySize()-getAvailableSize())/memory.getMemorySize());
	}
	
	public ArrayList<MMemoryItem> getMemoryUsedList(){
		return memory.getMemoryUsed();
	}
	
	
	private double getAvailableSize(){
		double unavailableSize=0;
		for(int i=0;i<memory.getMemoryUsed().size();i++){
			unavailableSize=unavailableSize+memory.getMemoryUsed().get(i).getSize();
		}
		return memory.getMemorySize()-unavailableSize;
	}
	
	
	private double[] getMaxAvailableBlock(){
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
			}
			high_last=memory.getMemoryUsed().get(i).getHigh();
		}
		temp=memory.getMemorySize()-high_last;
		if(temp>maxAvailableBlock){
			maxAvailableBlock=temp;
		}
		block[0]=maxAvailableBlock;
		block[1]=blockLow;
		return block;
	}
	
}
