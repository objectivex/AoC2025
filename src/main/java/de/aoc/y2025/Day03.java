package de.aoc.y2025;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day03 {

    public static long partOne(String input) {
        var lines = input.lines().toList();

        long sum = 0;
        for (String line : lines) {
            sum+= getJolt(line);
        }
        return sum;
    }

    private static long getJolt(String line) {
        var max = 0;
        for (int i = 0; i < line.length()-1; i++) {
            for (int j = i+1; j < line.length(); j++) {
                var jolt = Integer.parseInt(line.substring(i,i+1))*10 + Integer.parseInt(line.substring(j,j+1));
                max = Math.max(max, jolt);

            }
        }

        return max;
    }

    public static long partTwo(String input) {
        return -1;
    }

}
