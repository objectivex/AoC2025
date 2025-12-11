package de.aoc.y2025;

import java.util.*;

public class Day11 {
    static class Node {
        String name;
        List<Node> nodes = new ArrayList<>();
    }

    static List<List<String>> possiblePath = new ArrayList<>();

    public static long partOne(String input) {
        var nodes = parseNodes(input);
        possiblePath.clear();
        List<String> path = new ArrayList<>();
        path.add("you");
        findPath(nodes, "you", "out", new HashSet<>(), path);
        return possiblePath.size();

    }


    private static void findPath(Map<String, List<String>> nodes, String currentNode, String end, Set<String> visited, List<String> path) {
        if (currentNode.equals(end)) {
            possiblePath.add(path);
            return;
        }

        visited.add(currentNode);

        var children = nodes.get(currentNode);
        for (String child : children) {
            if (visited.contains(child)) {
                continue;
            }

            path.add(child);
            findPath(nodes, child, end, visited, path);
            path.remove(child);
        }

        visited.remove(currentNode);
    }

    private static Map<String, List<String>> parseNodes(String input) {
        Map<String, List<String>> nodes = new HashMap<>();
        var lines = input.split("\n");
        for (String line : lines) {
            var name = line.split(":")[0].trim();
            var children = Arrays.stream(line.split(":")[1].split("\\s"))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .toList();

            nodes.put(name, children);
        }

        return nodes;
    }

    public static long partTwo(String input) {
        return 0;
    }
}
