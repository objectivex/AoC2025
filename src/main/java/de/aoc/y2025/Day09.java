package de.aoc.y2025;

import java.util.*;

public class Day09 {
    record Position(int x, int y) {
    }

    public static long partOne(String input) {
        var positions = readPositions(input);

        long maxArea = 0;
        for (int i = 0; i < positions.size(); i++) {
            var pos1 = positions.get(i);
            for (int j = i + 1; j < positions.size(); j++) {
                var pos2 = positions.get(j);

                var length = Math.max(pos1.x, pos2.x) - Math.min(pos1.x, pos2.x) + 1;
                var height = Math.max(pos1.y, pos2.y) - Math.min(pos1.y, pos2.y) + 1;
                var area = (long) length * (long) height;

                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    private static List<Position> readPositions(String input) {
        return input.lines()
                .map(l -> {
                            var split = l.split(",");
                            return new Position(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                        }
                ).toList();
    }

    public static long partTwo(String input) {
        var positions = readPositions(input);
        var maxX = positions.stream().mapToInt(Position::x).max().orElseThrow();
        var maxY = positions.stream().mapToInt(Position::x).max().orElseThrow();


        Map<Position, Boolean> border = new HashMap();


        for (Position p1 : positions) {
            for (Position p2 : positions) {
                if (p1.x == p2.x) {
                    for (int y = Math.min(p1.y, p2.y); y <= Math.max(p1.y, p2.y); y++) {
                        border.put(new Position(p1.x, y), true);
                        // grid[y][p1.x] = 'X';
                    }
                }

                if (p1.y == p2.y) {
                    for (int x = Math.min(p1.x, p2.x); x <= Math.max(p1.x, p2.x); x++) {
                        border.put(new Position(x, p1.y), true);
//                        grid[p1.y][x] = 'X';
                    }
                }
            }
        }

//        var grid = AoCUtils.initGrid(maxY + 1, maxX + 1);
//        for (Position position : border.keySet()) {
//            grid[position.y][position.x] = 'X';
//        }
//        AoCUtils.print(grid);

        var start = positions.getFirst();
        start = new Position(start.x+1, start.y+1);
//        grid[start.y][start.x] = '#';
        fill(border, start);

//        for (Position position : border.keySet()) {
//            grid[position.y][position.x] = 'X';
//        }
//        AoCUtils.print(grid);

        long maxArea = 0;
        for (int i = 0; i < positions.size(); i++) {
            var pos1 = positions.get(i);
            for (int j = i + 1; j < positions.size(); j++) {
                var pos2 = positions.get(j);

                var length = Math.max(pos1.x, pos2.x) - Math.min(pos1.x, pos2.x) + 1;
                var height = Math.max(pos1.y, pos2.y) - Math.min(pos1.y, pos2.y) + 1;
                var area = (long) length * (long) height;

                if (contained(pos1, pos2, border, maxX, maxY)) {
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
//        return -1;
    }

    private static void fill(Map<Position, Boolean> border, Position start) {
        if (border.containsKey(start)) {
            return;
        }

        border.put(start, true);
        fill(border,new Position(start.x-1, start.y));
        fill(border,new Position(start.x+1, start.y));
        fill(border,new Position(start.x, start.y-1));
        fill(border,new Position(start.x, start.y+1));
    }

    private static boolean contained(Position pos1, Position pos2, Map<Position, Boolean> positions, int maxX, int maxY) {

        var p1 = new Position(pos1.x, pos1.y);
        var p2 = new Position(pos1.x, pos2.y);
        var p3 = new Position(pos2.x, pos1.y);
        var p4 = new Position(pos2.x, pos2.y);

        var c1 = contained(p1, positions, maxX, maxY);
        var c2 = contained(p2, positions, maxX, maxY);
        var c3 = contained(p3, positions, maxX, maxY);
        var c4 = contained(p4, positions, maxX, maxY);

        return c1 && c2 && c3 && c4;
    }

    private static boolean contained(Position p1, Map<Position, Boolean> positions, int maxX, int maxY) {

        if (positions.containsKey(p1)) {
            return true;
        }

        boolean foundBorder = false;

        for (int x = p1.x; x >= 0; x--) {

            if (positions.containsKey(new Position(x, p1.y))) {
                foundBorder = true;
                break;
            }
        }

        if (!foundBorder) {
            return false;
        }

        foundBorder = false;

        for (int x = p1.x; x <= maxX; x++) {
            if (positions.containsKey(new Position(x, p1.y))) {
                foundBorder = true;
                break;
            }
        }
        if (!foundBorder) {
            return false;
        }

        foundBorder = false;
        for (int y = p1.y; y >= 0; y--) {
            if (positions.containsKey(new Position(p1.x, y))) {
                foundBorder = true;
                break;
            }
        }

        if (!foundBorder) {
            return false;
        }

        foundBorder = false;
        for (int y = p1.y; y <= maxY; y++) {
            if (positions.containsKey(new Position(p1.x, y))) {
                foundBorder = true;
                break;
            }
        }

        if (!foundBorder) {
            return false;
        }

        return true;
    }

    private static boolean contained(int leftX, int topY, List<Position> positions) {
        boolean leftContained = false;
        boolean topContained = false;
        for (Position position : positions) {
        }

        return leftContained && topContained;
    }
}
