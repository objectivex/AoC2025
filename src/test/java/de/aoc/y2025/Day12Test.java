package de.aoc.y2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test extends AoCTest {
    String input = """
            0:
            ###
            ##.
            ##.
            
            1:
            ###
            ##.
            .##
            
            2:
            .##
            ###
            ##.
            
            3:
            ##.
            ###
            ##.
            
            4:
            ###
            #..
            ###
            
            5:
            ###
            .#.
            ###
            
            4x4: 0 0 0 0 2 0
            12x5: 1 0 1 0 2 2
            12x5: 1 0 1 0 3 2
            """;

    @Test
    public void testPartOne() {
        assertEquals(2, Day12.partOne(input));
        assertEquals(534, Day12.partOne(readInput()));
    }
}
