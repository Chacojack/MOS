package com.main;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.app.launcher.frame.MOSFrame;
import com.app.launcher.frame.UserLoginFrame;
import com.hardware.MDisk;
import com.hardware.MFile;
import com.manager.MMessageQueueManager;

public class MSO {
	
	public static void initHardware(){
		MDisk.getDisk().initDisk();
	}
	
	public static void startManagers(){
		MMessageQueueManager.getMessageQueueManager().start();
	}
	
	public static void main(String[] args) {
		initHardware();
		startManagers();
		/*Map<String, MFile> map = MDisk.getDisk().getChildFile();
		Iterator<Entry<String, MFile>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, MFile> entry = (Entry<String, MFile>)(iterator.next());
			MFile mFile = entry.getValue();
			
			System.out.println(mFile.toString());
		}*/
		new UserLoginFrame();
//		new MOSFrame();
	}
	
	

}
