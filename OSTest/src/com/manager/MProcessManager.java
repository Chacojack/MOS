package com.manager;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.driver.MCPUDriver;
import com.hardware.MCPU;

public class MProcessManager extends MProcess{
	private static final MProcessManager mprocessManager = new MProcessManager(); 
	public enum ManagerType {
		firstInFirstOut,shortProcessFirst,timeSliceRound
	}
	private ArrayList<Double> readyProcessList;
	private ArrayList<Double> blockProcessList;
	private Map<Double, MProcess> processRegistry=new HashMap<>();
	private double runningProcess;
	private ManagerType managerType=ManagerType.firstInFirstOut;
	private MMemoryManager mmemoryManager=MMemoryManager.getMemoryManager();//获得内存管理实例
	
	private MProcessManager(){}
	public static MProcessManager getProcessManager(){
		return mprocessManager;
	}
	//进程执行代码块
	public void run(){
		registerProcess(this);
		
			while(true){
				if(!readyProcessList.isEmpty()){
					switch (managerType) {
					case firstInFirstOut:
						firstInFirstOut();
						break;
					case shortProcessFirst:
						shortProcessFirst();
						break;
					case timeSliceRound:
						timeSliceRound();
						break;
					}
				}
			}
	}
	//先进先出策略
	private void firstInFirstOut(){
		runningProcess=readyProcessList.get(readyProcessList.size()-1);
		readyProcessList.remove(readyProcessList.size()-1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		double runTime=processRegistry.get(runningProcess).getRunTime();
		runTime=runTime-MCPUDriver.time;
		processRegistry.get(runningProcess).setRunTime(runTime);
		checkProcessState(runningProcess);
//		readyProcessList.add(0, runningProcess);
	}
	//短作业优先
	private void shortProcessFirst(){
		double shortProcess=0;
		double shortTime=0;
		for(int i=0;i<readyProcessList.size();i++){
			if(processRegistry.get(readyProcessList.get(i)).getRunTime()<shortTime){
				shortTime=processRegistry.get(readyProcessList.get(i)).getRunTime();
				shortProcess=readyProcessList.get(i);
			}
		}
	}
	//时间片轮转
	private void timeSliceRound(){
		
	}
	
	//判断运行进程在该时间片结束之后的状态，若已经完成，则从注册队列中将其剔除，然后检测内存使用情况，如果已经达到使用峰值，则调用MMemoryManager进行内存的整理，整理完成后检测阻塞队列是否为空，如不为空，则令其再次申请内存。
	private void  checkProcessState(double runningProcess){
		if(processRegistry.get(runningProcess).getRunTime()<=0){
			
		}
		else{
			readyProcessList.add(0, runningProcess);
		}
	}
	
	//注册进程
	public boolean registerProcess(MProcess process){
		processRegistry.put(process.getPid(), process);
		if(mmemoryManager.apply(process)){
			readyProcessList.add(process.getPid());
		}
		else{
			blockProcessList.add(process.getPid());
		}
		return true;
	}
	//注销进程
	public boolean unregisterProcess(double pid){
		processRegistry.remove(pid);
		return true;
	}
	//设置进程调度策略
	public boolean setManagerType(ManagerType type){
		managerType=type;
		return true;
	}
	//获得进程调度策略
	public ManagerType getManagerType(){
		return  managerType;
	}
	
	
	
	
}
