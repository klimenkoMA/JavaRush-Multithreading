package com.javarush.task.task25.task2502;

import java.util.ArrayList;
import java.util.List;

/* 
Машину
на СТО
не
повезем!
*/

public class Solution {
	public static void main(String[] args) {
        Car car = new Car();
	}

	public static enum Wheel {
		FRONT_LEFT,
		FRONT_RIGHT,
		BACK_LEFT,
		BACK_RIGHT
	}

	public static class Car {
		protected List<Wheel> wheels;

		public Car(){
			//init wheels here
			List<Wheel> list = new ArrayList<>();

			Wheel[] enumWheel = Wheel.values();
			String[] wheel = loadWheelNamesFromDB();
			if(wheel.length != 4){
			    throw  new ArrayIndexOutOfBoundsException();
            }

			for(String s : wheel
			) {
			    try {
                    Wheel wh = Wheel.valueOf(s);
                }catch(IllegalArgumentException e){
			        e.printStackTrace();
                }
				for(Wheel enums : enumWheel
				) {
					if(s.equals(enums.toString())) {
						list.add(enums);
					}
				}
			}
			wheels = list;
			wheels.forEach(System.out::println);
		}

		protected String[] loadWheelNamesFromDB() {
			//this method returns mock data
			return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT", "dfsdf"};
		}
	}
}
