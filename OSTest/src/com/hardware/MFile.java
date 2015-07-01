package com.hardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MFile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum FileType{
		file,directory,disk
	}
	
	private String id;
	private String name ;
	private double low;
	private double high;
	private String iconPath;
	private String path;
	private Map<String , MFile> childFile  = new HashMap<String, MFile>();
	private String rootDisk ;
	private String data ;
	private double size ;
	private FileType type ;
	private List<DiskBlock> blockList = new ArrayList<>();
	
	public MFile() {
		super();
	}
	
	
	
	
	public MFile(String name, String rootDisk, FileType type) {
		this(name,0,0,FileType.directory);
		this.rootDisk = rootDisk;
	}




	public MFile(String name, double low, double size, FileType type) {
		super();
		this.name = name;
		this.low = low;
		this.size = size;
		this.type = type;
		this.high = low + size; 
		if(type == FileType.disk){
			initDiskBlock();
		}
	}
	
	public void initDiskBlock(){
		blockList.clear();
		int blockNum = (int) Math.ceil(size/MDisk.MB);
		for(int i = 0;i<blockNum;i++){
			blockList.add(new DiskBlock(DiskBlock.LEVEL_1));
		}
		blockList.get(0).divideBlockToLevel2();
	}

	public MFile(String name, String rootDisk, double size, FileType type) {
		super();
		this.name = name;
		this.rootDisk = rootDisk;
		this.size = size;
		this.type = type;
	}




	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Map<String, MFile> getChildFile() {
		return childFile;
	}
	public void setChildFile(Map<String, MFile> childFile) {
		this.childFile = childFile;
	}
	public String getRootDisk() {
		return rootDisk;
	}
	public void setRootDisk(String rootDisk) {
		this.rootDisk = rootDisk;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public FileType getType() {
		return type;
	}
	public void setType(FileType type) {
		this.type = type;
	}
	public List<DiskBlock> getBlockList() {
		if(this.type!=FileType.disk){
			return null;
		}
		return blockList;
	}
	public void setBlockList(List<DiskBlock> blockList) {
		if(this.type!=FileType.disk){
			return ;
		}
		this.blockList = blockList;
	}
	

	@Override
	public String toString() {
		return "MFile [id=" + id + ", name=" + name + ", low=" + low
				+ ", high=" + high + ", iconPath=" + iconPath + ", path="
				+ path + ", childFile=" + childFile + ", rootDisk=" + rootDisk
				+ ", data=" + data + ", size=" + size + ", type=" + type + "]";
	}

	

	
	
	

}
