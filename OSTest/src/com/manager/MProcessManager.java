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
	private MMemoryManager mmemoryManager=MMemoryManager.getMemoryManager();//����ڴ����ʵ��
	
	private MProcessManager(){}
	public static MProcessManager getProcessManager(){
		return mprocessManager;
	}
	//����ִ�д����
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
	//�Ƚ��ȳ�����
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
	//����ҵ����
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
	//ʱ��Ƭ��ת
	private void timeSliceRound(){
		
	}
	
	//�ж����н����ڸ�ʱ��Ƭ����֮���״̬�����Ѿ���ɣ����ע������н����޳���Ȼ�����ڴ�ʹ�����������Ѿ��ﵽʹ�÷�ֵ�������MMemoryManager�����ڴ������������ɺ������������Ƿ�Ϊ�գ��粻Ϊ�գ��������ٴ������ڴ档
	private void  checkProcessState(double runningProcess){
		if(processRegistry.get(runningProcess).getRunTime()<=0){
			
		}
		else{
			readyProcessList.add(0, runningProcess);
		}
	}
	
	//ע�����
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
	//ע������
	public boolean unregisterProcess(double pid){
		processRegistry.remove(pid);
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
	
	
	
	
}
