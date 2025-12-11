package de.aoc.y2025;

import java.util.*;

public class Day11 {

    @FunctionalInterface
    interface PathAcceptor {
        boolean accept(List<String> path);
    }

    static Map<String, Long> cache;

    public static long partOne(String input) {
        var nodes = parseNodes(input);
        List<String> path = new ArrayList<>();
        path.add("you");
        cache = new HashMap<>();
        return findPaths(nodes, "you", "out", path, p -> true);
    }

    public static long partTwo(String input) {
        var nodes = parseNodes(input);
        cache = new HashMap<>();

        List<String> path = new ArrayList<>();
        path.add("svr");
        return findPaths(nodes, "svr", "out", path, path1 -> (path1.contains("fft") && path1.contains("dac")));
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

    private static long findPaths(Map<String, List<String>> nodes, String currentNode, String end, List<String> path, PathAcceptor acceptor) {
        long foundPaths = 0;

        if (currentNode.equals(end)) {
            return acceptor.accept(path) ? 1 : 0;
        }

        var children = nodes.get(currentNode);

        for (String child : children) {
            if (path.contains(child)) {
                continue;
            }

            path.add(child);
            String key = generateKey(path);

            if (cache.containsKey(key)) {
                foundPaths += cache.get(key);
            } else {
                long subPaths = findPaths(nodes, child, end, path, acceptor);
                cache.put(key, subPaths);
                foundPaths += subPaths;
            }
            path.remove(child);
        }

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
}
