package api;

import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class generatePath {

    private final String path;
    private final InputStream input;

    public generatePath(String fileName, InputStream input) throws URISyntaxException {
        this.path = createFile(fileName);
        this.input = input;
    }

    public void dataWriter()
    {
        try {
            RandomWriter rw = new RandomWriter(this.path);
            //System.out.println(this.input);
            rw.writeOnJson(this.input);
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createFile(String fileName) throws URISyntaxException {
        String initialPath = ClassLoader.getSystemClassLoader().getResource("").toURI().getPath();
        initialPath = initialPath.substring(0,initialPath.indexOf("classes"));
        String filePath = initialPath + "resources" + File.separator;
        File directory = new File(filePath);
        if(!directory.exists())
            directory.mkdirs();
        return filePath + fileName;
    }

}
