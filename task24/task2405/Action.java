package com.javarush.task.task24.task2405;

public interface Action {
    void someAction();
}

/**
 * 5
 * 4
 * 3
 * 2
 * 1
 * class FirstClass, method someAction
 * class SecondClass, method someAction
 * Specific action for anonymous SecondClass, param = 0
 * Count of created Action objects is 2
 * class SecondClass, method someAction
 * Specific action for anonymous SecondClass, param = -1
 * Count of created Action objects is 3
 */