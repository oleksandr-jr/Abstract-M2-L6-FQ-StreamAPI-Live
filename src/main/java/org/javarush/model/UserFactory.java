package org.javarush.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserFactory {

    private final static int MAX_AGE = 65;
    private final static double MAX_BASE_SALARY = 10_000.0;
    private final static int MAX_SENIORITY = 10;

    public static List<User> createUsers(int amount){
        List<User> users = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            String username = "User" + i;
            String email = "User" + i + "@gmail.com";
            String password = "root";

            int age = random.nextInt(MAX_AGE) + 18;
            double baseSalary = random.nextDouble() * MAX_BASE_SALARY;
            int seniority = random.nextInt(MAX_SENIORITY) + 1;

            User user = new User(username, email, password, age, baseSalary, seniority);

            users.add(user);
        }

        return users;
    }
}
