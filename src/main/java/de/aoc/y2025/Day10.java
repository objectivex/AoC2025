package de.aoc.y2025;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 {
    static class Line {
        Integer desiredState;
        Integer currentState;

        List<Integer> bitmap = new ArrayList<>();

        @Override
        public String toString() {
            return new StringJoiner(", ", Line.class.getSimpleName() + "[", "]")
                    .add("desiredState=" + desiredState)
                    .add("currentState=" + currentState)
                    .add("bitmap=" + bitmap)
                    .toString();
        }
    }

    public static long partOne(String input) {
        var lines = input.split("\n");
//        System.out.println(line);
        int sum = 0;
//        String line = lines[1];

        for (String line : lines) {
//        {
            System.out.println(line);
            var first = line.substring(line.indexOf("[") + 1, line.indexOf("]"));

            Pattern p = Pattern.compile("(\\([^)]+\\))");
            Matcher m = p.matcher(line);

            var l = new Line();
            l.desiredState = parseDesireg(first);

            while (m.find()) {
                int bitmap = parseBitmap(m.group());
                l.bitmap.add(bitmap);
            }

//            System.out.println(l);

            var x = calculate(l);

//            System.out.println();
            sum += x;
        }
        return sum;
    }

    private static int calculate(Line l) {
        int presses = 0;

        int minPressed = Integer.MAX_VALUE;
        for (Integer i : l.bitmap) {
            Set<Integer> states = new HashSet<>();
            var state = 0;
            state ^= i;
            //     presses++;

            if (state == l.desiredState) {
                return 1;
            }

            states.add(state);
            var x = calculate(l, state, states, i, 1, Integer.MAX_VALUE);
            minPressed = Math.min(minPressed, x);

        }

        return minPressed;
    }

    private static int calculate(Line l, int state, Set<Integer> s, Integer lastPressed, int presses, int minPressed) {
        if (presses >= minPressed) {
            return minPressed;
        }
        minPressed = Integer.MAX_VALUE;

        for (Integer i : l.bitmap) {
            var states = new HashSet<>(s);
            if (i.equals(lastPressed)) {
                continue;
            }

            var currentState = state;
            currentState ^= i;


            if (currentState == l.desiredState) {
                return presses + 1;
            }

            if (states.contains(currentState)) {
                return Integer.MAX_VALUE;
            }
            states.add(currentState);

            var y = calculate(l, currentState, states, i, presses + 1, minPressed);
            minPressed = Math.min(minPressed, y);
            System.out.println(y);
        }
        return minPressed;
    }

//    private static int calculate(Line l, int maxValue) {
//        int presses = 0;
//        for (Integer i : l.bitmap) {
//
//        }
//    }

    private static int parseBitmap(String group) {
        var bits = Arrays.stream(group.substring(1, group.length() - 1).split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

//        System.out.print(bits);
//        System.out.print(" -> ");

        int b = 0;
        for (Integer bit : bits) {
            if (bit == 0) {
                b = b | 1;
            } else {
                b = b | 2 << (bit - 1);
            }
        }

//        System.out.println(b + "(" + Integer.toBinaryString(b) + ")");

        return b;
    }

    private static int parseDesireg(String first) {
        var x = first.replace('.', '0').replace('#', '1');
        x = new StringBuilder(x).reverse().toString();
        var y = Integer.parseInt(x, 2);
        return y;
    }

    public static long partTwo(String input) {
        return -1;
    }

}
