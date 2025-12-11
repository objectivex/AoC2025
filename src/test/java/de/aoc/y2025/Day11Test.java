package de.aoc.y2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test extends AoCTest {
    String input = """
            aaa: you hhh
            you: bbb ccc
            bbb: ddd eee
            ccc: ddd eee fff
            ddd: ggg
            eee: out
            fff: out
            ggg: out
            hhh: ccc fff iii
            iii: out
            """;

    @Test
    public void testPartOne() {
        assertEquals(5, Day11.partOne(input));
        assertEquals(431, Day11.partOne(readInput()));
    }

    @Test
    public void testPartTwo() {
        assertEquals(2, Day11.partTwo("""
                svr: aaa bbb
                aaa: fft
                fft: ccc
                bbb: tty
                tty: ccc
                ccc: ddd eee
                ddd: hub
                hub: fff
                eee: dac
                dac: fff
                fff: ggg hhh
                ggg: out
                hhh: out
                """));
        assertEquals(358458157650450L, Day11.partTwo(readInput()));

        // > 187126290
    }
}
