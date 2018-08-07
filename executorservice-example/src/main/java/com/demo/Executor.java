package com.demo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Executor {
	
	public int execute(List<String> toBeProcessedList, int THREAD_SIZE, int BATCH_SIZE) {		
		
		int dataCount = toBeProcessedList.size();
		int subListFromIdx = 0, subListToIdx = BATCH_SIZE;
		int bundleCount = dataCount/BATCH_SIZE;
		int remainderData = dataCount % BATCH_SIZE;
		if(remainderData != 0) {
			bundleCount++;
		}
		if(bundleCount == 1 && remainderData != 0) {
			subListToIdx = remainderData;
		}
		
		int firstLoop = 0, secondLoop = 0;
		firstLoop = bundleCount/THREAD_SIZE;
		int remainderRec = bundleCount % THREAD_SIZE;
		if(remainderRec != 0) {
			firstLoop++;
		}
		
		try {
		for (int i = 0; i < firstLoop; i++) {
			List<Future<Boolean>> futLst = new ArrayList<Future<Boolean>>();
			ExecutorService executor;
			if((i == (firstLoop-1)) && (remainderRec != 0)) {
				executor = Executors.newFixedThreadPool(remainderRec);
				secondLoop = remainderRec;
			}
			else {
				executor = Executors.newFixedThreadPool(THREAD_SIZE);
				secondLoop = THREAD_SIZE;
			}
			
			for (int j = 0; j < secondLoop; j++) {			
				
				List<String> partialList = toBeProcessedList.subList(subListFromIdx, subListToIdx);
				
				Callable<Boolean> worker = new ExecutorRunner(partialList);
				Future<Boolean> result = executor.submit(worker);
				futLst.add(result);
				subListFromIdx = subListToIdx;
				if((i == (firstLoop-1)) && (j==(secondLoop-2))) {
					subListToIdx = subListToIdx + remainderData;
				} else {
					subListToIdx = subListToIdx + BATCH_SIZE;
				}				
			}
			 
			
	        for (Future<Boolean> future : futLst) {
	            try {
	                System.out.println(future.get().booleanValue());
	            } catch (InterruptedException | ExecutionException e) {
	                e.printStackTrace();
	            }
	        }
	     
			executor.shutdown();
			
			while (!executor.isTerminated()) {
				 System.out.println("Waiting for all threads from a bundle to finish");
			}
			System.out.println("Finished one bundle threads");
		
		}
		
		System.out.println("Finished executing all bundles of threads");
		
		} catch(Exception e) {
			 e.printStackTrace();
		}
		return dataCount;
				
	} 
}
