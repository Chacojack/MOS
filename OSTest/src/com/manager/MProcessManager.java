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
	private MMemoryManager mmemoryManager=MMemoryManager.getMemoryManager();//����ڴ����ʵ��
	private MIOManager mioManager=MIOManager.getIOManager();
	
	private MProcessManager(){
		this.start();
	}
	public static MProcessManager getProcessManager(){
		return mprocessManager;
	}
	//����ִ�д����
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
	//ʱ��Ƭ��ת����
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
	
	//����ҵ����
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
	//�Ƚ��ȳ�
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
	
	//���ȼ�����
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
	
//	//��ɵĽ��̣����ע������н����޳���Ȼ�����ڴ�ʹ�����������Ѿ��ﵽʹ�÷�ֵ�������MMemoryManager�����ڴ������������ɺ������������Ƿ�Ϊ�գ��粻Ϊ�գ��������ٴ������ڴ档
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
	
	//ע�����
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
	//ע������
	public boolean unregisterProcess(double pid){
		getProcessRegistry().remove(pid);
		mioManager.releaseIO(pid);
		mmemoryManager.checkMemoryState();
		checkBlockList();
		return true;               
	}
	//���ý��̵��Ȳ���
	public boolean setManagerType(ManagerType type){
		managerType=type;
		return true;
	}
	//��ý��̵��Ȳ���
	public ManagerType getManagerType(){
		return  managerType;
	}
	//�ٴ�Ϊ���������еĽ��������ڴ��io��Դ
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
