package com.javarush.task.task21.task2108;

/* 
Клонирование растений
*/

import java.util.Arrays;
import java.util.Objects;

public class Solution {
    public static void main(String[] args) {
        Tree tree = new Tree("willow", new String[]{"s1", "s2", "s3", "s4"});
        Tree clone = null;
        try {
            clone = (Tree) tree.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(tree);
        System.out.println(clone);

        System.out.println(Arrays.toString(tree.branches));
        System.out.println(Arrays.toString(clone.branches));
    }

    public static class Plant {
        private String name;

        @Override
        public boolean equals(Object o) {
            if(this == o)
                return true;
            if(!(o instanceof Plant))
                return false;
            Plant plant = (Plant) o;
            return Objects.equals(name, plant.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        public Plant(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Tree extends Plant implements Cloneable{
        private String[] branches;

        public Tree(String name, String[] branches) {
            super(name);
            this.branches = branches;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o)
                return true;
            if(!(o instanceof Tree))
                return false;
            if(!super.equals(o))
                return false;
            Tree tree = (Tree) o;
            return Arrays.equals(branches, tree.branches);
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + Arrays.hashCode(branches);
            return result;
        }

        public String[] getBranches() {
            return branches;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            String[] br = new String[branches.length];
            System.arraycopy(branches, 0, br, 0, br.length);
            Tree tree = new Tree(super.getName(), br);
            tree.branches = br;
            return tree;
        }
    }
}
