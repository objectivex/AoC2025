package de.aoc.y2025;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 {
    public static long partOne(String input) {
        var lines = input.lines().toList();

        long sum = 0;
        for (String line : lines) {
            var desired = parseDesired(line.substring(line.indexOf("[") + 1, line.indexOf("]")));
            List<Integer> toggles = parseToggles(line);

            var cache = buildCache(toggles);
            sum += cache.get(desired).stream().mapToInt(List::size).min().orElseThrow();
        }
        return sum;
    }

    private static Map<Integer, List<List<Integer>>> buildCache(List<Integer> toggles) {
        Map<Integer, List<List<Integer>>> cache = new HashMap<>();

        List<Integer> l1 = new ArrayList<>();
        l1.add(toggles.getFirst());
        buildCache(toggles.subList(1, toggles.size()), l1, cache);

        List<Integer> l2 = new ArrayList<>();
        buildCache(toggles.subList(1, toggles.size()), l2, cache);

        return cache;
    }

    private static void buildCache(List<Integer> toggles, List<Integer> head, Map<Integer, List<List<Integer>>> cache) {
        if (toggles.isEmpty()) {
            int state = 0;
            for (Integer toggle : head) {
                state ^= toggle;
            }

            var list = cache.getOrDefault(state, new ArrayList<>());
            list.add(head);
            cache.put(state, list);
            return;
        }

        List<Integer> l1 = new ArrayList<>(head);
        l1.add(toggles.getFirst());
        buildCache(toggles.subList(1, toggles.size()), l1, cache);

        List<Integer> l2 = new ArrayList<>(head);
        buildCache(toggles.subList(1, toggles.size()), l2, cache);
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
        var lines = input.lines().toList();

        long sum = 0;
        for (String line : lines) {
            List<Integer> toggles = parseToggles(line);
            List<Integer> joltages = parseJoltages(line);
            var cache = buildCache(toggles);

            sum += calculatePushes(joltages, cache);

        }

        return sum;
    }

    private static int getDesired(List<Integer> joltages) {
        StringBuilder desiredString = new StringBuilder();
        for (Integer joltage : joltages) {
            if (joltage % 2 == 0) {
                desiredString.append('.');
            } else {
                desiredString.append('#');
            }
        }

        return parseDesired(desiredString.toString());
    }

    private static long calculatePushes(List<Integer> joltages, Map<Integer, List<List<Integer>>> cache) {
        if(joltages.stream().allMatch( i-> i==0)) {
            return 0;
        }

        long result = Integer.MAX_VALUE;
        int desired = getDesired(joltages);
        var possibleButtonCombinations = cache.getOrDefault(desired, Collections.emptyList());

        for (List<Integer> buttonsToPress : possibleButtonCombinations) {
            var newJoltages = new ArrayList<>(joltages);

            for (Integer button : buttonsToPress) {
                for (Integer index : getIndex(button)) {
                    newJoltages.set(index, newJoltages.get(index) - 1);
                }
            }


            if (joltages.stream().anyMatch(i -> i < 0)) {
               continue;
            }

            newJoltages.replaceAll(i -> i / 2);

            var presses = calculatePushes(newJoltages, cache);
            if (presses == Integer.MAX_VALUE) {
                continue;
            }
            presses = buttonsToPress.size() + 2 * presses;
            result = Math.min(result, presses);
        }

        return result;

    }

    private static List<Integer> getIndex(Integer possiblePush) {
        List<Integer> index = new ArrayList<>();

        var s = Integer.toBinaryString(possiblePush);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(s.length() - 1 - i);
            if (c == '1') {
                index.add(i);
            }
        }


        return index;
    }

    private static List<Integer> parseJoltages(String line) {
        return Arrays.stream(line.substring(line.indexOf('{') + 1, line.indexOf('}')).split(","))
                .map(Integer::parseInt)
                .toList();

    }
}
