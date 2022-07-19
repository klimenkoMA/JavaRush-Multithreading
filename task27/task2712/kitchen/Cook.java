package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {

	private String name;
	private boolean busy;
	private LinkedBlockingQueue<Order> queue;

	public Cook(String name) {
		this.name = name;
	}

	public void setQueue(LinkedBlockingQueue<Order> queue) {
		this.queue = queue;
	}

	public boolean isBusy() {
		return busy;
	}

	@Override
	public String toString() {
		return name;
	}


	public void startCookingOrder(Order order) throws InterruptedException {
		busy = true;
		ConsoleHelper.writeMessage("Start cooking - " + order);
		StatisticManager statisticManager = StatisticManager.getInstance();

		statisticManager.register(new CookedOrderEventDataRow(order.toString(),
				this.toString(), order.getTotalCookingTime() * 60, order.getDishes()));
		setChanged();
		notifyObservers(order);
		Thread.sleep(order.getTotalCookingTime() * 10L);
		busy = false;
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			if(!queue.isEmpty()) {

				if(!this.isBusy()) {
					try {
						this.startCookingOrder(queue.take());
					} catch(InterruptedException e) {
						/**/
					}
				}

			}
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {
				/**/
			}
		}
	}
}
