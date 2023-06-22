package org.javarush.consumer;

import org.javarush.model.User;
import org.javarush.model.UserFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsumersDemo {
    public static void main(String[] args) {
        List<User> usersList = UserFactory.createUsers(1000);

        Map<Integer, List<User>> collect = usersList.stream().collect(Collectors.groupingBy(User::getSeniority));

        collect.forEach((key, users) -> {
                    System.out.println(key);
                    users.forEach(System.out::println);
                }
        );
    }
}
