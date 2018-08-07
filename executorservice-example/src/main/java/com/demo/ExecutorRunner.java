package com.demo;

import java.util.List;
import java.util.concurrent.Callable;

public class ExecutorRunner implements Callable<Boolean> {

	List<String> partialList;
	
	public ExecutorRunner(List<String> partialList) {
		super();
		this.partialList = partialList;
	}

	@Override
	public Boolean call() {
		Boolean flag = false;

		try {
				//do business logic here
			System.out.println("processing business logic.....");
			flag = true;

		} catch (Exception e) {		
			e.printStackTrace();
			
		} 
		return flag;
	}

}
