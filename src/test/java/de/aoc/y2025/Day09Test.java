package de.aoc.y2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day09Test extends AoCTest {
    String input = """
            7,1
            11,1
            11,7
            9,7
            9,5
            2,5
            2,3
            7,3
            """;

    @Test
    public void testPartOne() {
        assertEquals(50, Day08.Day09.partOne(input));
        assertEquals(4763509452L, Day08.Day09.partOne(readInput()));
    }

    @Test
    public void testPartTwo() {
        assertEquals(25272, Day08.Day09.partTwo(input));
//        assertEquals(7264308110L, Day09.partTwo(readInput()));
    }
}
