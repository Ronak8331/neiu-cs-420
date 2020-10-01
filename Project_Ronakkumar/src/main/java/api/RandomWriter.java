package api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Map;

public class RandomWriter {

    private String path;
    private final BufferedWriter bw;
    JSONObject result;
    public RandomWriter (String path) throws FileNotFoundException {
        this.path=path;
        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.path)));
    }

    public void writeOnJson(InputStream input,String para, String arrName) throws IOException, ParseException, NullPointerException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        JSONParser parser = new JSONParser();
        JSONObject response = (JSONObject) parser.parse(bufferedReader.readLine());
        JSONArray rr = (JSONArray) response.get(arrName);
        for(Object r: rr)
        {
            result = (JSONObject) r;
            //System.out.println(result);
            //bw.write( result.get("cuisines") +" is the one of cities " + "\n");
            if(para=="cuisine")
            {
                Map cuisin = ((Map)result.get(para));
                bw.write((String)cuisin.get("cuisine_name")+"\n");
            }
            else
            {
                bw.write( result.get(para) + "\n");
            }

        }
        bw.close();
    }

    public long getCityId(){
        return (Long)(result.get("id"));
    }
}
