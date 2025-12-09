package de.aoc.y2025;

public class AoCUtils {
    public static void print(char[][] grid) {
        for (char[] chars : grid) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    static char[][] copyGrid(char[][] grid) {
        var copy = new char[grid.length][grid[0].length];
        for (int y = 0; y < grid.length; y++) {
            System.arraycopy(grid[y], 0, copy[y], 0, grid[y].length);
        }

        return copy;
    }

    static char[][] getGrid(String input) {
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

    public static char[][] initGrid(int maxY, int maxX) {
        char[][] grid = new char[maxY][maxX];
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                grid[y][x] = '.';
            }
        }
        return grid;
    }
}
