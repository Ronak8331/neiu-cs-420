import api.ReadWritePath;
import api.ZomotoApi;
import org.json.simple.parser.ParseException;
import api.ReadFromFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args){
        try {
            ZomotoApi api = new ZomotoApi();
            InputStream JSON = api.makeConnection();
            ReadWritePath readWritePath = new ReadWritePath("DATA.txt", JSON);
            readWritePath.writeJSON();
            ReadFromFile.readData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
