package de.aoc.y2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day07 {
    public static long partOne(String input) {
        var grid = getGrid(input);
//        AoCUtils.print(grid);
//        System.out.println();

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

//            AoCUtils.print(grid);
//            System.out.println();
        }

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
        return -1;
    }

}
