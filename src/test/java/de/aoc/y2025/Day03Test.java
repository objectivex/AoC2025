package de.aoc.y2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test extends AoCTest {
    String input = """
            987654321111111
            811111111111119
            234234234234278
            818181911112111
            """;

    @Test
    public void testPartOne() {
        assertEquals(357, Day03.partOne(input));
        assertEquals(17452, Day03.partOne(readInput()));
    }

    @Test
    public void testPartTwo() {
        assertEquals(434234234278L, Day03.partTwo("234234234234278"));
        assertEquals(3121910778619L, Day03.partTwo(input));
        assertEquals(173300819005913L, Day03.partTwo(readInput()));
    }
}
