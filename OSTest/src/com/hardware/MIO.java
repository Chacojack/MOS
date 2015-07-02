package com.hardware;

import java.util.HashMap;
import java.util.Map;

public class MIO {
	private static final MIO io = new MIO();
	private  Map<Integer, Double> ioUserList=new HashMap();
	private MIO(){
		ioUserList.put(1, null);
		ioUserList.put(2, null);
		ioUserList.put(3, null);
		ioUserList.put(4, null);
		ioUserList.put(5, null);
	}
	public static MIO getMio(){
		return io;
	}
	public Map<Integer, Double> getIOUserList(){
		return ioUserList;
	}
}
