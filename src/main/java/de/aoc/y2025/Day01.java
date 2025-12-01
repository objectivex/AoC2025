package de.aoc.y2025;

public class Day01 {
    public static int partOne(String input) {
        var lines = input.lines().map(String::trim).toList();
        int dial = 50;
        int zero = 0;
        for (String line : lines) {
            var direction = line.substring(0, 1);
            var distance = Integer.parseInt(line.substring(1));

            if (direction.equals("L")) {
                distance = -distance;
            }
            dial = dial + distance;
            dial = dial % 100;
//            if (dial > 99) {
//                dial = dial - 100;
//            } else if (dial < 0 ) {
//                dial = dial + 100;
//            }

            if (dial == 0) {
                zero++;
            }

//            System.out.println(dial);

        }
        return zero;
    }

    public static int partTwo(String input) {
        var lines = input.lines().map(String::trim).toList();
        int dial = 50;
        int zero = 0;
        for (String line : lines) {
            var direction = line.substring(0, 1);
            var distance = Integer.parseInt(line.substring(1));

//            if (direction.d
            var z = 0;

            for (int i = 0; i < distance; i++) {
                if (direction.equals("L")) {
                    dial--;
                    if (dial == 0 && i + 1 == distance) {
                        zero++;
                    }
                    if (dial < 0) {
                        if (i > 0) {
                            zero++;
                        }
                        dial = 99;
                    }
                } else if (direction.equals("R")) {
                    dial++;
                    if (dial == 100) {
                        zero++;
                        dial = 0;
                    }
                }
            }

//            zero += z;

        }
        return zero;
    }
}
