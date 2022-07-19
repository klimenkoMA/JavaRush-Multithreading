package com.javarush.task.task29.task2909.user;

import java.util.concurrent.atomic.AtomicInteger;

public class UserHelper {
	private User userAnya = new User("Аня", "Смирнова", 10);
	private User userRoma = new User("Рома", "Виноградов", 30);

	public void printUsers() {
		userAnya.printInfo();
		userAnya.printAdditionalInfo();
		userRoma.printInfo();
		userRoma.printAdditionalInfo();
	}

	public int calculateAverageAge() {
		User userUra = new User("Юра", "Карп", 28);
		return (userAnya.getAge() + userRoma.getAge() + userUra.getAge()) / 3;
	}

	public int calculateRate(AtomicInteger base, int age, boolean hasWork, boolean hasHouse) {

		AtomicInteger clone = new AtomicInteger(base.intValue());

		clone.set(clone.get() + age / 100);
		clone.set((int) (clone.get() * (hasWork ? 1.1 : 0.9)));
		clone.set((int) (clone.get() * (hasHouse ? 1.1 : 0.9)));
		return clone.get();
	}

	public String getBossName(User user) {
		return user.getBoss();
	}
}