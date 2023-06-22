package org.javarush.benchmark;

import org.javarush.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListBenchmark {
        // age > 30
        // salary
        // collect to map

    public static List<User> runFor(List<User> users){
        List<User> idToUser = new ArrayList<>();

        for (User user : users) {
            if (user.getAge() > 30){
                user.setSalary( user.getBaseSalary() + (user.getSeniority() * 300) );

                idToUser.add(user);
            }
        }

        return idToUser;
    }

    public static List<User> runStream(List<User> users){
        return users.stream()
                .filter(user -> user.getAge() > 30)
                .peek(user -> user.setSalary( user.getBaseSalary() + (user.getSeniority() * 300) ))
                .collect(Collectors.toList());
    }

    public static List<User> runParallelStream(List<User> users){
        return users.parallelStream()
                .filter(user -> user.getAge() > 30)
                .peek(user -> user.setSalary( user.getBaseSalary() + (user.getSeniority() * 300) ))
                .collect(Collectors.toList());
    }

    public static double measurePerf(Function<List<User>, List<User>> function, List<User> users){
        int numOfExecution = 100;
        double totalTime = 0.0;

        for (int i = 0; i < numOfExecution; i++) {
            double startTime = System.nanoTime();

            function.apply(users);

            double duration = (System.nanoTime() - startTime) / 1_000_000;
            totalTime += duration;
        }

        return totalTime / numOfExecution;
    }

}
