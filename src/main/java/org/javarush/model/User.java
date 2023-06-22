package org.javarush.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    private static long COUNTER;

    private final long id;
    private String username;
    private String email;
    private String password;
    private int age;
    private double baseSalary;
    private double salary;
    private int seniority;

    public User(String username, String email, String password, int age, double baseSalary, int seniority) {
        this.id = COUNTER++;
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
        this.baseSalary = baseSalary;
        this.seniority = seniority;
    }
}
