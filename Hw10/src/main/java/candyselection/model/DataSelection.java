package candyselection.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataSelection {

    private static List<CandySelection> candy;
    private static int length;
    private static String[] data;
    private static boolean first, second, third, fourth, fifth, sixth, seventh, eighth;

    public static List<CandySelection> candy() throws IOException {
        String readData = ReadFromFile.readData();
        candy = new ArrayList<>();
        length = readData.split("\n").length;
        for (int i = 0; i < (length - 1); i++) {
            writeData(i + 1, readData);
        }
        return candy;
    }

    private static void writeData(int i, String readData)
    {
        data = readData.split("\n")[i].split(",");
        first = forBoolean(data[1]);
        second = forBoolean(data[2]);
        third = forBoolean(data[3]);
        fourth = forBoolean(data[4]);
        fifth = forBoolean(data[5]);
        sixth = forBoolean(data[6]);
        seventh = forBoolean(data[7]);
        eighth = forBoolean(data[8]);
        toAddData(first, second, third, fourth, fifth, sixth, seventh, eighth);
    }

    private static void toAddData(boolean first, boolean second, boolean third, boolean fourth, boolean fifth, boolean sixth, boolean seventh, boolean eighth) {
        candy.add(new CandySelection(data[0], first, second, third,
                fourth, fifth, sixth, seventh,
                eighth, Float.parseFloat(data[10]), Float.parseFloat(data[11]),
                Float.parseFloat(data[12])));
    }

    private static boolean forBoolean(String value)
    {
        if (value.equals("0")){
            return false;
        }
        else{
            return true;
        }
    }
}
