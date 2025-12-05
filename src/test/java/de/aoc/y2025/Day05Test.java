package de.aoc.y2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test extends AoCTest {
    String input = """
            3-5
            10-14
            16-20
            12-18
            
            1
            5
            8
            11
            17
            32
            """;

    @Test
    public void testPartOne() {
        assertEquals(3, Day05.partOne(input));
        assertEquals(517, Day05.partOne(readInput()));
    }

    @Test
    public void testPartTwo() {
        assertEquals(14, Day05.partTwo(input));
        assertEquals(20, Day05.partTwo("""
                10-14
                16-18
                1-20"""));
        assertEquals(21, Day05.partTwo("""
                10-14
                16-18
                12-15
                5-20
                2-22
                """));
//        assertEquals(21, Day05.partTwo("""
//                100-200
//                20-50
//                110-210
//                10-22
//
//                """));
        assertEquals(9000, Day05.partTwo(readInput()));
    }
}
