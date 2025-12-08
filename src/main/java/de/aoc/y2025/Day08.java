package de.aoc.y2025;

import java.util.*;

public class Day08 {
    record Position(long x, long y, long z) {
    }

    record Distance(Position p1, Position p2, Double distance) {
    }

    public static long partOne(String input, int maxPairs) {
        var positions = getPositions(input);

        Map<Position, Integer> circuitMapping = createCircuitMapping(positions);
        List<Distance> distances = calculateDistances(positions).subList(0, maxPairs);

        for (Distance distance : distances) {
            connectCircuits(circuitMapping, distance);
        }

        Map<Integer, Integer> frequencies = new HashMap<>();

        circuitMapping.values()
                .stream()
                .distinct()
                .forEach(v -> frequencies.put(v, Collections.frequency(circuitMapping.values(), v)));


        var topMost = frequencies.values()
                .stream()
                .sorted(Comparator.comparingInt(i -> (int) i).reversed())
                .toList();

        long sum = topMost.getFirst();
        for (int i = 1; i < 3; i++) {
            sum *= topMost.get(i);
        }
        return sum;
    }

    public static long partTwo(String input) {
        var positions = getPositions(input);
        Map<Position, Integer> circuitMapping = createCircuitMapping(positions);
        List<Distance> distances = calculateDistances(positions);

        for (Distance distance : distances) {
            connectCircuits(circuitMapping, distance);

            var circuitCountAfter = circuitMapping.values().stream().distinct().count();
            if (circuitCountAfter == 1) {
                long x1 = distance.p1.x;
                long x2 = distance.p2.x;
                return x1 * x2;
            }

        }

        return -1;
    }

    private static void connectCircuits(Map<Position, Integer> circuitMapping, Distance distance) {
        var circuit1 = circuitMapping.get(distance.p1);
        var circuit2 = circuitMapping.get(distance.p2);

        if (circuit1 != null && circuit2 != null) {
            if (circuit1.equals(circuit2)) {
                return;
            }
            circuitMapping.entrySet().stream()
                    .filter(e -> e.getValue().compareTo(circuit2) == 0)
                    .map(Map.Entry::getKey)
                    .forEach(position -> circuitMapping.put(position, circuit1));
        } else if (circuit1 == null && circuit2 != null) {
            circuitMapping.put(distance.p1, circuit2);
        } else if (circuit1 != null) {
            circuitMapping.put(distance.p2, circuit1);
        }
    }

    private static List<Distance> calculateDistances(List<Position> positions) {
        List<Distance> distances = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            for (int j = i + 1; j < positions.size(); j++) {
                Position p1 = positions.get(i);
                Position p2 = positions.get(j);
                Double distance = Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2) + Math.pow((p1.z - p2.z), 2));
                distances.add(new Distance(p1, p2, distance));
            }
        }

        distances.sort(Comparator.comparingDouble(Distance::distance));
        return distances;
    }

    private static Map<Position, Integer> createCircuitMapping(List<Position> positions) {
        Map<Position, Integer> circuitMapping = new HashMap<>();
        for (int i = 0; i < positions.size(); i++) {
            var position = positions.get(i);
            circuitMapping.put(position, i);
        }
        return circuitMapping;
    }

    private static List<Position> getPositions(String input) {
        return input.lines()
                .map(l -> {
                    var p = l.split(",");
                    return new Position(Long.parseLong(p[0]), Long.parseLong(p[1]), Long.parseLong(p[2]));
                })
                .toList();
    }
}
