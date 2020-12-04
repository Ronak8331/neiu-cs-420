package api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadWritePath extends CreatePath {

    private final BufferedWriter bw;
    private final List<InputStream> is;
    private final String fileName;
    private Path path;

    public ReadWritePath(String fileName, List<InputStream> is) throws IOException, URISyntaxException {
        this.fileName = fileName;
        this.is = is;
        this.bw = bufferedWriter();
    }

    public ReadWritePath(String fileName, List<InputStream> is, Path path) throws IOException {
        this.fileName = fileName;
        this.path = path;
        this.is = is;
        this.bw = bufferedWriter(path);
    }

    private BufferedWriter bufferedWriter(Path path) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(path);
        return bw;
    }

    private BufferedWriter bufferedWriter() throws IOException, URISyntaxException {
        super.setFilePath(this.fileName);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(super.getFilePath())));
        return bw;
    }

    public void writeJSON() throws IOException, ParseException {
        for (InputStream i : is) {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(i));
            JSONParser parser = new JSONParser();
            JSONObject response = (JSONObject) parser.parse(bufferedReader.readLine());
            JSONArray restaurants_array = (JSONArray) response.get("restaurants");
            for (Object r : restaurants_array) {
                JSONObject result = (JSONObject) r;
                JSONObject cuisine = (JSONObject) result.get("restaurant");
                String city = String.valueOf(((JSONObject) cuisine.get("location")).get("city"));
                String name = String.valueOf(((JSONObject) result.get("restaurant")).get("name"));
                String rating = String.valueOf(((JSONObject) cuisine.get("user_rating")).get("aggregate_rating"));
                String timing = String.valueOf(((JSONObject) result.get("restaurant")).get("timings"));
                String cu = String.valueOf(((JSONObject) result.get("restaurant")).get("cuisines"));
                if (!(city.isEmpty()) && !(name.isEmpty()) && !(rating.isEmpty()) && !(timing.isEmpty()) && !(cu.isEmpty()))
                    bw.append(city + "---" + name + "---" + rating + "---" + timing + "---" + cu + "\n");
            }
        }
        bw.close();
    }
}

