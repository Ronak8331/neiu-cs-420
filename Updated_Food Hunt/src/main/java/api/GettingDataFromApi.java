package api;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GettingDataFromApi {

    private GettingDataFromApi() {
    }

    public static void dataFromApi(Path path) throws IOException, ParseException {
        ZomatoApi api = new ZomatoApi();
        List<InputStream> streams = new ArrayList<>();
        List<Integer> startLimits = Arrays.asList(0, 20, 40, 60, 80);
        for (Integer startLimit : startLimits) {
            InputStream JSONData = api.connectionString(startLimit);
            streams.add(JSONData);
        }
        ReadAndWrite readWritePath = new ReadAndWrite(streams, (Path.of(path.toString())));
        readWritePath.writeOnFile();
    }
}
