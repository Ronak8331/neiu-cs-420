package api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.ws.rs.HttpMethod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class FoodHunt {

    private final int connectTime = 1000;
    private HttpURLConnection connection = null;
    private String city;
    //private String restaurant;

    public FoodHunt()
    {
        //this.city = city;
        //this.restaurant = restaurant;

    }

    public InputStream makeConnection(String pathOfApi , String query) throws IOException {
        URL url = getApiURL(pathOfApi,query);
        connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(connectTime);
        connection.setRequestMethod(HttpMethod.GET);
        connection.setRequestProperty("X-Zomato-API-Key", System.getenv("API_KEY"));
        connection.setRequestProperty("Accept", "application/json");
        //System.out.println(connection.getResponseCode());
        if(connection.getResponseCode() == 200)
        {
            final InputStream inputStream = connection.getInputStream();
            return inputStream;
        }
        return null;
    }

    private URL getApiURL(String pathOfApi, String Query) throws MalformedURLException
    {
        String urlString = pathOfApi+Query;
        //System.out.println(urlString);
        URL url = new URL(urlString);
        return url;
    }

}
