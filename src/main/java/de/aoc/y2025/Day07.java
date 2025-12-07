package de.aoc.y2025;

import java.util.HashMap;
import java.util.Map;

public class Day07 {

    public static long partOne(String input) {
        var grid = getGrid(input);


        long splitted = 0;
        for (int y = 1; y < grid.length; y++) {
            var line = grid[y];

            for (int x = 0; x < line.length; x++) {
                if (grid[y - 1][x] == 'S') {
                    grid[y][x] = '|';
                } else if (grid[y - 1][x] == '|') {
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

//        AoCUtils.print(grid);
        return splitted;
    }

    private static char[][] getGrid(String input) {
        String[] lines = input.split("\n");
        var maxLength = lines[0].length();

        char[][] grid = new char[lines.length][maxLength];
        for (int y = 0; y < lines.length; y++) {
            var line = lines[y];
            for (int x = 0; x < line.length(); x++) {
                grid[y][x] = line.charAt(x);
            }
        }

        return grid;
    }

    public static long partTwo(String input) {
        var grid = getGrid(input.replace('S', '|'));

        long splitted = 1;
        for (int y = 1; y < grid.length; y++) {
            var line = grid[y];

            for (int x = 0; x < line.length; x++) {
                if (grid[y - 1][x] == '|') {
                    if (grid[y][x] == '.') {
                        grid[y][x] = '|';
                    } else if (grid[y][x] == '^') {
                        splitted += splitTimeLine(grid, x, y, new HashMap<>());
                    }
                }
            }

        }
        return splitted;
    }

    private static long splitTimeLine(char[][] grid, int x, int y, Map<Integer, Long> splits) {

        var index = x*10000 + y;

        if (splits.containsKey(index)) {
            return splits.get(index);
        }

        long splitted = 1;

        var leftGrid = copyGrid(grid);
        leftGrid[y][x - 1] = '|';

        var rightGrid = copyGrid(grid);
        rightGrid[y][x + 1] = '|';

        for (int y2 = y + 1; y2 < leftGrid.length; y2++) {
            var line = leftGrid[y2];

            for (int x2 = 0; x2 < line.length; x2++) {
                if (leftGrid[y2 - 1][x2] == '|') {
                    if (leftGrid[y2][x2] == '.') {
                        leftGrid[y2][x2] = '|';
                    } else if (leftGrid[y2][x2] == '^') {
                        splitted += splitTimeLine(leftGrid, x2, y2, splits);
                    }
                }
            }

        }

        for (int y2 = y + 1; y2 < rightGrid.length; y2++) {
            var line = rightGrid[y2];

            for (int x2 = 0; x2 < line.length; x2++) {
                if (rightGrid[y2 - 1][x2] == '|') {
                    if (rightGrid[y2][x2] == '.') {
                        rightGrid[y2][x2] = '|';
                    } else if (rightGrid[y2][x2] == '^') {
                        splitted += splitTimeLine(rightGrid, x2, y2,splits);
                    }
                }
            }
        }

        splits.put(index, splitted);

//        AoCUtils.print(leftGrid);
//        System.out.println("-----");
//        AoCUtils.print(rightGrid);
//        System.out.println("-----");


        return splitted;
    }

    private static char[][] copyGrid(char[][] grid) {
        var copy = new char[grid.length][grid[0].length];
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                copy[y][x] = grid[y][x];
            }
        }

        return copy;
    }

}
