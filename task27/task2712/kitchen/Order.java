package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {

	private final Tablet tablet;
	protected List<Dish> dishes;

	public Order(Tablet tablet) throws IOException {
		this.tablet = tablet;
		initDishes();
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	protected void initDishes() throws IOException {
		this.dishes = ConsoleHelper.getAllDishesForOrder();
	}

	public int getTotalCookingTime() {

		int result = 0;
		if(!isEmpty()) {
			for(Dish d : dishes
			) {
				result += d.getDuration();
			}
		}
		return result;
	}

	public boolean isEmpty() {
		return dishes.isEmpty();
	}

	public String toString() {

		return "You order: " + dishes.toString() +
				" of " + tablet + ", cooking time " +
				getTotalCookingTime() + "min";
	}
}
