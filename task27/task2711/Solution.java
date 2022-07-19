package com.javarush.task.task27.task2711;

import java.util.concurrent.CountDownLatch;

/* 
CountDownLatch
*/

public class Solution {


	CountDownLatch latch = new CountDownLatch(1);

	public static void main(String[] args) {

	}

	public void someMethod() throws InterruptedException {
		//        synchronized (lock) {
		//            while (isWaitingForValue) {
		//                lock.wait();
		//            }
		//
		//            retrieveValue();
		//
		//            isWaitingForValue = false;
		//            lock.notify();
		//        }
		
			latch.await();

				latch.countDown();


		retrieveValue();
	}

	void retrieveValue() {
		System.out.println("Value retrieved.");
	}
}
