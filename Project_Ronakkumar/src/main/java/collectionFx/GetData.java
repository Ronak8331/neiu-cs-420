package collectionFx;
import api.RandomWriter;
import api.generatePath;
import api.FoodHunt;
import MyApp.Main;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class GetData {

    private String fileName="";

    public GetData(String fileName) {
        this.fileName = fileName;
    }

    ArrayList<String> read_data = new ArrayList<>();

    private String getFile() throws URISyntaxException {
        String initialPath1 = ClassLoader.getSystemClassLoader().getResource("").toURI().getPath();
        initialPath1 = initialPath1.substring(0,initialPath1.indexOf("classes"));
        String filePath = initialPath1 + "resources" + File.separator;
        File directory = new File(filePath);
        if(!directory.exists())
            directory.mkdirs();
        return filePath + this.fileName;
    }
    public ArrayList<String> getDataFromFile() throws URISyntaxException, IOException {
        File file = new File(getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String s;
        while((s=bufferedReader.readLine())!=null)
        {
            read_data.add(s);
        }
      return read_data;
    }
}
