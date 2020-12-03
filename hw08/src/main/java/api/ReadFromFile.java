package api;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFromFile {

    private static final String fileName = "DATA.txt";
    private static String line;
    private static String temp;
    private static final Charset CHARSET = Charset.forName(String.valueOf(StandardCharsets.ISO_8859_1));

    public ReadFromFile() {
    }

    public static String readData() throws IOException, ParseException {
        Path path = getFileFromPath();
        BufferedReader reader = Files.newBufferedReader(path, CHARSET);
        while ((line = reader.readLine()) != null) {
            temp = temp + line + "\n";
        }
        temp = temp.trim();
        return temp;
    }

    private static Path getFileFromPath () throws IOException, ParseException {

        Path path = Path.of("src","main","resources").toAbsolutePath();
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
        if(!Files.exists(Path.of(path.toString(),fileName))){
            createFileForData(Path.of(path.toString(),fileName));
        }
        return Path.of(path.toString(),fileName);
    }

    private static void createFileForData(Path path) throws IOException, ParseException {
        ZomotoApi api = new ZomotoApi();
        InputStream JSON = api.makeConnection();
        ReadWritePath readWritePath = new ReadWritePath("DATA.txt", JSON,path);
        readWritePath.writeJSON();
    }
}
