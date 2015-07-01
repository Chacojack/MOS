package com.hardware;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MFile implements Serializable{
	
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
	private String parent ;
	private String data ;
	private double size ;
	private FileType type ;
	
	public MFile() {
		super();
	}
	
	public MFile(String name, double low, double size, FileType type) {
		super();
		this.name = name;
		this.low = low;
		this.size = size;
		this.type = type;
		this.high = low + size; 
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
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
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

	@Override
	public String toString() {
		return "MFile [id=" + id + ", name=" + name + ", low=" + low
				+ ", high=" + high + ", iconPath=" + iconPath + ", path="
				+ path + ", childFile=" + childFile + ", parent=" + parent
				+ ", data=" + data + ", size=" + size + ", type=" + type + "]";
	}

	
	
	

}
