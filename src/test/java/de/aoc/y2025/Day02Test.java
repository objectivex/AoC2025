package de.aoc.y2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test extends AoCTest {
    String input = """
            11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124
            """;

    @Test
    public void testPartOne() {
        assertEquals(1227775554, Day02.partOne(input));
        assertEquals(31000881061L, Day02.partOne(readInput()));
    }

    @Test
    public void testPartTwo() {
        assertEquals(4174379265L, Day02.partTwo(input));
        assertEquals(46769308485L, Day02.partTwo(readInput()));
    }
}
