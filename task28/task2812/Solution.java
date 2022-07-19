package com.javarush.task.task28.task2812;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

/* 
ShutdownNow!
*/

public class Solution {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 10; i++) {
            final int localId = i;
             executor.submit(new Runnable() {
                public void run() {
                    doExpensiveOperation(localId);
                }
            });

        }

        List<Runnable> list = executor.shutdownNow();
        for(Runnable run:list
            ) {
            System.out.println(run.toString());
        }


    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);
    }
}
