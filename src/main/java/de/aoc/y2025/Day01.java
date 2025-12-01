package de.aoc.y2025;

public class Day01 {
    public static int partOne(String input) {
        var lines = input.lines().map(String::trim).toList();

        int dial = 50;
        int zeroes = 0;

        for (String line : lines) {
            var direction = line.substring(0, 1);
            var distance = Integer.parseInt(line.substring(1));

            if (direction.equals("L")) {
                distance = -distance;
            }
            dial = (dial + distance) % 100;

            if (dial < 0) {
                dial = 100 + dial;
            }
            if (dial == 0) {
                zeroes++;
            }
        }
        return zeroes;
    }

    public static int partTwo(String input) {
        var lines = input.lines().map(String::trim).toList();

        int dial = 50;
        int zeroes = 0;

        for (String line : lines) {
            var direction = line.substring(0, 1);
            var distance = Integer.parseInt(line.substring(1));

            for (int i = 0; i < distance; i++) {
                if (direction.equals("L")) { // Move dial to left
                    dial--;
                    if (dial == 0 && i + 1 == distance) {
                        zeroes++;
                    }
                    if (dial < 0) {
                        if (i > 0) {
                            zeroes++;
                        }
                        dial = 99;
                    }
                } else if (direction.equals("R")) { //Move dial to right
                    dial++;
                    if (dial == 100) {
                        zeroes++;
                        dial = 0;
                    }
                }
            }

//            zero += z;

        }
        return zeroes;
    }
}
