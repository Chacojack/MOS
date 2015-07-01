package com.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hardware.DiskBlock;
import com.hardware.MDisk;
import com.hardware.MFile;
import com.hardware.MFile.FileType;
import com.tool.math.MMathTool;

public class MDiskManager {
	
	private static MDiskManager diskManager = new MDiskManager();
	
	private MDiskManager(){}
	
	public static MDiskManager getDiskManager(){
		if(diskManager==null){
			diskManager = new MDiskManager();
		}
		return diskManager;
	}
	
	public boolean divide(double[] sizes){
		System.out.println("divide sizes: "+sizes.toString());
		if(sizes==null||sizes.length==0){
			return false;
		}
		double[] new_sizes = new double[sizes.length+1];
		double last_size = MDisk.getDisk().getDiskSize()-MMathTool.sum(sizes);
		if(last_size>0){
			for(int i=0;i < sizes.length;i++){
				new_sizes[i] = sizes[i];
			}
			new_sizes[new_sizes.length-1] = last_size;
			MDiskManager.getDiskManager().divide(new_sizes);
		}
		double low = 0;
		for(int i = 0;i<sizes.length ;i++ ){
			MDisk disk = MDisk.getDisk();
			MFile mFile = new MFile("block"+(i+1) ,low+1 , sizes[i], FileType.disk);
			disk.getChildFile().put(mFile.getName(), mFile);
			low = low + sizes[i];
		}
		return true;
	}
	
	public float getUsedRate(MFile mFile){
		return 0;
	}
	
