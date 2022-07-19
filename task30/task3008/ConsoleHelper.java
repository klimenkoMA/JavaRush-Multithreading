package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class ConsoleHelper {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void writeMessage(String message){
		System.out.println(message);
	}

	public static String readString() throws IOException {
		String result;
		try{
			return reader.readLine();

		} catch(IOException e) {
			writeMessage("Произошла ошибка при попытке ввода текста." +
					" Попробуйте еще раз.");
			readString();
			result = reader.readLine();
		}
		return result;
	}

	public static int readInt() throws IOException {
		try{
			return Integer.parseInt(Objects.requireNonNull(readString()));
		}catch(NumberFormatException | IOException e){
			writeMessage("Произошла ошибка при попытке ввода числа." +
					" Попробуйте еще раз.");
			readInt();
		}
		return Integer.parseInt(Objects.requireNonNull(readString()));
	}
}
