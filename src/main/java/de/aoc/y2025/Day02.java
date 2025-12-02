package de.aoc.y2025;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day02 {
    record Range(long start, long end) {
    }

    public static long partOne(String input) {
        var ranges = getRanges(input);

        long checksum = 0;

        for (Range range : ranges) {
            for (long i = range.start(); i <= range.end(); i++) {
                String id = "" + i;
                if (id.length() % 2 != 0) {
                    continue;
                }

                String left = id.substring(0, id.length() / 2);
                String right = id.substring(id.length() / 2);
                if (left.equals(right)) {
                    checksum += i;
                }
            }
        }
        return checksum;
    }

    public static long partTwo(String input) {
        var ranges = getRanges(input);

        Set<Long> invalidIds = new HashSet<>();

        for (Range range : ranges) {
            for (long i = range.start(); i <= range.end(); i++) {
                String id = "" + i;
                for (int j = 1; j <= id.length() / 2; j++) {
                    String left = id.substring(0, j);
                    String right = id.substring(j);
                    if (right.length() % left.length() != 0) {
                        continue;
                    }

                    var repeat = right.length() / left.length();
                    if (right.equals(left.repeat(repeat))){
                        invalidIds.add(i);
                    }
                }
            }
        }
        return invalidIds.stream().reduce(0L, Long::sum);
    }

    private static List<Range> getRanges(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(l -> new Range(Long.parseLong(l.split("-")[0]), Long.parseLong(l.split("-")[1])))
                .toList();
    }
}
