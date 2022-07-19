package com.javarush.task.task26.task2601;

import java.util.Arrays;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

	public static void main(String[] args) {

//		Integer[] a = new Integer[]{13, 8, 15, 5, 18, 6, 14, 11, 9};
//		sort(a);
//		for(Integer b : a
//		) {
//			System.out.print(b + ", ");
//		}

	}

	public static Integer[] sort(Integer[] array) {
		//implement logic here
		int median;


		Arrays.sort(array);
		if(array.length % 2 == 0) {
			median = (array[array.length / 2 - 1] + array[array.length / 2]) / 2;
		} else {
			median = array[array.length / 2];
		}

		Arrays.sort(array, ((o1, o2) -> Math.abs(median - o1) - Math.abs(median - o2)));

		return array;
	}
}
