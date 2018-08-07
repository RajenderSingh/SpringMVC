package com.demo;

import java.util.ArrayList;
import java.util.List;

public class MainApp {

	public static void main(String[] args) {
		final int THREAD_SIZE = 10;
		final int BATCH_SIZE = 5;
		List<String> toBeProcessedList = new ArrayList<String>();
		for(int i=1; i<=20; i++) {
			toBeProcessedList.add("string-"+i);
		}
		
		new Executor().execute(toBeProcessedList, THREAD_SIZE, BATCH_SIZE);

	}

}
