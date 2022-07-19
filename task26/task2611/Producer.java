package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable{
	private ConcurrentHashMap<String, String> map;

	public Producer(ConcurrentHashMap<String, String> map) {
		this.map = map;
	}

	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		int key = 0;


		try {
			while(true){
				key++;
				map.put(String.valueOf(key), String.format("Some text for %s", key));

				Thread.sleep(500);
			}

		} catch(InterruptedException e) {
			System.out.println(String.format("[%s] thread was terminated", currentThread.getName()));
		}

	}
}
