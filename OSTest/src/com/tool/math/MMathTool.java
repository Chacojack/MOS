package com.tool.math;

public class MMathTool {
	
	public static int sum(int[] array){
		int sum = 0;
		for(int i=0;i<array.length;i++){
			sum+=array[i];
		}
		return sum;
	}
	
	public static double sum(double[] array){
		double sum = 0;
		for(int i=0;i<array.length;i++){
			sum+=array[i];
		}
		return sum;
	}

}
