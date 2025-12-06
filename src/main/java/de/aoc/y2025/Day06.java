package de.aoc.y2025;

import java.util.ArrayList;
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
            for (int i1 = 1; i1 < inputs.size() - 1; i1++) {
                if (apply.equals("+")) {
                    internalSum += Long.parseLong(inputs.get(i1).get(i));
                } else if (apply.equals("*")) {
                    internalSum *= Long.parseLong(inputs.get(i1).get(i));
                    ;
                }

            }
            sum += internalSum;
        }
        return sum;
    }

    public static long partTwo(String input) {
        var lines = input.split("\n");
        var maxLength = Arrays.stream(lines).mapToInt(l -> l.length()).max().orElseThrow();

        char[][] grid = new char[lines.length][maxLength];
        for (int y = 0; y < lines.length; y++) {
            var line = lines[y];
            for (int x = 0; x < line.length(); x++) {
                grid[y][x] = line.charAt(x);
            }
        }

        print(grid);

        List<Long> numbers = new ArrayList<>();
        char operand = ' ';
        long sum = 0;

        for (int x = grid[0].length - 1; x >= 0; x--) {
            String number = "";
            for (int y = 0; y < grid.length - 1; y++) {
                if (Character.isDigit(grid[y][x])) {
                    number += grid[y][x];
                }
            }

            if (!number.isBlank()) {
                numbers.add(Long.parseLong(number));
            }
            var o = grid[grid.length - 1][x];
            if (o == '+') {
                System.out.println("+");
                System.out.println(numbers);
                var s = numbers.stream().reduce(0L, Long::sum);
                sum += s;
                numbers.clear();
            } else if (o == '*') {
                System.out.println("*");
                System.out.println(numbers);
                var s = numbers.stream().reduce(1L, (aLong, aLong2) -> aLong * aLong2);
                sum += s;
                numbers.clear();
            }

//            System.out.println(o);
        }

        return sum;
    }

    private static void print(char[][] grid) {
        for (char[] chars : grid) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

}
