package com.javarush.task.task26.task2603;

import java.util.Comparator;

/* 
Убежденному убеждать других не трудно
*/

public class Solution {

    public static class CustomizedComparator<T> implements Comparator<T>{

        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator... comparators) {
            this.comparators = comparators;
        }


        @Override
        public int compare(T o1, T o2) {
            int result = 0;

            for(Comparator comparator: comparators
            ) {
                int a = comparator.compare(o1, o2);
                if(a != 0){
                    result = a;
                    break;
                }
            }
            return  result;
        }
    }

    public static void main(String[] args) {

    }
}
