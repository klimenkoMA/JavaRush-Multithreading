package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Observable;
import java.util.Observer;

public class Waiter implements Observer {


	@Override
	public void update(Observable observable, Object arg) {
		StatisticManager manager = StatisticManager.getInstance();
		ConsoleHelper.writeMessage(arg + " was cooked by " + observable.toString());
	}
}
