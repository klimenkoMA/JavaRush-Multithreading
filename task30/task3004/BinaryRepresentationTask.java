package com.javarush.task.task30.task3004;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {

	private int x;

	public BinaryRepresentationTask(int x) {
		this.x = x;
	}

	@Override
	protected String compute() {
		int x1 = x;

		BinaryRepresentationTask a = new BinaryRepresentationTask( x1 % 2);
		a.fork();

		BinaryRepresentationTask b = new BinaryRepresentationTask( x1 / 2);


		String result = String.valueOf(a.x);
		if (b.x > 0) {
			b = new BinaryRepresentationTask(x1/2);
			b.fork();
			return b.join() + result;
		}
		return result;
	}
}
