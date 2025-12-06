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
}
