package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car {
	static public final int TRUCK = 0;
	static public final int SEDAN = 1;
	static public final int CABRIOLET = 2;
	public double summerFuelConsumption;
	public double winterFuelConsumption;
	public double winterWarmingUp;
	double fuel;
	private int type;

	private boolean driverAvailable;
	private int numberOfPassengers;

	public Car(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	protected Car(int type, int numberOfPassengers) {
		this.type = type;
		this.numberOfPassengers = numberOfPassengers;
	}

	public static Car create(int type, int numberOfPassengers) {
		Car car;
		switch(type) {
			case 0:
				car = new Truck(numberOfPassengers);
				break;
			case 1:
				car = new Sedan(numberOfPassengers);
				break;
			case 2:
				car = new Cabriolet(numberOfPassengers);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + type);
		}
		return car;
	}

	private boolean canPassengersBeTransferred() {
		return isDriverAvailable() && fuel > 0;
	}

	public int getNumberOfPassengersCanBeTransferred() {
		if(!canPassengersBeTransferred()) {
			return 0;
		} else {
			return numberOfPassengers;
		}
	}

	public boolean isSummer(Date date, Date summerStart, Date summerEnd) {
		return date.after(summerStart) && date.before(summerEnd);
	}

	public double getWinterConsumption(int length) {
		return length * winterFuelConsumption + winterWarmingUp;
	}

	public double getSummerConsumption(int length) {
		return length * summerFuelConsumption;
	}

	public void fill(double numberOfLiters) throws Exception {
		if(numberOfLiters < 0) {
			throw new Exception();
		} else {
			fuel += numberOfLiters;
		}
	}

	public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
		double consumption;
		if(isSummer(date, SummerStart, SummerEnd)) {
			consumption = getSummerConsumption(length);
		} else {
			consumption = getWinterConsumption(length);
		}
		return consumption;
	}


	public boolean isDriverAvailable() {
		return driverAvailable;
	}

	public void setDriverAvailable(boolean driverAvailable) {
		this.driverAvailable = driverAvailable;
	}

	public void startMoving() {
		if(numberOfPassengers > 0) {
			fastenPassengersBelts();
		}
		fastenDriverBelt();
	}

	public void fastenPassengersBelts() {
	}

	public void fastenDriverBelt() {
	}

	public abstract int getMaxSpeed();
}