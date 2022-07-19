package com.javarush.task.task23.task2305;

/* 
Inner
*/

public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {

        Solution sol3 = new Solution();
        Solution sol4 = new Solution();
        InnerClass sol = sol3.new InnerClass();
        InnerClass sol2 = sol3.new InnerClass();
        InnerClass sol6 = sol4.new InnerClass();
        InnerClass sol7 = sol4.new InnerClass();
        sol3.innerClasses[0] = sol;
        sol3.innerClasses[1] = sol2;
        sol4.innerClasses[0] = sol6;
        sol4.innerClasses[1] = sol7;

        Solution[] sols = new Solution[2];
        sols[0] = sol3;
        sols[1] = sol4;


        return sols;
    }

    public static void main(String[] args) {

    }
}
