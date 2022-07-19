package com.javarush.task.task29.task2907;

import java.math.BigDecimal;
import java.math.MathContext;

/* 
Этот странный BigDecimal
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getValue(1.1d, 1.2d));
    }

    public static BigDecimal getValue(double v1, double v2) {
        BigDecimal d1 = new BigDecimal("" + v1);
        BigDecimal d2 = new BigDecimal("" + v2);
        d1 = d1.add(d2);
        return d1;
    }
}
