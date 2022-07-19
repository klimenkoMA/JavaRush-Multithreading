package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

public class Producer implements Runnable {
	private TransferQueue<ShareItem> queue;

	public Producer(TransferQueue<ShareItem> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		int count = 1;

		while(true) {

			try {
				for(int i = count; i < 10; i++, count++) {
					System.out.format("Элемент 'ShareItem-%d' добавлен\n", i);
					queue.offer(new ShareItem("ShareItem-" + i, i));
					Thread.sleep(100);
					if(queue.hasWaitingConsumer()) {
						System.out.format("Consumer в ожидании!\n");
					}
				}
				return;
			} catch(InterruptedException e) {
				/**/

			}
		}
	}
}
