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

    public FoodHunt(String city)
    {
        this.city = city;
        //this.restaurant = restaurant;
    }

    public InputStream makeConnection() throws IOException {
        URL url = getApiURL();
        connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(connectTime);
        connection.setRequestMethod(HttpMethod.GET);
        connection.setRequestProperty("X-Zomato-API-Key", System.getenv("API_KEY"));
        connection.setRequestProperty("Accept", "application/json");
        //System.out.println(connection.getResponseCode());
        if(connection.getResponseCode() == 200)
        {
            final InputStream inputStream = connection.getInputStream();
            //System.out.println(inputStream);
            return inputStream;
        }
        return null;
    }

    private URL getApiURL() throws MalformedURLException
    {
        String urlString = "https://developers.zomato.com/api/v2.1/cities"+"?q="+this.city;
        //System.out.println(urlString);
        URL url = new URL(urlString);
        return url;
    }






}
