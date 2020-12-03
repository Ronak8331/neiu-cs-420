package api;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class CreatePath {

    private String path;
    public CreatePath() {
    }

    public void setFilePath(String fileName) throws URISyntaxException {
        this.path = createPath(getFullPath(),fileName);
    }
    public String getFilePath() {
        return this.path;
    }

    private String createPath(String path, String fileName)
    {
        File directory = new File(path);
        if(!directory.exists()) {
            directory.mkdirs();
        }
        return path + fileName;
    }
    private String getFullPath() throws URISyntaxException {
        String initialPath = ClassLoader.getSystemClassLoader().getResource("").toURI().getPath();
        initialPath = initialPath.substring(0,initialPath.indexOf("classes"));
        String finalPath = initialPath + Path.of("main","resources").toAbsolutePath().toString();
        return finalPath;
    }
}
