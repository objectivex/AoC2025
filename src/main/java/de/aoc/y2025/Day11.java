package de.aoc.y2025;

import java.util.*;

public class Day11 {
    static class Node {
        String name;
        List<Node> nodes = new ArrayList<>();
    }

    //    static List<List<String>> possiblePath = new ArrayList<>();
    static int foundPath = 0;

    public static long partOne(String input) {
        var nodes = parseNodes(input);
        foundPath = 0;
        List<String> path = new ArrayList<>();
        path.add("you");
        findPath(nodes, "you", "out", new HashSet<>(), path);
        return foundPath;

    }


    private static void findPath(Map<String, List<String>> nodes, String currentNode, String end, Set<String> visited, List<String> path) {
        if (currentNode.equals(end)) {
            foundPath++;
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

    private static void findPath2(Map<String, List<String>> nodes, String currentNode, String end, Set<String> visited, List<String> path) {
        if (currentNode.equals(end)) {
            if (path.contains("fft") && path.contains("dac")) {
                foundPath++;
            }
            return;
        }

        visited.add(currentNode);

        var children = nodes.getOrDefault(currentNode, Collections.emptyList());

        for (String child : children) {
            if (visited.contains(child)) {
                continue;
            }

            path.add(child);
            findPath2(nodes, child, end, visited, path);
            path.remove(child);
        }

        visited.remove(currentNode);
    }

    public static long partTwo(String input) {
        var nodes = parseNodes(input);
        foundPath = 0;
        List<String> path = new ArrayList<>();
        path.add("svr");
        findPath2(nodes, "svr", "out", new HashSet<>(), path);
        return foundPath;
    }
}
