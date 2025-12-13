package de.aoc.y2025;

import java.util.ArrayList;
import java.util.List;

public class Day12 {
    record Region(int width, int length, List<Integer> indexCount) {
    }

    public static long partOne(String input) {
        List<Integer> shapeSize = new ArrayList<>();
        List<Region> regions = new ArrayList<>();

        String[] lines = input.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.matches("^\\d+:$")) {
                String grid = lines[i + 1] + lines[i + 2] + lines[i + 3];
                i += 3;
                shapeSize.add((int) grid.chars().filter(c -> c != '.').count());
            }

            if (line.contains("x")) {
                var grid = line.split(":");
                var width = Integer.parseInt(grid[0].split("x")[0].trim());
                var length = Integer.parseInt(grid[0].split("x")[1].trim());

                List<Integer> counts = new ArrayList<>();
                for (String s : grid[1].split("\\s+")) {
                    if (s.isBlank()) {
                        continue;
                    }
                    counts.add(Integer.parseInt(s.trim()));
                }

                regions.add(new Region(width, length, counts));
            }
        }

        return regions.stream()
                .filter(r -> match(r, shapeSize))
                .mapToInt(r -> 1)
                .sum();
    }

    private static boolean match(Region region, List<Integer> shapes) {
        int sum = 0;
        for (int i = 0; i < region.indexCount.size(); i++) {
            int count = region.indexCount.get(i);
            sum += shapes.get(i) * count;
        }

        // 20% Aufschlag auf den tatsächlichen Platzbedarf. Heuristik!
        return !(sum * 1.2 > region.length * region.width);
    }
}
