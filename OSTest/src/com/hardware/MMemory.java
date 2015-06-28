package com.hardware;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MMemory { 
	public enum MemoryState{
		reading,writing,avaliable;
	}
	private static final MMemory mmemory=new MMemory();
	private String memoryModel;
	private double memorySize;
	private String memoryFactoryInfo;
	private MemoryState memoryState;
	private String memoryExtraInfo;
//	private Map<String,MMemoryItem> memoryUsed=new LinkedHashMap<String, MMemoryItem>();
	private ArrayList<MMemoryItem> memoryUsed;
	private MMemory(){};
	public static MMemory getMemory(){
		return mmemory;
	}
	
	public String getMemoryModel() {
		return memoryModel;
	}
	public void setMemoryModel(String memoryModel) {
		this.memoryModel = memoryModel;
	}
	public double getMemorySize() {
		return memorySize;
	}
	public void setMemorySize(double memorySize) {
		this.memorySize = memorySize;
	}
	public String getMemoryFactoryInfo() {
		return memoryFactoryInfo;
	}
	public void setMemoryFactoryInfo(String memoryFactoryInfo) {
		this.memoryFactoryInfo = memoryFactoryInfo;
	}
	public String getMemoryExtraInfo() {
		return memoryExtraInfo;
	}
	public void setMemoryExtraInfo(String memoryExtraInfo) {
		this.memoryExtraInfo = memoryExtraInfo;
	}
	public MemoryState getMemoryState() {
		return memoryState;
	}
	public void setMemoryState(MemoryState memoryState) {
		this.memoryState = memoryState;
	}
//	public Map<String,MMemoryItem> getMemoryUsed() {
//		return memoryUsed;
//	}
	public ArrayList<MMemoryItem> getMemoryUsed() {
		return memoryUsed;
	}
	public void setMemoryUsed(MMemoryItem memoryItem) {
		double tempLow = 0;
		double newLow=memoryItem.getLow();
		if(this.memoryUsed.get(0).getLow()<newLow){
			for(int i=0;i<this.memoryUsed.size();i++)    
			{    
				if(this.memoryUsed.get(i).getLow()<newLow){
					this.memoryUsed.add(i+1, memoryItem);
				}
			} 
		}
		else{
			this.memoryUsed.add(0, memoryItem);
		}
		
	}

}