	public double getUsedSum(MFile mFile){
		double sum = 0;
		MFile mFile2 = mFile;
		Map<String, MFile> map = mFile2.getChildFile();
		if(map==null){
			return 0;
		}
		Iterator<java.util.Map.Entry<String, MFile>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			java.util.Map.Entry<String, MFile> entry = iterator.next();
			MFile mFile3 = entry.getValue();
			switch (mFile3.getType()) {
			case file:
				sum +=  mFile3.getSize();
				break;
			case directory:
				sum += getUsedSum(mFile3);
			default:
				break;
			}
		}
		return sum;
	}
	
	public boolean distribute(MFile parent ,MFile mFile){
		MFile rootDisk = MDisk.getDisk().getChildFile().get(mFile.getRootDisk());
		if(getUsedRate(rootDisk)+mFile.getSize()>=rootDisk.getSize()){         //先判断是否可以装下
			return false;
		}
		int level = getLevel(mFile);                 //得到文件大小等级
		switch (level) {
		case 1:
			return distributeLevel1(rootDisk,parent,mFile);
		case 2:
			return distributeLevel2(rootDisk,parent,mFile);
		case 3:
			return distributeLevel2(rootDisk,parent,mFile);
		default:
			return false;
		}
	}
	
	public boolean distributeLevel1(MFile rootDisk , MFile parent , MFile mFile){      //比等级一块还大的文件
		int blockNum = (int) Math.ceil(mFile.getSize()/DiskBlock.blockSize_1);
		List<DiskBlock> diskBlocks = rootDisk.getBlockList();
		int count = 0;
		for(int i=diskBlocks.size()-1;i>=0;i--){
			if(diskBlocks.get(i).isAvaliable()&&(diskBlocks.get(i).getBlockList()==null)){   //不可用或者已经将其转化为第二等级块
				count++;
				if(count==blockNum){  //有可以分配的空间
					for(int j = 0;j<blockNum;j++){
						diskBlocks.get(i+j).setAvaliable(false);
					}
					mFile.setLow(rootDisk.getLow()+DiskBlock.blockSize_1*i);
					mFile.setHigh(mFile.getLow()+mFile.getSize());
					parent.getChildFile().put(mFile.getName(), mFile);
					return true;
				}
			}else {
				count= 0;
			}
		}
		return false;
	}
	
	public boolean distributeLevel2(MFile rootDisk , MFile parent , MFile mFile){      //比等级二块大，比等级一的块大的文件
		int blockNum = (int) Math.ceil(mFile.getSize()/DiskBlock.blockSize_2);
		List<DiskBlock> diskBlocks = rootDisk.getBlockList();
		int count = 0;
		for(int i=0;i<diskBlocks.size();i++){       //遍历等级一的块
			List<DiskBlock> diskBlocks_inner = diskBlocks.get(i).getBlockList();
			if(diskBlocks_inner==null){
				if(diskBlocks.get(i).divideBlockToLevel2()){
					continue ;
				}
				diskBlocks_inner = diskBlocks.get(i).getBlockList();
			}  //确保已将其分为等级二的块
			for(int j = diskBlocks_inner.size()-1;j>=0;j--){
				if(diskBlocks_inner.get(j).isAvaliable()){
					count++;
					if(count==blockNum){
						for(int k = 0;k<blockNum;k++){
							diskBlocks_inner.get(j+k).setAvaliable(false);
						}
						mFile.setLow(rootDisk.getLow()+DiskBlock.blockSize_1*i+DiskBlock.blockSize_2*j);
						mFile.setHigh(mFile.getLow()+mFile.getSize());
						parent.getChildFile().put(mFile.getName(), mFile);
						return true;
					}
				}else{
					count=0;
				}
			}
		}
		return false;
	}
	
	public boolean createFloder(MFile parent , MFile mFile){
		if(parent.getChildFile()==null){
			parent.setChildFile(new HashMap<String, MFile>());
		}
		parent.getChildFile().put(mFile.getName(), mFile);
		return true;
	}
	
	public int getLevel(MFile mFile){
		if (mFile.getSize()<DiskBlock.blockSize_2) {
			return 3;
		}else if (mFile.getSize()<DiskBlock.blockSize_1) {
			return 2;
		}else {
			return 1;
		}
	}
	
	public void format(){
		format(MDisk.getDisk());
	}
	
	public void format(MFile mFile){
		if(mFile.getType()!=FileType.disk){
			return ;
		}
		mFile.setChildFile(new HashMap<String, MFile>());
		mFile.initDiskBlock();
	}
	
	public boolean deleteFile(MFile parent,MFile mFile){
		double low = mFile.getLow();
		int level = getLevel(mFile);
		MFile rootDisk = MDisk.getDisk().getChildFile().get(mFile.getRootDisk());
		int blockNum_1 ,blockNum_2;
		switch (level) {
		case 1:
			blockNum_1 = (int) ((low-rootDisk.getLow())/DiskBlock.blockSize_1);
			int num = (int) Math.ceil(mFile.getSize()/DiskBlock.blockSize_1);
			for(int i=0;i<num;i++){
				rootDisk.getBlockList().get(blockNum_1+i).setAvaliable(true);
			}
			parent.getChildFile().remove(mFile.getName());
			return true;
		case 2:
			blockNum_1 = (int) ((low-rootDisk.getLow())/DiskBlock.blockSize_1);
			blockNum_2 = (int) ((low-rootDisk.getLow()-DiskBlock.blockSize_1*blockNum_1)/DiskBlock.blockSize_2);
			int num_2 = (int) Math.ceil(mFile.getSize()/DiskBlock.blockSize_2);
			for(int i=0;i<num_2;i++){
				rootDisk.getBlockList().get(blockNum_1).getBlockList().get(blockNum_2+i).setAvaliable(true);
			}
			parent.getChildFile().remove(mFile.getName());
			return true;
		case 3:
			blockNum_1 = (int) ((low-rootDisk.getLow())/DiskBlock.blockSize_1);
			blockNum_2 = (int) ((low-rootDisk.getLow()-DiskBlock.blockSize_1*blockNum_1)/DiskBlock.blockSize_2);
			rootDisk.getBlockList().get(blockNum_1).getBlockList().get(blockNum_2).setAvaliable(true);
			parent.getChildFile().remove(mFile.getName());
			return true;
		default:
			return false;
		}
	}
	
	public boolean deleteFolder(MFile parent,MFile mFile){
		Iterator<Entry<String, MFile>> iterator = parent.getChildFile().entrySet().iterator();
		while(iterator.hasNext()){
			Entry< String, MFile> entry = iterator.next();
			MFile mFile2 = entry.getValue();
			switch(mFile2.getType()){
			case file:
				return deleteFile(mFile, mFile2);
			case directory:
				return deleteFolder(mFile, mFile2);
			default:
				return true;
			}
		}
		parent.getChildFile().remove(mFile.getName());
		return true;
	}
	
	public boolean delete(MFile parent,MFile mFile) {
		switch (mFile.getType()) {
		case file:
			return deleteFile(parent, mFile);
		case directory:
			return deleteFolder(parent, mFile);
		default:
			return false;
		}
	}
}
