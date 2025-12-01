package de.aoc.y2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test extends AoCTest {
    @Test
    public void testPartOne() {
        String input = """
              L68
              L30
              R48
              L5
              R60
              L55
              L1
              L99
              R14
              L82
              """;
        assertEquals(3, Day01.partOne(input));
        assertEquals(1180, Day01.partOne(readInput()));
    }

    @Test
    public void testPartTwo() {
        String input = """
              L68
              L30
              R48
              L5
              R60
              L55
              L1
              L99
              R14
              L82
              """;
        assertEquals(6, Day01.partTwo(input));
        assertEquals(6892, Day01.partTwo(readInput()));
    }
}
