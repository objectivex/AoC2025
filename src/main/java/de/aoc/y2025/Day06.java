package de.aoc.y2025;

import java.util.Arrays;
import java.util.List;

public class Day06 {
    public static long partOne(String input) {
        var inputs =
                input.lines().map(l -> Arrays.asList(l.trim().split("\\s+"))).toList();

        long sum = 0;
        var x = inputs.getFirst().size();

        for (int i = 0; i < x; i++) {
            String apply = inputs.getLast().get(i);

            long internalSum = Long.parseLong(inputs.get(0).get(i));
            for (int i1 = 1; i1 < inputs.size()-1; i1++) {
                if (apply.equals("+")) {
                    internalSum += Long.parseLong(inputs.get(i1).get(i));
                } else if (apply.equals("*")) {
                    internalSum *= Long.parseLong(inputs.get(i1).get(i));;
                }

            }
            sum += internalSum;
        }
        return sum;
    }

    public static long partTwo(String input) {
        var lines = input.split("\n");

        return -1;
    }

}
