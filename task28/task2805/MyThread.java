package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {

	private static final AtomicInteger priorityLvl = new AtomicInteger(MIN_PRIORITY);

	public MyThread() {
		if(priorityLvl.get() == MIN_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(priorityLvl.incrementAndGet());
			return;
		}
		if(priorityLvl.get() < MAX_PRIORITY) {
			this.setPriority(priorityLvl.get());
			priorityLvl.set(priorityLvl.incrementAndGet());
		}else if(priorityLvl.get() == MAX_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(MIN_PRIORITY);
		}
	}

	public MyThread(Runnable target) {
		super(target);
		if(priorityLvl.get() == MIN_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(priorityLvl.incrementAndGet());
			return;
		}
		if(priorityLvl.get() < MAX_PRIORITY) {
			this.setPriority(priorityLvl.get());
			priorityLvl.set(priorityLvl.incrementAndGet());
		}else if(priorityLvl.get() == MAX_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(MIN_PRIORITY);
		}


	}

	public MyThread(ThreadGroup group, Runnable target) {
		super(group, target);
		if(priorityLvl.get() == MIN_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(priorityLvl.incrementAndGet());
			return;
		}
		if(priorityLvl.get() < MAX_PRIORITY) {
			this.setPriority(Math.min(priorityLvl.get(), group.getMaxPriority()));
			priorityLvl.set(priorityLvl.incrementAndGet());
		}else if(priorityLvl.get() == MAX_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(MIN_PRIORITY);
		}
	}

	public MyThread(String name) {
		super(name);
		if(priorityLvl.get() == MIN_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(priorityLvl.incrementAndGet());
			return;
		}
		if(priorityLvl.get() < MAX_PRIORITY) {
			this.setPriority(priorityLvl.get());
			priorityLvl.set(priorityLvl.incrementAndGet());
		}else if(priorityLvl.get() == MAX_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(MIN_PRIORITY);
		}


	}

	public MyThread(ThreadGroup group, String name) {
		super(group, name);
		if(priorityLvl.get() == MIN_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(priorityLvl.incrementAndGet());
			return;
		}
		if(priorityLvl.get() < MAX_PRIORITY) {
			this.setPriority(Math.min(priorityLvl.get(), group.getMaxPriority()));
			priorityLvl.set(priorityLvl.incrementAndGet());
		}else if(priorityLvl.get() == MAX_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(MIN_PRIORITY);
		}

	}

	public MyThread(Runnable target, String name) {
		super(target, name);
		if(priorityLvl.get() == MIN_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(priorityLvl.incrementAndGet());
			return;
		}
		if(priorityLvl.get() < MAX_PRIORITY) {
			this.setPriority(priorityLvl.get());
			priorityLvl.set(priorityLvl.incrementAndGet());
		}else if(priorityLvl.get() == MAX_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(MIN_PRIORITY);
		}
	}

	public MyThread(ThreadGroup group, Runnable target, String name) {
		super(group, target, name);
		if(priorityLvl.get() == MIN_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(priorityLvl.incrementAndGet());
			return;
		}
		if(priorityLvl.get() < MAX_PRIORITY) {
			this.setPriority(Math.min(priorityLvl.get(), group.getMaxPriority()));
			priorityLvl.set(priorityLvl.incrementAndGet());
		}else if(priorityLvl.get() == MAX_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(MIN_PRIORITY);
		}

	}

	public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
		super(group, target, name, stackSize);
		if(priorityLvl.get() == MIN_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(priorityLvl.incrementAndGet());
			return;
		}
		if(priorityLvl.get() < MAX_PRIORITY) {
			this.setPriority(Math.min(priorityLvl.get(), group.getMaxPriority()));
			priorityLvl.set(priorityLvl.incrementAndGet());
		}else if(priorityLvl.get() == MAX_PRIORITY){
			this.setPriority(priorityLvl.get());
			priorityLvl.set(MIN_PRIORITY);
		}

	}

//	public MyThread(ThreadGroup group, Runnable target, String name, long stackSize, boolean inheritThreadLocals) {
//		super(group, target, name, stackSize, inheritThreadLocals);
//		if(priorityLvl.get() == MIN_PRIORITY){
//			this.setPriority(priorityLvl.get());
//			priorityLvl.set(priorityLvl.incrementAndGet());
//			return;
//		}
//		if(priorityLvl.get() < MAX_PRIORITY) {
//			this.setPriority(Math.min(priorityLvl.get(), group.getMaxPriority()));
//			priorityLvl.set(priorityLvl.incrementAndGet());
//		}else if(priorityLvl.get() == MAX_PRIORITY){
//			this.setPriority(priorityLvl.get());
//			priorityLvl.set(MIN_PRIORITY);
//		}
//
//	}
}
