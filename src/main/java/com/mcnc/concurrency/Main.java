package com.mcnc.concurrency;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
	
	private static ThreadPoolExecutor executor; 
	
	public static void main(String[] args) throws InterruptedException {
		final int NUM_TASK = 1000;
		final int NUM_MAX_THREAD = 100;
		
		executor = new ThreadPoolExecutor(NUM_MAX_THREAD, NUM_MAX_THREAD, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		
		ThreadPoolMonitor threadPoolMonitor = new ThreadPoolMonitor(executor);
		threadPoolMonitor.start();
		
		for (int i = 0 ; i < NUM_TASK; i++) {
			executor.submit(new Task("Task_" + (i + 1)));
		}
		executor.shutdown();
	}
}
