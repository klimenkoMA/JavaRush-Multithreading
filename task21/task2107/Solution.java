package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* 
Глубокое клонирование карты
*/

public class Solution implements Cloneable {
    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(!(o instanceof Solution))
            return false;
        Solution solution = (Solution) o;
        return Objects.equals(users, solution.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = (Solution) solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Solution sol = (Solution) super.clone();
        Map<String, User> map = new LinkedHashMap<>();
        for(Map.Entry<String, User> pair: users.entrySet()
            ) {
            User user = pair.getValue();
            map.put(pair.getKey(), user.clone());
        }
        sol.users = map;
        return sol;
    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User implements Cloneable{
        int age;
        String name;

        @Override
        public boolean equals(Object o) {
            if(this == o)
                return true;
            if(!(o instanceof User))
                return false;
            User user = (User) o;
            return age == user.age && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        protected User clone() {

            return new User(this.age, this.name);
        }
    }
}
