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
        List<Range> range = parseRanges(input);
        List<Long> ingredients = parseIngredients(input);
        int cnt = 0;
        for (Long ingredient : ingredients) {
            for (Range range1 : range) {
                if (ingredient >= range1.start() && ingredient <= range1.end()) {
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

    private static List<Range> parseRanges(String input) {
        List<Range> ranges = new ArrayList<>();
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
        List<Range> ranges = parseRanges(input);

        var count = calculate(ranges);

        while (true) {
            System.out.println(ranges);
            ranges = consolidate(ranges);
            var newCount = calculate(ranges);
            if (newCount == count) {
                return newCount;
            }

            count = newCount;
        }
//        List<Range> cleanedRanges = new ArrayList<>();
//
//        for (Range range1 : range) {
//            Range r = new Range(range1.start(), range1.end());
//
//            for (Range cleanedRange : cleanedRanges) {
//                if (r.start() >= cleanedRange.start() && r.start() <= cleanedRange.end()) {
//                    r = new Range(Math.min(cleanedRange.end() + 1, r.end()), r.end);
//                }
//
//                if (r.end() >= cleanedRange.start() && r.start() <= cleanedRange.end()) {
//                    r = new Range(r.start(), Math.min(cleanedRange.start() - 1, r.start()));
//                }
//
//                if (r.start() >= cleanedRange.start() && r.end() <= cleanedRange.end()) {
//                    r = new Range(0L, -1L);
//                }
//            }
//
//            if (r.start() <= r.end()) {
//                cleanedRanges.add(r);
//            }
//        }

//        System.out.println(cleanedRanges);
//        283193209309175
//        288622339221165
//        288622339221189
//        300943140654467
//        301242926522964
//        307598859591863
        //332274430193684

//        System.out.println(cleanedRanges);
    }

    private static List<Range> consolidate(List<Range> ranges) {
        List<Range> newRanges = new ArrayList<>(ranges.size());

        for (Range range : ranges) {
            boolean add = true;
            for (int i = 0; i < newRanges.size(); i++) {
                Range newRange = newRanges.get(i);
                if (range.start() >= newRange.start() && range.end() <= newRange.end()) {
                    add = false;
                } else if (range.start() <= newRange.start() && range.end() >= newRange.start() && range.end() <= newRange.end()) {
                    var r = new Range(range.start(), newRange.end());
                    newRanges.set(i, r);
                    add = false;
                } else if (range.start() >= newRange.start() && range.start()<= newRange.end() && range.end() > newRange.end()) {
                    var r = new Range(newRange.start(), range.end());
                    newRanges.set(i, r);
                    add = false;
                } else if (range.start() <= newRange.start() && range.end() >= newRange.end()) {
                    newRanges.set(i, range);
                    add = false;
                }

            }
            if (add) {
                newRanges.add(range);
            }
        }

        return newRanges;
    }

    private static long calculate(List<Range> ranges) {
        return ranges.stream()
                .mapToLong(r -> r.end() - r.start() + 1)
                .sum();
    }
}
