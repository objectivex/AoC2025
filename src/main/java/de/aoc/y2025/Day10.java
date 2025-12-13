package de.aoc.y2025;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 {
    public static long partOne(String input) {
        var lines = input.lines()
                .toList();

        long sum = 0;
        for (String line : lines) {


//            var line = lines.getFirst();
            var desired = parseDesired(line.substring(line.indexOf("[") + 1, line.indexOf("]")));

            List<Integer> toggles = parseToggles(line);
            sum+= calculateMin(toggles, 0, desired);
        }
        return sum;
//        int sum = 0;
//        for (int i = 0; i < toggles.size(); i++) {
//            var toggle = toggles.get(i);
//            sum = sum ^ toggle;
//            if (sum == desired) {
//                return i+1;
//            }
//        }
//
//        return -1;
    }

    private static long calculateMin(List<Integer> toggles, int current, int desired) {
        long min = 100000000000L;

        if (current == desired) {
            return 0;
        }

        for (int i = 0; i < toggles.size(); i++) {
            long m1 = 2L + calculateMin(toggles.subList(i + 1, toggles.size()), current ^ toggles.get(i), desired);
            long m2 = 1L+calculateMin(toggles.subList(i + 1, toggles.size()), current ^ toggles.get(i), desired);
            min = Math.min(min, Math.min(m1,m2));
        }


        return min;
    }

    private static List<Integer> parseToggles(String line) {
        var pattern = Pattern.compile("\\(([^)]+)\\)");
        Matcher m = pattern.matcher(line);
        List<Integer> list = new ArrayList<>();
        while (m.find()) {
            var s = Arrays.stream(m.group(1).split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
            int number = 0;
            for (Integer i : s) {
                if (i == 0) {
                    number = number | 1;
                } else {
                    number = number | 2 << (i - 1);
                }
            }

            list.add(number);

        }


        return list;
    }

    private static int parseDesired(String input) {
        var s = new StringBuffer(input.replace('.', '0').replace('#', '1')).reverse().toString();
        return Integer.parseInt(s, 2);
    }

    public static long partTwo(String input) {
        return -1;
    }

}
