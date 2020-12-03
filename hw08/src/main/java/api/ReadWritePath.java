package api;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

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
        JSONObject response = (JSONObject) parser.parse(bufferedReader.readLine());
        JSONArray restaurants_array = (JSONArray) response.get("restaurants");
        for(Object r: restaurants_array)
        {
            JSONObject result = (JSONObject) r;
            Map cuisin = ((Map)result.get("restaurant"));
            Map cuisin2= ((Map)cuisin.get("user_rating"));
            Map cuisin3= ((Map)cuisin.get("location"));
            bw.write(cuisin3.get("city")+"---"+(String)cuisin.get("name")+"---"+(String)cuisin2.get("aggregate_rating")+"---"+(String)cuisin.get("timings")+"---"+(String)cuisin.get("cuisines")+"\n");
        }
        bw.close();
    }
}

