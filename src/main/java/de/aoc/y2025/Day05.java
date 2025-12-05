package de.aoc.y2025;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

public class Day05 {
    record Range(Long start, Long end) {
    }

    public static long partOne(String input) {
        Set<Range> range = parseRanges(input);
        List<Long> ingredients = parseIngredients(input);
        int cnt = 0;
        for (Long ingredient : ingredients) {
            for (Range range1 : range) {
                if (ingredient>=range1.start() && ingredient<=range1.end()) {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }

    private static List<Long> parseIngredients(String input) {
        List<Long> ingredients = new ArrayList<>();
        input.lines().forEach(l -> {
            if (l.contains("-") || l.trim().isBlank()) {
                return;
            }

            ingredients.add(Long.parseLong(l.trim()));

        });

        return ingredients;
    }

    private static Set<Range> parseRanges(String input) {
        Set<Range> ranges = new HashSet<>();
        input.lines().forEach(l ->
                {
                    if (l.contains("-")) {
                        long start = Long.parseLong(l.split("-")[0].trim());
                        long end = Long.parseLong(l.split("-")[1].trim());
                        ranges.add(new Range(start, end));
                    }
                }
        );

        return ranges;
    }

    public static long partTwo(String input) {
        return -1;
    }

}
