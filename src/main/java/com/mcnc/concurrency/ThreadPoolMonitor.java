package com.mcnc.concurrency;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolMonitor extends Thread {

	private ThreadPoolExecutor executor;

	public ThreadPoolMonitor(ThreadPoolExecutor executor) {
		this.executor = executor;
	}

	public void run() {
		long startingTime = System.currentTimeMillis();
		try {
			while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
				printStatus();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		printStatus();
		displayProcessingTime(System.currentTimeMillis() - startingTime);
	}
	
	private void printStatus() {
		System.out.println(String.format(
				"[ThreadPoolMonitor] [%d/%d] ACTIVE: %d, COMPLETED: %d, TASK: %d, MEM: %dKB",
				executor.getPoolSize(),
				executor.getCorePoolSize(),
				executor.getActiveCount(),
				executor.getCompletedTaskCount(),
				executor.getTaskCount(),
				displayMemoryUsage()));
	}
	
	private static void displayProcessingTime(long milliseconds) {
		long second = (milliseconds / 1000) % 60;
		long minute = (milliseconds / (1000 * 60)) % 60;
		long hour = (milliseconds / (1000 * 60 * 60)) % 24;
		System.out.println(String.format("Processing Time: %02dH:%02dMN:%02dS:%dSS", hour, minute, second, milliseconds));
	}
	
	private static long displayMemoryUsage() {
		Runtime rt = Runtime.getRuntime();
	    return (rt.totalMemory() - rt.freeMemory()) / 1024;
	}
}
