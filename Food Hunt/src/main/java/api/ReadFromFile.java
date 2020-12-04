package api;

import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFromFile {

    private static final String fileName = "DATA.txt";
    private static final Charset CHARSET = Charset.forName(String.valueOf(StandardCharsets.ISO_8859_1));
    private static List<InputStream> streams;

    public ReadFromFile() {
    }

    public static String readData() throws IOException, ParseException {
        Path path = getFileFromPath();
        BufferedReader reader = Files.newBufferedReader(path, CHARSET);
        return reader.lines().collect(Collectors.joining("\n"));
    }

    private static Path getFileFromPath() throws IOException, ParseException {

        Path path = Path.of("src", "main", "resources").toAbsolutePath();
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        if (!Files.exists(Path.of(path.toString(), fileName))) {
            createFileForData(Path.of(path.toString(), fileName));
        }
        return Path.of(path.toString(), fileName);
    }

    private static void createFileForData(Path path) throws IOException, ParseException {
        ZomatoApi api = new ZomatoApi();
        streams = new ArrayList<>();
        List<Integer> startLimits = Arrays.asList(0, 20, 40, 60, 80);
        for (Integer startLimit : startLimits) {
            InputStream JSON = api.makeConnection(startLimit);
            streams.add(JSON);
        }
        ReadWritePath readWritePath = new ReadWritePath("DATA.txt", streams, path);
        readWritePath.writeJSON();
    }
}
