package de.aoc.y2025;

import java.util.*;

public class Day11 {
    static class Node {
        String name;
        List<Node> nodes = new ArrayList<>();
    }

    //    static List<List<String>> possiblePath = new ArrayList<>();
    static int foundPath = 0;
    static Map<String, Long> cache;

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

    private static long findPath2(Map<String, List<String>> nodes, String currentNode, String end, Set<String> visited, List<String> path) {
        long foundPaths = 0;

        if (currentNode.equals("out")) {
            if (path.contains("fft") && path.contains("dac")) {
                return 1;
            }
            return 0;
        }

        visited.add(currentNode);

        var children = nodes.getOrDefault(currentNode, Collections.emptyList());

        for (String child : children) {
            if (path.contains(child)) {
                continue;
            }

            path.add(child);
            String key = generateKey(path);

            if (cache.containsKey(key)) {
                foundPaths += cache.get(key);
            } else {
                long subPaths = findPath2(nodes, child, end, new HashSet<>(visited), path);
                cache.put(key, subPaths);
                foundPaths += subPaths;
            }
            path.remove(child);
        }

        visited.remove(currentNode);

        return foundPaths;
    }

    private static String generateKey(List<String> path) {
        String key = path.getLast();
        if (path.contains("fft")) {
            key += "fft";
        }
        if (path.contains("dac")) {
            key += "dac";
        }


        return key;
    }

    public static long partTwo(String input) {
        var nodes = parseNodes(input);
        foundPath = 0;
        cache = new HashMap<>();

        List<String> path = new ArrayList<>();
        path.add("svr");
        var x = findPath2(nodes, "svr", "out", new HashSet<>(), path);
        return x;
    }
}
