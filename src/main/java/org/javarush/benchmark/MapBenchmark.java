package org.javarush.benchmark;

import org.javarush.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapBenchmark {
        // age > 30
        // salary
        // collect to map

    public static Map<Long, User> runFor(List<User> users){
        Map<Long, User> idToUser = new HashMap<>();

        for (User user : users) {
            if (user.getAge() > 30){
                user.setSalary( user.getBaseSalary() + (user.getSeniority() * 300) );

                idToUser.put(user.getId(), user);
            }
        }

        return idToUser;
    }

    public static Map<Long, User> runStream(List<User> users){
        return users.stream()
                .filter(user -> user.getAge() > 30)
                .peek(user -> user.setSalary( user.getBaseSalary() + (user.getSeniority() * 300) ))
                .collect(Collectors.toMap(User::getId, user -> user ));
    }

    public static Map<Long, User> runParallelStream(List<User> users){
        return users.parallelStream()
                .filter(user -> user.getAge() > 30)
                .peek(MapBenchmark::calculateSalary)
                .collect(Collectors.toConcurrentMap(User::getId, user -> user ));
    }

    public static void calculateSalary(User user){
        user.setSalary( user.getBaseSalary() + (user.getSeniority() * 300));
    }

    public static double measurePerf(Function<List<User>, Map<Long, User>> function, List<User> users){
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
