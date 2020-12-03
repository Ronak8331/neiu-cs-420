package candyselection.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class ReadFromFile {

    private ReadFromFile(){}

    private static final String fileName = "candy-ranking-2017.txt";

    public static String readData() throws IOException {
        Path path = getFileFromPath();
        BufferedReader reader = Files.newBufferedReader(path);
        return reader.lines().collect(Collectors.joining("\n"));
    }

    private static Path getFileFromPath() {
        Path path = Path.of("src","main","resources").toAbsolutePath();
        return Path.of(path.toString(),fileName);
    }
}
