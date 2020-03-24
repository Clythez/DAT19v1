import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReadJSON {

    private static String filePath = "unece.json";


    public int numberOfKeys () {
        int numberOfKeys = 0;
        try (FileReader reader = new FileReader(filePath)) {

            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) object;
            numberOfKeys = jsonArray.size();

            JSONObject jsonObject = (JSONObject) jsonArray.get(0);

            System.out.println(jsonObject.keySet());
            Set<String> keySet = jsonObject.keySet();
            System.out.println(keySet.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfKeys;
    }

    public void printVital() {

        try (FileReader reader = new FileReader(filePath)) {

            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) object;

            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;

                System.out.println("|-----------------------|");
                System.out.println(jsonObject.get("Country"));
                System.out.println(jsonObject.get("Year"));
                System.out.println(jsonObject.get("Total population"));
                System.out.println("|-----------------------|");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Country> getArrayList() {

        ArrayList<Country> noRepeatList = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {

            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) object;

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);

                String countryName = (String) jsonObject1.get("Country");
                String year = (String) jsonObject1.get("Year");
                String totalPopulation = String.valueOf(jsonObject1.get("Total population"));

                Country country1 = new Country(countryName, year, totalPopulation);

                noRepeatList.add(country1);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return noRepeatList;
    }

    public ArrayList<Country> getLatestResult() {

        ArrayList<Country> vitalInfo = new ArrayList<>();
        // ArrayList<Country> noRepeatList = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {

            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) object;

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);

                String countryName = (String) jsonObject1.get("Country");
                String year = (String) jsonObject1.get("Year");
                String totalPopulation = String.valueOf(jsonObject1.get("Total population"));

                Country country1 = new Country(countryName, year, totalPopulation);

                vitalInfo.add(country1);
                // noRepeatList.add(country1);
            }

            System.out.println(vitalInfo.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        sortArray(vitalInfo);
        System.out.println(vitalInfo.size());
        return vitalInfo;
        // return noRepeatList; --> List uden nogen removed elements
    }

    private void sortArray(ArrayList<Country> vitalInfo) {
        for (int i = 1; i < vitalInfo.size(); i++) {
            int year = Integer.parseInt(vitalInfo.get(i).getYear());
            int year1 = Integer.parseInt(vitalInfo.get(i-1).getYear());

            String country = vitalInfo.get(i).getCountryName();
            String country1 = vitalInfo.get(i-1).getCountryName();

            if (year > year1 && country.equals(country1)) {
                vitalInfo.get(i).setTotalPopulation(vitalInfo.get(i-1).getTotalPopulation());
                vitalInfo.remove(i-1);
                i -= 1;
            }
        }
    }

    public void writeToFile() {

        try (FileReader reader = new FileReader(filePath))  {
            FileWriter fileWriter = new FileWriter("info.json");

            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) object;

            JSONArray infoArray = new JSONArray();

            for (Object o : jsonArray) {
                JSONObject jsonObject1 = (JSONObject) o;
                JSONObject temp = new JSONObject();

                String key1 = "Country";
                String key2 = "Year";
                String key3 = "Total population";

                String countryName = (String) jsonObject1.get(key1);
                String year = (String) jsonObject1.get(key2);
                String totalPopulation = String.valueOf(jsonObject1.get(key3));

                temp.put(key1, countryName);
                temp.put(key2, year);
                temp.put(key3, totalPopulation);

                infoArray.add(temp);

            }

            fileWriter.write(infoArray.toJSONString());
            fileWriter.close();
            System.out.println("Succesfully wrote to file");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
