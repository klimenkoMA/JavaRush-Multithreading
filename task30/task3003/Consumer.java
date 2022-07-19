package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

public class Consumer implements Runnable {
	private TransferQueue<ShareItem> queue;

	public Consumer(TransferQueue<ShareItem> queue) {
		this.queue = queue;
	}


	@Override
	public void run() {
		//		int count = 1;
		try {
			Thread.sleep(450);
		} catch(InterruptedException e) {
			/**/

		}
		while(!Thread.currentThread().isInterrupted()) {

			try {
				System.out.format("Processing %s\n", queue.take());

				Thread.sleep(1);

			} catch(InterruptedException e) {
				/**/
				return;
			}
		}


	}
}
