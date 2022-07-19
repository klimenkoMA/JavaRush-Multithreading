package com.javarush.task.task25.task2512;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/* 
Живем своим умом
*/

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        printE(e);
    }

    public void printE(Throwable e) {

        if(e.getCause() != null) {
            printE(e.getCause());
        }
        System.out.println(e);
    }



    public static void main(String[] args) {
        new Solution().uncaughtException(new Thread(), new Exception("ABC"
                , new RuntimeException("DEF", new IllegalAccessException("GHI"))));

    }
}
