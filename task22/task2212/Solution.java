package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
	public static boolean checkTelNumber(String telNumber) {

		if(telNumber == null) {
			return false;
		}
		if(telNumber.startsWith("+")) {
			if(telNumber.matches("(^\\+)(\\d{0,12})")) {
				return true;
			} else if(telNumber.matches("(^\\+)(\\d{2}\\(\\d{3}\\)\\d{7})")) {
				return true;
			} else
				return telNumber.matches("(^\\+)(\\d{8})(-)(\\d{2})(-)(\\d{2})");
		} else
			//		return telNumber.matches("(^\\+)(\\d{0,12})");
			//		return telNumber.matches("(^\\+)(\\d{2}\\(\\d{3}\\)\\d{7})");
			//		return telNumber.matches("(^\\+)(\\d{8})(-)(\\d{2})(-)(\\d{2})");
			return telNumber.matches("(\\d{6})(-)(\\d{4})");
	}


	/**
	 * +380501234567 - true
	 * +38(050)1234567 - true
	 * +38050123-45-67 - true
	 * 050123-4567 - true
	 * +38)050(1234567 - false
	 * +38(050)1-23-45-6-7 - false
	 * 050ххх4567 - false
	 * 050123456 - false
	 * (0)501234567 - false
	 */

	public static void main(String[] args) throws IOException {
		String fileName = "q";
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String s;
			while((s = reader.readLine()) != null) {
				System.out.println(checkTelNumber(s));
			}
		}

	}
}
