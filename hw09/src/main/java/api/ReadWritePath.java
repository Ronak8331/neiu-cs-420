package api;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadWritePath extends CreatePath{

    private final BufferedWriter bw;
    private final InputStream is;
    private final String fileName;
    private Path path;

    public ReadWritePath (String fileName,InputStream is) throws IOException, URISyntaxException {
        this.fileName = fileName;
        this.is = is;
        this.bw = bufferedWriter();
    }
    public ReadWritePath(String fileName,InputStream is, Path path) throws IOException {
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
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject response = (org.json.simple.JSONObject) parser.parse(bufferedReader.readLine());
        JSONArray restaurants_array = (org.json.simple.JSONArray) response.get("restaurants");
        for(Object r: restaurants_array)
        {
            JSONObject result = (JSONObject) r;
            JSONObject cuisine = (JSONObject) result.get("restaurant");
            String city = (String) ((JSONObject) cuisine.get("location")).get("city");
            String name = (String) ((JSONObject) result.get("restaurant")).get("name");
            String rating = (String) ((JSONObject) cuisine.get("user_rating")).get("aggregate_rating");
            String timing = (String) ((JSONObject) result.get("restaurant")).get("timings");
            String cu = (String) ((JSONObject) result.get("restaurant")).get("cuisines");
            bw.write(city+"---"+name+"---"+rating+"---"+timing+"---"+cu+"\n");
        }
        bw.close();
    }
}

