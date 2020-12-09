package api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadAndWrite {

    private final BufferedWriter bw;
    private final List<InputStream> is;

    public ReadAndWrite(List<InputStream> is, Path path) throws IOException {
        this.is = is;
        this.bw = Files.newBufferedWriter(path);
    }

    public void writeOnFile() throws IOException, ParseException {
        for (InputStream i : is) {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(i));
            JSONParser parser = new JSONParser();
            JSONObject response = (JSONObject) parser.parse(bufferedReader.readLine());
            JSONArray restaurants_array = (JSONArray) response.get("restaurants");
            convertDataToJSONArray(restaurants_array);
        }
        bw.close();
    }

    private void convertDataToJSONArray(JSONArray restaurants_array) throws IOException {
        for (Object r : restaurants_array) {
            JSONObject result = (JSONObject) r;
            JSONObject cuisine = (JSONObject) result.get("restaurant");
            String city = String.valueOf(((JSONObject) cuisine.get("location")).get("city"));
            String name = String.valueOf(((JSONObject) result.get("restaurant")).get("name"));
            String rating = String.valueOf(((JSONObject) cuisine.get("user_rating")).get("aggregate_rating"));
            String timing = String.valueOf(((JSONObject) result.get("restaurant")).get("timings"));
            String cu = String.valueOf(((JSONObject) result.get("restaurant")).get("cuisines"));
            String cost = String.valueOf(((JSONObject) result.get("restaurant")).get("average_cost_for_two"));
            String delivery = String.valueOf(((JSONObject) result.get("restaurant")).get("has_online_delivery"));
            if (!(city.isEmpty()) && !(name.isEmpty()) && !(rating.isEmpty()) && !(timing.isEmpty()) && !(cu.isEmpty()) && !(cost.isEmpty()) && !(delivery.isEmpty()))
                bw.append(city + "---" + name + "---" + rating + "---" + timing + "---" + cu + "---" + cost + "---" + delivery + "\n");
        }
    }
}

