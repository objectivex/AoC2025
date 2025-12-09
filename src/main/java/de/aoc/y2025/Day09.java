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
        List<Position> positions = new ArrayList<>(readPositions(input));
        positions.add(positions.getFirst());

        List<Position> border = new ArrayList<>();

        for (int i = 0; i < positions.size() - 1; i++) {
            var pos1 = positions.get(i);
            var pos2 = positions.get(i + 1);

            if (pos1.x == pos2.x) {
                for (int y = Math.min(pos1.y, pos2.y); y <= Math.max(pos1.y, pos2.y); y++) {
                    border.add(new Position(pos1.x, y));
                }
            } else if (pos1.y == pos2.y) {
                for (int x = Math.min(pos1.x, pos2.x); x <= Math.max(pos1.x, pos2.x); x++) {
                    border.add(new Position(x, pos1.y));
                }
            }
        }

        long maxArea = 0;
        for (int i = 0; i < positions.size(); i++) {
            var pos1 = positions.get(i);
            for (int j = i + 1; j < positions.size(); j++) {
                var pos2 = positions.get(j);

                var length = Math.max(pos1.x, pos2.x) - Math.min(pos1.x, pos2.x) + 1;
                var height = Math.max(pos1.y, pos2.y) - Math.min(pos1.y, pos2.y) + 1;
                var area = (long) length * (long) height;

                if (area > maxArea && enclosed(pos1, pos2, border)) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    private static boolean enclosed(Position pos1, Position pos2, List<Position> border) {
        int minX = Math.min(pos1.x, pos2.x);
        int maxX = Math.max(pos1.x, pos2.x);
        int minY = Math.min(pos1.y, pos2.y);
        int maxY = Math.max(pos1.y, pos2.y);

        for (Position position : border) {
            if (position.x > minX && position.x < maxX && position.y > minY && position.y < maxY) {
                return false;
            }
        }

        return true;


    }

    private static void fillLine(List<Position> positions, int y, Map<Integer, List<Integer>> border) {
//        System.out.println("filling " + y);
        List<Integer> intersections = new ArrayList<>();

        for (int i = 0; i < positions.size() - 1; i++) {
            Position pos1 = positions.get(i);
            Position pos2 = positions.get(i + 1);
            if (pos1.y == pos2.y) {
                continue;
            }

            intersections.addAll(intersect(pos1, pos2, y));
        }

        Collections.sort(intersections);
        border.put(y, intersections);

//        for (int i = 0; i < intersections.size() - 1; i++) {
//            var start = intersections.get(i);
//            var end = intersections.get(i + 1);
//            for (Integer integer = start; integer <= end; integer++) {
//                border.put(new Position(integer, y), true);
//
//            }
//        }
    }

    private static boolean isIncluded(Position pos1, Position pos2, Map<Integer, List<Integer>> border, List<Position> positions) {
        var minX = Math.min(pos1.x, pos2.x);
        var minY = Math.min(pos1.y, pos2.y);
        var maxX = Math.max(pos1.x, pos2.x);
        var maxY = Math.max(pos1.y, pos2.y);
//
//        if (!filledLines.contains(minY)) {
//            fillLine(positions, minY, border);
//            filledLines.add(minY);
//        }
//        if (!filledLines.contains(maxY)) {
//            fillLine(positions, maxY, border);
//            filledLines.add(maxY);
//        }

//        if (!border.containsKey(new Position(minX, minY))) {
//            return false;
//        }
//        if (!border.containsKey(new Position(minX, maxY))) {
//            return false;
//        }
//        if (!border.containsKey(new Position(maxX, minY))) {
//            return false;
//        }
//        if (!border.containsKey(new Position(maxX, maxY))) {
//            return false;
//        }

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                var intersections = border.getOrDefault(y, Collections.emptyList());
                boolean found = false;
                for (int i = 0; i < intersections.size() - 1; i++) {
                    var start = intersections.get(i);
                    var end = intersections.get(i + 1);
                    if (x >= start && x <= end) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
//                if (!filledLines.contains(y)) {
//                    fillLine(positions, y, border);
//                    filledLines.add(y);
//                }
//                if (!border.containsKey(new Position(x, y))) {
//                    return false;
//                }
            }

        }
        return true;
    }

    private static List<Integer> intersect(Position pos1, Position pos2, int y) {
        List<Integer> integers = new ArrayList<>();

        int minY = Math.min(pos1.y, pos2.y);
        int maxY = Math.max(pos1.y, pos2.y);


        if (y < minY) {
            return integers;
        } else if (y == maxY) {
            return List.of(pos1.x, pos1.x);
        } else if (y == minY) {
            return List.of(pos1.x);
        } else if (y > minY && y < maxY) {
            return List.of(pos1.x);
        }

        return Collections.emptyList();
    }

    private static void fill(Map<Position, Boolean> border, Position start) {
        if (border.containsKey(start)) {
            return;
        }

        border.put(start, true);
        fill(border, new Position(start.x - 1, start.y));
        fill(border, new Position(start.x + 1, start.y));
        fill(border, new Position(start.x, start.y - 1));
        fill(border, new Position(start.x, start.y + 1));
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
