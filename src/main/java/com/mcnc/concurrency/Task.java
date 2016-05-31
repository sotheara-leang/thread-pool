package com.mcnc.concurrency;

public class Task implements Runnable {

	private String id;
	
	public Task(String id) {
		this.id = id;
	}
	
	public void run() {
		System.out.println(String.format("THREAD[%s]\tSTART\tTASK[%s]", Thread.currentThread().getName(), id));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(String.format("THREAD[%s]\tFINISH\tTASK[%s]", Thread.currentThread().getName(), id));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
