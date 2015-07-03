package com.manager;

public class MMessage {
	
	public int arg1,arg2;
	
	public Object object;
	
	public String targetTag,sourceTag;

	public int getArg1() {
		return arg1;
	}

	public void setArg1(int arg1) {
		this.arg1 = arg1;
	}

	public int getArg2() {
		return arg2;
	}

	public void setArg2(int arg2) {
		this.arg2 = arg2;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getTargetTag() {
		return targetTag;
	}

	public void setTargetTag(String targetTag) {
		this.targetTag = targetTag;
	}

	public String getSourceTag() {
		return sourceTag;
	}

	public void setSourceTag(String sourceTag) {
		this.sourceTag = sourceTag;
	}

	@Override
	public String toString() {
		return "MMessage [arg1=" + arg1 + ", arg2=" + arg2 + ", object="
				+ object + ", targetTag=" + targetTag + ", sourceTag="
				+ sourceTag + "]";
	}
	
	

}
