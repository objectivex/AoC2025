package de.aoc.y2025;

import java.util.*;

public class Day08 {
    record Position(int x, int y, int z) {
    }

    record Distance(Position p1, Position p2, Double distance) {
    }

    public static long partOne(String input, int maxPairs) {
        Map<Position, Integer> circuitMapping = new HashMap<>();
        var positions = input.lines()
                .map(l -> {
                    var p = l.split(",");
                    return new Position(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]));
                })
                .toList();

        List<Distance> distances = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            for (int j = i + 1; j < positions.size(); j++) {
                Position p1 = positions.get(i);
                Position p2 = positions.get(j);
                distances.add(new Distance(p1, p2, calculateDistance(p1, p2)));
            }
        }

        distances.sort(Comparator.comparingDouble(Distance::distance));
        int currentCircuit = 0;

        distances = distances.subList(0, maxPairs);
        for (Distance distance : distances) {
            var circuit1 = circuitMapping.get(distance.p1);
            var circuit2 = circuitMapping.get(distance.p2);

            int circuit = 0;
            if (circuit1 != null && circuit2 != null) {
                List<Position> toConnect = circuitMapping.entrySet().stream().filter(e -> e.getValue().compareTo(circuit2) == 0).map(e -> e.getKey()).toList();
                for (Position position : toConnect) {
                    circuitMapping.put(position, circuit1);
                }
            } else if (circuit1 == null && circuit2 != null) {
                circuitMapping.put(distance.p1, circuit2);
            } else if (circuit1 != null && circuit2 == null) {
                circuitMapping.put(distance.p2, circuit1);
            } else if (circuit1 == null && circuit2 == null) {
                currentCircuit++;
                circuit = currentCircuit;
                circuitMapping.put(distance.p1, circuit);
                circuitMapping.put(distance.p2, circuit);
            }

        }

        Map<Integer, Integer> frequencies = new HashMap<>();

        circuitMapping.values().stream()
                .distinct()
                .forEach(v -> frequencies.put(v, Collections.frequency(circuitMapping.values(), v)));

        System.out.println(frequencies);

        var topMost = frequencies.values()
                .stream()
                .sorted(Comparator.comparingInt(i -> (int) i).reversed())
                .toList();

        long sum = topMost.get(0);
        for (int i = 1; i < 3; i++) {
            sum *= topMost.get(i);

        }
        return sum;
    }

    private static Double calculateDistance(Position position, Position position1) {
        return Math.sqrt(Math.pow((position.x - position1.x), 2) + Math.pow((position.y - position1.y), 2) + Math.pow((position.z - position1.z), 2));
    }

    public static long partTwo(String input) {
        Map<Position, Integer> circuitMapping = new HashMap<>();
        var positions = input.lines()
                .map(l -> {
                    var p = l.split(",");
                    return new Position(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]));
                })
                .toList();

        int currentCircuit = 1;
        for (Position position : positions) {
            circuitMapping.put(position, currentCircuit);
            currentCircuit++;
        }

        List<Distance> distances = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            for (int j = i + 1; j < positions.size(); j++) {
                Position p1 = positions.get(i);
                Position p2 = positions.get(j);
                distances.add(new Distance(p1, p2, calculateDistance(p1, p2)));
            }
        }

        distances.sort(Comparator.comparingDouble(Distance::distance));

        for (Distance distance : distances) {
            var circuit1 = circuitMapping.get(distance.p1);
            var circuit2 = circuitMapping.get(distance.p2);

            var circuitCountBefore = circuitMapping.values().stream().distinct().count();
            int circuit = 0;
            if (circuit1 != null && circuit2 != null) {
                List<Position> toConnect = circuitMapping.entrySet().stream().filter(e -> e.getValue().compareTo(circuit2) == 0).map(e -> e.getKey()).toList();
                for (Position position : toConnect) {
                    circuitMapping.put(position, circuit1);
                }
            } else if (circuit1 == null && circuit2 != null) {
                circuitMapping.put(distance.p1, circuit2);
            } else if (circuit1 != null && circuit2 == null) {
                circuitMapping.put(distance.p2, circuit1);
            } else if (circuit1 == null && circuit2 == null) {
                currentCircuit++;
                circuit = currentCircuit;
                circuitMapping.put(distance.p1, circuit);
                circuitMapping.put(distance.p2, circuit);
            }

            var circuitCountAfter = circuitMapping.values().stream().distinct().count();
            if (circuitCountAfter == 1) {
                long x1 = distance.p1.x;
                long x2 = distance.p2.x;
                return x1 * x2;
            }

        }

        return -1;
    }
}
