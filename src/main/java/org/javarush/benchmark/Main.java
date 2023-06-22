package org.javarush.benchmark;


import org.javarush.model.User;
import org.javarush.model.UserFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = UserFactory.createUsers(1_000_000);

        System.out.printf("| %-7s | %-10s | %-10s | %10s |%n", "Amount", "For", "Stream", "Parallel");
        System.out.println("--------------------------------------------------");
        runListBenchmark(1_000);
        runListBenchmark(10_000);
        runListBenchmark(100_000);
        runListBenchmark(500_000);
        runListBenchmark(1_000_000);

    }

    public static void runBenchmark(int amount){
        List<User> users = UserFactory.createUsers(amount);

        System.out.printf("| %-7d | %10.5f | %10.5f | %10.5f |",
                amount,
                MapBenchmark.measurePerf(MapBenchmark::runFor, users),
                MapBenchmark.measurePerf(MapBenchmark::runStream, users),
                MapBenchmark.measurePerf(MapBenchmark::runParallelStream, users)
        );

        System.out.println();
    }

    public static void runListBenchmark(int amount){
        List<User> users = UserFactory.createUsers(amount);

        System.out.printf("| %-7d | %10.5f | %10.5f | %10.5f |",
                amount,
                ListBenchmark.measurePerf(ListBenchmark::runFor, users),
                ListBenchmark.measurePerf(ListBenchmark::runStream, users),
                ListBenchmark.measurePerf(ListBenchmark::runParallelStream, users)
        );

        System.out.println();
    }
}