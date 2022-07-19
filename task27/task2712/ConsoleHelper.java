package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ConsoleHelper {

	private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

	public static void writeMessage(String message) {
		System.out.println(message);
	}

	public static String readString() throws IOException {
		return READER.readLine();
	}

	public static List<Dish> getAllDishesForOrder() throws IOException {
		writeMessage("Выберите блюдо: " + Dish.allDishesToString());
		String order;
		List<Dish> dishes = new ArrayList<>();
		while(!(order = READER.readLine()).equals("exit")) {
			switch(order.toLowerCase(Locale.ROOT)) {
				case "fish":
					dishes.add(Dish.FISH);
					break;
				case "steak":
					dishes.add(Dish.STEAK);
					break;
				case "soup":
					dishes.add(Dish.SOUP);
					break;
				case "juice":
					dishes.add(Dish.JUICE);
					break;
				case "water":
					dishes.add(Dish.WATER);
					break;
				default:
					writeMessage("Такого блюда нет, продолжите пожалуйста заказ.");
			}
		}

		return dishes;
	}
}
