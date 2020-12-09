package api;

import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class ReadFromFile {

    private static final String fileName = "DATA.txt";
    private static final Charset CHARSET = Charset.forName(String.valueOf(StandardCharsets.ISO_8859_1));

    public ReadFromFile() {
    }

    public static String readData() throws IOException, ParseException {

        Path path = Path.of("src", "main", "resources").toAbsolutePath();
        if (Files.exists(path)) {
        } else {
            Files.createDirectories(path);
        }
        if (Files.exists(Path.of(path.toString(), fileName))) {
        } else {
            GettingDataFromApi.dataFromApi((Path.of(path.toString(), fileName)));
        }
        BufferedReader reader = Files.newBufferedReader(Path.of(path.toString(), fileName), CHARSET);
        return reader.lines().collect(Collectors.joining("\n"));
    }

}
