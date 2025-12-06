package de.aoc.y2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 {
    public static long partOne(String input) {
        var inputs = input.lines()
                .map(l -> Arrays.asList(l.trim().split("\\s+")))
                .toList();

        long sum = 0;
        var inputSize = inputs.getFirst().size();

        for (int listIndex = 0; listIndex < inputSize; listIndex++) {
            String operator = inputs.getLast().get(listIndex);

            long internalSum = Long.parseLong(inputs.getFirst().get(listIndex));
            for (int rowIndex = 1; rowIndex < inputs.size() - 1; rowIndex++) {
                if (operator.equals("+")) {
                    internalSum += Long.parseLong(inputs.get(rowIndex).get(listIndex));
                } else if (operator.equals("*")) {
                    internalSum *= Long.parseLong(inputs.get(rowIndex).get(listIndex));
                }

            }
            sum += internalSum;
        }
        return sum;
    }

    public static long partTwo(String input) {
        var lines = input.split("\n");
        var maxLength = Arrays.stream(lines).mapToInt(String::length).max().orElseThrow();

        char[][] grid = new char[lines.length][maxLength];
        for (int y = 0; y < lines.length; y++) {
            var line = lines[y];
            for (int x = 0; x < line.length(); x++) {
                grid[y][x] = line.charAt(x);
            }
        }

        List<Long> numbers = new ArrayList<>();
        long sum = 0;

        for (int x = grid[0].length - 1; x >= 0; x--) {
            long number = 0;
            for (int y = 0; y < grid.length - 1; y++) {
                if (Character.isDigit(grid[y][x])) {
                    number = 10 * number + Long.parseLong("" + grid[y][x]);
                }
            }

            if (number != 0) {
                numbers.add(number);
            }

            var operator = grid[grid.length - 1][x];
            if (operator == '+') {
                var localSum = numbers.stream().reduce(0L, Long::sum);
                sum += localSum;
                numbers.clear();
            } else if (operator == '*') {
                var localSum = numbers.stream().reduce(1L, (l1, l2) -> l1 * l2);
                sum += localSum;
                numbers.clear();
            }
        }

        return sum;
    }

}
