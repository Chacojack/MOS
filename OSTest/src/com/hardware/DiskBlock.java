package com.hardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiskBlock implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final double blockSize_1 = 1*MDisk.MB , blockSize_2 = 1*MDisk.KB ;
	
	/**
	 * LEVEL_1表示第一类磁盘快，磁盘块大小为blockSize_1
	 * LEVEL_2表示第二类磁盘快，磁盘块大小为blockSize_2
	 */
	public static final int LEVEL_1 = 1, LEVEL_2 = 2;
	
	private int level ;
	private boolean avaliable = true;
	private List<DiskBlock> blockList ;
	
	public DiskBlock(int level) {
		super();
		this.level = level;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public List<DiskBlock> getBlockList() {
		if(level!=LEVEL_1){
			return null;
		}
		return blockList;
	}
	public void setBlockList(List<DiskBlock> blockList) {
		if(level!=LEVEL_1){
			return;
		}
		this.blockList = blockList;
	}
	public boolean isAvaliable() {
		return avaliable;
	}
	public void setAvaliable(boolean avaliable) {
		this.avaliable = avaliable;
	}
	
	public boolean divideBlockToLevel2(){
		if (level!=LEVEL_1) {
			return false;
		}
		blockList = new ArrayList<DiskBlock>();
		int blockNum = (int) (blockSize_1/blockSize_2);
		for(int i=0;i<blockNum;i++){
			blockList.add(new DiskBlock(DiskBlock.LEVEL_2));
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "DiskBlock [blockSize_1=" + blockSize_1 + ", blockSize_2="
				+ blockSize_2 + ", level=" + level + ", avaliable=" + avaliable
				+ ", blockList=" + blockList + "]";
	}
	
	
	
	
	
}
