package de.aoc.y2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test extends AoCTest {
    String input = """
            ..@@.@@@@.
            @@@.@.@.@@
            @@@@@.@.@@
            @.@@@@..@.
            @@.@@@@.@@
            .@@@@@@@.@
            .@.@.@.@@@
            @.@@@.@@@@
            .@@@@@@@@.
            @.@.@@@.@.
            """;

    @Test
    public void testPartOne() {
        assertEquals(13, Day04.partOne(input));
        assertEquals(1389, Day04.partOne(readInput()));
    }

    @Test
    public void testPartTwo() {
//        assertEquals(43, Day04.partTwo(input));
//        assertEquals(173300819005913L, Day04.partTwo(readInput()));
    }
}
