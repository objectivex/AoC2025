package de.aoc.y2025;

import java.util.*;

public class Day03 {

    public static long partOne(String input) {
        var lines = input.lines().toList();

        long sum = 0;
        for (String line : lines) {
            sum += getJolt(line, 2);
        }
        return sum;
    }

    private static long getJolt(String line, int size) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            numbers.add(Integer.parseInt(line.substring(i, i + 1)));
        }

        int startIndex = 0;
        long max = 0;
        for (int i = size - 1; i >= 0; i--) {
            var head = numbers.subList(startIndex, numbers.size()  - i);
            int localMax = head.stream().mapToInt(n -> n).max().orElseThrow();
            startIndex = startIndex+head.indexOf(localMax) + 1;
            max = 10 * max + localMax;
        }
        return max;
    }

    private static long getJolt(List<Integer> integers, long current, int remaining) {
        if (remaining == 0) {
            return current;
        }
        long max = 0;
        for (int i1 = 0; i1 < integers.size(); i1++) {
            long returnValue = current * 10 + integers.get(i1);

            max = Math.max(max, getJolt(integers.subList(i1 + 1, integers.size()), returnValue, remaining - 1));
        }

        return max;
    }

    public static long partTwo(String input) {
        var lines = input.lines().toList();

        long sum = 0;
        for (String line : lines) {
            sum += getJolt(line, 12);
        }
        return sum;
    }

}
