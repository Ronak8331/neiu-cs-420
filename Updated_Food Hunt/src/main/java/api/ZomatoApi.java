package api;

import javax.ws.rs.HttpMethod;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ZomatoApi {

    URL url;

    public ZomatoApi() {
    }

    public InputStream connectionString(int start) throws IOException {

        url = urlString(start);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int connectTime = 5000;
        connection.setConnectTimeout(connectTime);
        connection.setRequestMethod(HttpMethod.GET);
        connection.setRequestProperty("X-Zomato-API-Key", System.getenv("API_KEY"));
        connection.setRequestProperty("Accept", "application/json");
        if (connection.getResponseCode() == 200) {
            InputStream inputStream = connection.getInputStream();
            return inputStream;
        }
        return null;
    }

    private URL urlString(int start) throws MalformedURLException {

        String urlString2 = "https://developers.zomato.com/api/v2.1/search?entity_type=city&start=" + start + "&count=20";
        URL url2 = new URL(urlString2);
        return url2;
    }
}
