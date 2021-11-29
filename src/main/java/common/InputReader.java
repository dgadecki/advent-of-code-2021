package common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class InputReader {

    public List<String> readInput(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get("src/main/resources/" + fileName))) {
            return stream.toList();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return List.of();
    }
}
