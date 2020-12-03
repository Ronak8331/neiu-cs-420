package api;
import javax.ws.rs.HttpMethod;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ZomotoApi {

    URL url;
    private final int connectTime = 5000;
    private HttpURLConnection connection = null;

    public ZomotoApi() {
    }

    public InputStream makeConnection() throws IOException {

        url=getRestaurantURL();
        connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(connectTime);
        connection.setRequestMethod(HttpMethod.GET);
        connection.setRequestProperty("X-Zomato-API-Key", System.getenv("API_KEY"));
        connection.setRequestProperty("Accept", "application/json");
        System.out.println(connection.getResponseCode());
        if(connection.getResponseCode() == 200)
        {
            final InputStream inputStream = connection.getInputStream();
            return inputStream;
        }
        return null;
    }

    private URL getRestaurantURL() throws MalformedURLException {

        String urlString2 ="https://developers.zomato.com/api/v2.1/search?entity_type=city&count=20";
        URL url2 = new URL(urlString2);
        return url2;
    }
}
