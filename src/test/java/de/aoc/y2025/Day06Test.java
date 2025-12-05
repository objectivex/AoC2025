package de.aoc.y2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test extends AoCTest {
    String input = """
            123 328  51 64
             45 64  387 23
              6 98  215 314
            *   +   *   +
            """;

    @Test
    public void testPartOne() {
        assertEquals(4277556, Day06.partOne(input));
        assertEquals(6957525317641L, Day06.partOne(readInput()));
    }

    @Test
    public void testPartTwo() {
        assertEquals(3263827, Day06.partTwo(input));
        assertEquals(336173027056994L, Day06.partTwo(readInput()));
    }
}
