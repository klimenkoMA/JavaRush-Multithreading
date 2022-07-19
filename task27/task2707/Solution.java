package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
*/

public class Solution {
	public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception {
		Thread firstThread = new Thread() {
			@Override
			public void run() {
				synchronized(o1) {
					//					while(true) {
					try {
						Object o3 = new Object();
						o3 = o1;
						Thread.sleep(500);
						synchronized(o2) {
							o3 = o2;
						}
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				//				}

			}
		};

		Thread secondThread = new Thread() {
			@Override
			public void run() {
				solution.someMethodWithSynchronizedBlocks(o1, o2);
			}
		};
		firstThread.setDaemon(true);
		secondThread.setDaemon(true);

		firstThread.start();
		secondThread.start();

		Thread.sleep(2000);

		//do something here
		return secondThread.getState() != Thread.State.BLOCKED;
	}

	public static void main(String[] args) throws Exception {
		final Solution solution = new Solution();
		final Object o1 = new Object();
		final Object o2 = new Object();

		System.out.println(isLockOrderNormal(solution, o1, o2));
	}

	public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
		synchronized(obj1) {
			synchronized(obj2) {
				System.out.println(obj1 + " " + obj2);
			}
		}
	}
}
