package collectionFx;
import api.*;
import MyApp.*;

import java.util.ArrayList;

public class CityNames {

    ArrayList<String> cityNames = new ArrayList<>();
    public ArrayList<String> getCity()
    {
        cityNames.add("Pune");
        cityNames.add("Ahmedabad");
        cityNames.add("Mumbai");

        return cityNames;
    }


}
