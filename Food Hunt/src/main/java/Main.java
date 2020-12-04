import api.ReadWritePath;
import api.ZomatoApi;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ZomatoApi api = new ZomatoApi();
            List<InputStream> streams = new ArrayList<>();
            List<Integer> startLimits = Arrays.asList(0, 20, 40, 60, 80);
            for (Integer startLimit : startLimits) {
                InputStream JSON = api.makeConnection(startLimit);
                streams.add(JSON);
            }
            ReadWritePath readWritePath = new ReadWritePath("DATA.txt", streams);
            readWritePath.writeJSON();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
