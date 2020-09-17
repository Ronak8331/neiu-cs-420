package MyApp;

import api.generatePath;
import api.FoodHunt;

import java.io.InputStream;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        try{
            FoodHunt food = new FoodHunt("Ahmedabad");
            InputStream JSON = food.makeConnection();
            generatePath gp = new generatePath("readable.txt", JSON);
            gp.dataWriter();
        }catch (URISyntaxException e)
        {
            System.out.println(e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
