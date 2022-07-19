package com.javarush.task.task28.task2803;

import java.util.concurrent.ThreadLocalRandom;

/* 
ThreadLocalRandom
*/

public class Solution {
    public static int getRandomIntegerBetweenNumbers(int from, int to) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        return random.nextInt(from, to);
    }

    public static double getRandomDouble() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextDouble();
    }

    public static long getRandomLongBetween0AndN(long n) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextLong(n);
    }

    public static void main(String[] args) {
    }
}
