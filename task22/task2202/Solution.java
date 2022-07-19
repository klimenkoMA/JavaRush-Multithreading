package com.javarush.task.task22.task2202;

/* 
Найти
подстроку
*/

public class Solution {
	public static void main(String[] args) {
		System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
	}

	public static String getPartOfString(String string) {
		try {
			String[] spl = string.split(" ");
			StringBuilder builder = new StringBuilder();
			if(spl.length < 4) {
			    throw new TooShortStringException();
			} else {
				builder.append(spl[1]).append(" ")
						.append(spl[2]).append(" ")
						.append(spl[3]).append(" ")
						.append(spl[4]);
				return builder.toString();
			}
		} catch(RuntimeException e) {
			throw new TooShortStringException();
		}
	}

	public static class TooShortStringException extends RuntimeException {

	}
}
