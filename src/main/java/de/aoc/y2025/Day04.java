package de.aoc.y2025;

public class Day04 {

    public static long partOne(String input) {
        var grid = createGrid(input);
        identifyPaperrollsToMove(grid);
        return countMovablePaperrolls(grid);
    }

    public static long partTwo(String input) {
        var grid = createGrid(input);

        var cnt = 0;
        while (true) {
            identifyPaperrollsToMove(grid);

            var currentCnt = countAndRemovePaperrolls(grid);

            if (currentCnt == 0) {
                break;
            }

            cnt += currentCnt;
        }
        return cnt;
    }

    private static int countMovablePaperrolls(char[][] grid) {
        int cnt = 0;
        for (char[] line : grid) {
            for (char c : line) {
                if (c == 'x') {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static int countAndRemovePaperrolls(char[][] grid) {
        int cnt = 0;
        for (int y = 0; y < grid.length; y++) {
            var line = grid[y];
            for (int x = 0; x < line.length; x++) {
                if (grid[y][x] == 'x') {
                    grid[y][x] = '.';
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void identifyPaperrollsToMove(char[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            var line = grid[y];
            for (int x = 0; x < line.length; x++) {
                if (grid[y][x] == '@') {
                    var isok = check(grid, x, y);
                    if (isok) {
                        grid[y][x] = 'x';
                    } else {
                        grid[y][x] = grid[y][x];
                    }
                } else {
                    grid[y][x] = grid[y][x];
                }
            }
        }
    }

    private static char[][] createGrid(String input) {
        var lines = input.split("\n");

        var grid = new char[lines.length][lines[0].length()];

        for (int y = 0; y < lines.length; y++) {
            var line = lines[y];
            for (int x = 0; x < line.length(); x++) {
                grid[y][x] = line.charAt(x);
            }
        }
        return grid;
    }


    private static boolean check(char[][] grid, int xPos, int yPos) {
        int cnt = 0;
        if (getElement(grid, xPos - 1, yPos - 1) == '@' || getElement(grid, xPos - 1, yPos - 1) == 'x') {
            cnt++;
        }
        if (getElement(grid, xPos, yPos - 1) == '@' || getElement(grid, xPos, yPos - 1) == 'x') {
            cnt++;
        }
        if (getElement(grid, xPos + 1, yPos - 1) == '@' || getElement(grid, xPos + 1, yPos - 1) == 'x') {
            cnt++;
        }

        if (getElement(grid, xPos - 1, yPos) == '@' || getElement(grid, xPos - 1, yPos) == 'x') {
            cnt++;
        }
        if (getElement(grid, xPos + 1, yPos) == '@' || getElement(grid, xPos + 1, yPos) == 'x') {
            cnt++;
        }

        if (getElement(grid, xPos - 1, yPos + 1) == '@' || getElement(grid, xPos - 1, yPos + 1) == 'x') {
            cnt++;
        }
        if (getElement(grid, xPos, yPos + 1) == '@' || getElement(grid, xPos, yPos + 1) == 'x') {
            cnt++;
        }
        if (getElement(grid, xPos + 1, yPos + 1) == '@' || getElement(grid, xPos + 1, yPos + 1) == 'x') {
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
}
