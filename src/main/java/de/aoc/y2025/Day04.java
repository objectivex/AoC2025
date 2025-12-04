package de.aoc.y2025;

public class Day04 {

    public static long partOne(String input) {
        var lines = input.split("\n");

        var grid = new char[lines.length][lines[0].length()];

        for (int y = 0; y < lines.length; y++) {
            var line = lines[y];
            for (int x = 0; x < line.length(); x++) {
                grid[y][x] = line.charAt(x);
            }
        }


        var checkedGrid = new char[lines.length][lines[0].length()];

        for (int y = 0; y < grid.length; y++) {
            var line = grid[y];
            for (int x = 0; x < line.length; x++) {
                if (grid[y][x] == '@') {
                    var isok = check(grid, x, y);
                    if (isok) {
                        checkedGrid[y][x] = 'x';
                    } else {
                        checkedGrid[y][x] = grid[y][x];
                    }
                } else {
                    checkedGrid[y][x] = grid[y][x];
                }
            }
        }


        int cnt = 0;
        for (int y = 0; y < checkedGrid.length; y++) {
            var line = checkedGrid[y];
            for (int x = 0; x < line.length; x++) {
                if (checkedGrid[y][x] == 'x') {
                    cnt++;
                }
            }
        }

        return cnt;
    }


    private static boolean check(char[][] grid, int xPos, int yPos) {
        int cnt = 0;
        if (getElement(grid, xPos - 1, yPos - 1) == '@') {
            cnt++;
        }
        if (getElement(grid, xPos, yPos - 1) == '@') {
            cnt++;
        }
        if (getElement(grid, xPos + 1, yPos - 1) == '@') {
            cnt++;
        }

        if (getElement(grid, xPos - 1, yPos) == '@') {
            cnt++;
        }
        if (getElement(grid, xPos + 1, yPos) == '@') {
            cnt++;
        }

        if (getElement(grid, xPos - 1, yPos + 1) == '@') {
            cnt++;
        }
        if (getElement(grid, xPos, yPos + 1) == '@') {
            cnt++;
        }
        if (getElement(grid, xPos + 1, yPos + 1) == '@') {
            cnt++;
        }


        return cnt < 4;
    }

    private static char getElement(char[][] grid, int xPos, int yPos) {
        if (yPos < 0 || yPos >= grid.length) {
            return '.';
        }

        if (xPos < 0 || xPos >= grid[yPos].length) {
            return '.';
        }
        return grid[yPos][xPos];
    }

    private static void print(char[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            var line = grid[y];
            for (int x = 0; x < line.length; x++) {
                System.out.print(line[x]);
            }
            System.out.println();
        }
    }

    public static long partTwo(String input) {
        return -1;
    }

}
