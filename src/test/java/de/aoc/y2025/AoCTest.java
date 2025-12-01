package de.aoc.y2025;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AoCTest {
    protected String readInput() {
        String file = getClass().getSimpleName().replace("Test", "") + ".txt";
        try {
            var r = getClass().getResource("/" + file);
            if (r != null) {
                return Files.readString(Path.of(r.toURI()));
            } else {
                throw new RuntimeException("There is no input file for the given day ('" + file + "')");
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
