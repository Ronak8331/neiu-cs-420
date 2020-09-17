package api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class RandomWriter {

    private String path;
    private final BufferedWriter bw;

    public RandomWriter (String path) throws FileNotFoundException {
        this.path=path;
        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.path)));
    }

    public void writeOnJson(InputStream input) throws IOException, ParseException, NullPointerException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        JSONParser parser = new JSONParser();
        JSONObject response = (JSONObject) parser.parse(bufferedReader.readLine());
        JSONArray rr = (JSONArray) response.get("location_suggestions");
        for(Object r: rr)
        {
            JSONObject result = (JSONObject) r;
            bw.write( result.get("name") +" is on "+ result.get("name") + "\n");
        }
        bw.close();
    }

}
