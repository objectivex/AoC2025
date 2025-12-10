package de.aoc.y2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test extends AoCTest {
    String input = """
            [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
            [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
            [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
            """;

    @Test
    public void testPartOne() {
//        assertEquals(2, Day10.partOne("[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}"));
//        assertEquals(3, Day10.partOne("[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}"));
//        assertEquals(2, Day10.partOne("[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}"));
//        assertEquals(7, Day10.partOne(input));
        assertEquals(4763509452L, Day10.partOne(readInput()));
    }

    @Test
    public void testPartTwo() {
        assertEquals(24, Day10.partTwo(input));
//        assertEquals(1516897893, Day10.partTwo(readInput()));

        // < 4605538168
    }
}
