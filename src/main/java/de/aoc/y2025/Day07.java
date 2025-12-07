package de.aoc.y2025;

import java.util.HashMap;
import java.util.Map;

public class Day07 {

    public static long partOne(String input) {
        var grid = AoCUtils.getGrid(input.replace('S', '|'));

        long splitted = 0;
        for (int y = 1; y < grid.length; y++) {
            var line = grid[y];

            for (int x = 0; x < line.length; x++) {
                if (grid[y - 1][x] == '|') {
                    if (grid[y][x] == '.') {
                        grid[y][x] = '|';
                    } else if (grid[y][x] == '^') {
                        grid[y][x - 1] = '|';
                        grid[y][x + 1] = '|';
                        splitted++;
                    }
                }
            }
        }

        return splitted;
    }

    public static long partTwo(String input) {
        var grid = AoCUtils.getGrid(input.replace('S', '|'));

        long newTimelines = 1;
        for (int y = 1; y < grid.length; y++) {
            var line = grid[y];

            for (int x = 0; x < line.length; x++) {
                if (grid[y - 1][x] == '|') {
                    if (grid[y][x] == '.') {
                        grid[y][x] = '|';
                    } else if (grid[y][x] == '^') {
                        newTimelines += splitTimeline(grid, x, y, new HashMap<>());
                    }
                }
            }

        }
        return newTimelines;
    }

    private static long splitTimeline(char[][] grid, int x, int y, Map<Integer, Long> splitsInTime) {
        var index = x * 10000 + y;

        if (splitsInTime.containsKey(index)) {
            return splitsInTime.get(index);
        }

        long newTimeLines = 1;

        var leftGrid = AoCUtils.copyGrid(grid);
        leftGrid[y][x - 1] = '|';

        var rightGrid = AoCUtils.copyGrid(grid);
        rightGrid[y][x + 1] = '|';

        newTimeLines += splitTimeline(leftGrid, y, splitsInTime);
        newTimeLines += splitTimeline(rightGrid, y, splitsInTime);

        splitsInTime.put(index, newTimeLines);

        return newTimeLines;
    }

    private static long splitTimeline(char[][] grid, int startY, Map<Integer, Long> splitsInTime) {
        long newTimeLines = 0;
        for (int y = startY + 1; y < grid.length; y++) {
            var line = grid[y];

            for (int x = 0; x < line.length; x++) {
                if (grid[y - 1][x] == '|') {
                    if (grid[y][x] == '.') {
                        grid[y][x] = '|';
                    } else if (grid[y][x] == '^') {
                        newTimeLines += splitTimeline(grid, x, y, splitsInTime);
                    }
                }
            }

        }
        return newTimeLines;
    }
}
