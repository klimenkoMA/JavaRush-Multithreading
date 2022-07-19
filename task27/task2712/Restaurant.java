package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
	private static final int ORDER_CREATING_INTERVAL = 100;
	private final static LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>(200);

	public static void main(String[] args) throws InterruptedException {

		Cook cook = new Cook("Amigo");
		cook.setQueue(ORDER_QUEUE);

		Thread thread1 = new Thread(cook);
		thread1.start();

		Cook cook2 = new Cook("Deadpool");
		cook2.setQueue(ORDER_QUEUE);
		Thread thread2 = new Thread(cook);
		thread2.start();

		List<Tablet> tablets = new ArrayList<>();
		Waiter waiter = new Waiter();


		for(int i = 0; i < 5; i++) {
			Tablet tablet = new Tablet(i);
			tablets.add(tablet);

			tablet.setQueue(ORDER_QUEUE);
			cook.addObserver(waiter);
			cook2.addObserver(waiter);
		}

		Thread task = new Thread(
				new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
		task.start();

		Thread.sleep(1000);
		task.interrupt();
		thread1.interrupt();
		thread2.interrupt();

		DirectorTablet directorTablet = new DirectorTablet();
		directorTablet.printAdvertisementProfit();
		directorTablet.printCookWorkloading();
		directorTablet.printActiveVideoSet();
		directorTablet.printArchivedVideoSet();
	}
}
