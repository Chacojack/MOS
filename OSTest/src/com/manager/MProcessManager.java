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
	private ArrayList<Double> readyProcessList=new ArrayList<>();
	private ArrayList<Double> blockProcessList=new ArrayList<>();
	private Map<Double, MProcess> processRegistry=new HashMap<>();
	private double runningProcess;
	private ManagerType managerType=ManagerType.firstInFirstOut;
	private MMemoryManager mmemoryManager=MMemoryManager.getMemoryManager();//获得内存管理实例
	private MIOManager mioManager=MIOManager.getIOManager();
	
	private MProcessManager(){
		this.start();
	}
	public static MProcessManager getProcessManager(){
		return mprocessManager;
	}
	//进程执行代码块
	public void run(){
//		registerProcess(this);
			while(true){
				if(readyProcessList==null||readyProcessList.isEmpty()){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else{
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
	//时间片轮转策略
	private void timeSliceRound(){
		runningProcess=readyProcessList.get(readyProcessList.size()-1);
		readyProcessList.remove(readyProcessList.size()-1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		double runTime=getProcessRegistry().get(runningProcess).getRunTime();
		runTime=runTime-MCPUDriver.time;
		getProcessRegistry().get(runningProcess).setRunTime(runTime);
//		checkProcessState(runningProcess);
		if(runTime<=0){
//			checkMemoryState(runningProcess);
			unregisterProcess(runningProcess);
		}
		else{
			readyProcessList.add(0, runningProcess);
		}
		
	}
	
	//短作业优先
	private void shortProcessFirst(){
		double shortProcess=0;
		double shortTime=0;
		for(int i=0;i<readyProcessList.size();i++){
			if(getProcessRegistry().get(readyProcessList.get(i)).getRunTime()<shortTime){
				shortTime=getProcessRegistry().get(readyProcessList.get(i)).getRunTime();
				shortProcess=readyProcessList.get(i);
			}
		}
		readyProcessList.remove(shortProcess);
		try {
			Thread.sleep(1000*((int)(getProcessRegistry().get(shortProcess).getRunTime()/MCPUDriver.time)+1));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		processRegistry.remove(shortProcess);
		unregisterProcess(shortProcess);
//		checkMemoryState(shortProcess);
		
	}
	//先进先出
	private void firstInFirstOut(){
		runningProcess=readyProcessList.get(readyProcessList.size()-1);
		readyProcessList.remove(readyProcessList.size()-1);
		try {
			Thread.sleep(1000*((int)(getProcessRegistry().get(runningProcess).getRunTime()/MCPUDriver.time)+1));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		processRegistry.remove(runningProcess);
		unregisterProcess(runningProcess);
//		checkMemoryState(runningProcess);
	}
	
	//优先级调度
	private void prioritySchedule(){
		int maxPrority=10;
		for(int i=0;i<readyProcessList.size();i++){
			if(getProcessRegistry().get(readyProcessList.get(i)).getPriority()<=maxPrority){
				runningProcess=getProcessRegistry().get(readyProcessList.get(i)).getPid();
				readyProcessList.remove(runningProcess);
			}
		}
		try {
			Thread.sleep(1000*((int)(getProcessRegistry().get(runningProcess).getRunTime()/MCPUDriver.time)+1));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		unregisterProcess(runningProcess);
	}
	
//	//完成的进程，则从注册队列中将其剔除，然后检测内存使用情况，如果已经达到使用峰值，则调用MMemoryManager进行内存的整理，整理完成后检测阻塞队列是否为空，如不为空，则令其再次申请内存。
//	private void  checkMemoryState(double runningProcess){
//		double availableMemory=0;
//			double completedPid=runningProcess;
//			processRegistry.remove(runningProcess);
//			if(mmemoryManager.getUsedRate()<0.85){
//				availableMemory=mmemoryManager.cleanUp(completedPid);
//				if(availableMemory>0){
//					checkBlockList();
//				}
//			}
//	}
	
	//注册进程
	public boolean registerProcess(MProcess process){
		if(process.getPid()==0||process.getMemorySize()==0||process.getProcessPrority()==0){
			return false;
		}
		else{
			getProcessRegistry().put(process.getPid(), process);
			if(mmemoryManager.apply(process)&&mioManager.applyIO(process.getPid(), process.getIONeed())){
				mmemoryManager.distibuteMemory(process);
				mioManager.distributeIO(process.getPid(), process.getIONeed());
				readyProcessList.add(process.getPid());
			}
			else{
				blockProcessList.add(process.getPid());
			}
			return true;
		}
	}
	//注销进程
	public boolean unregisterProcess(double pid){
		getProcessRegistry().remove(pid);
		mioManager.releaseIO(pid);
		mmemoryManager.checkMemoryState();
		checkBlockList();
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
	//再次为阻塞队列中的进程请求内存和io资源
	private void checkBlockList(){
		if(blockProcessList.size()>0){
			MProcess blockedProcess=getProcessRegistry().get(blockProcessList.get(0));
			if(mmemoryManager.apply(blockedProcess)&&mioManager.applyIO(blockedProcess.getPid(), blockedProcess.getIONeed())){
				readyProcessList.add(blockedProcess.getPid());
				blockProcessList.remove(blockedProcess.getPid());
				mmemoryManager.distibuteMemory(blockedProcess);
				mioManager.distributeIO(blockedProcess.getPid(), blockedProcess.getIONeed());
			}
		}
	
		
	}
	public Map<Double, MProcess> getProcessRegistry() {
		return processRegistry;
	}
	
	
	
}
