package com.javarush.task.task30.task3001;

import java.math.BigInteger;
import java.util.Locale;

/* 
Конвертер систем счислений
*/

public class Solution {
	public static void main(String[] args) {

		Number number = new Number(NumberSystemType._10, "6");
		Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
		System.out.println(result);    //expected 110

		number = new Number(NumberSystemType._10, "60");
		result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
		System.out.println(result);    //expected 3337

		number = new Number(NumberSystemType._10, "60");
		result = convertNumberToOtherNumberSystem(number, NumberSystemType._3);
		System.out.println(result);    //expected 3337

		number = new Number(NumberSystemType._16, "6df");
		result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
		System.out.println(result);    //expected 3337

		number = new Number(NumberSystemType._16, "abcdefabcdef");
		result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
		System.out.println(result);    //expected abcdefabcdef
	}

	public static Number convertNumberToOtherNumberSystem(Number number, NumberSystem expectedNumberSystem) {
		//напишите тут ваш код
		BigInteger nmbr = new BigInteger(number.getDigit(),number.getNumberSystem().getNumberSystemIntValue());
		if(isLegalNumber(number)){
			return new Number(expectedNumberSystem, nmbr.toString(expectedNumberSystem.getNumberSystemIntValue()));
		}else{
			return null;
		}
	}


	public static boolean isLegalNumber(Number number) {

		switch((NumberSystemType) number.getNumberSystem()) {

			case _2:
				if(number.getDigit().matches("[0-1]*")) {
					return true;
				}
				throw new NumberFormatException();
			case _3:
				if(number.getDigit().matches("[0-2]*")) {
					return true;
				}
				throw new NumberFormatException();
			case _4:
				if(number.getDigit().matches("[0-3]*")) {
					return true;
				}
				throw new NumberFormatException();
			case _5:
				if(number.getDigit().matches("[0-4]*")) {
					return true;
				}
				throw new NumberFormatException();
			case _6:
				if(number.getDigit().matches("[0-5]*")) {
					return true;
				}
				throw new NumberFormatException();
			case _7:
				if(number.getDigit().matches("[0-6]*")) {
					return true;
				}
				throw new NumberFormatException();
			case _8:
				if(number.getDigit().matches("[0-7]*")) {
					return true;
				}
				throw new NumberFormatException();
			case _9:
				if(number.getDigit().matches("[0-8]*")) {
					return true;
				}
				throw new NumberFormatException();
			case _10:
				if(number.getDigit().matches("[0-9]*")) {
					return true;
				}
				throw new NumberFormatException();
			case _12:
				if(number.getDigit().toLowerCase(Locale.ROOT).matches("[0-9a-b]*")) {
					return true;
				}
				throw new NumberFormatException();
			case _16:
				if(number.getDigit().toLowerCase(Locale.ROOT).matches("[0-9a-f]*")) {
					return true;
				}
				throw new NumberFormatException();
			default:
				return false;
		}
	}
}
