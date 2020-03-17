import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromCSV {

    Scanner read;
    String filePath = "movie_metadata_500.csv";

    public ArrayList<MovieInfo> readFromCSV () {

        ArrayList<MovieInfo> movieInfo = new ArrayList<>();
        String[] values;

        try {
            read = new Scanner(new File(filePath));

            while (read.hasNextLine()) {
                String split = read.nextLine();
                values = split.split(",");

                if (values[1].equals("")) {
                    values[1] = "N/A";
                }
                if (values[1].equals("Florian Henckel von Donnersmarck")) {
                    values[1] = "Florian Donnersmarck";
                }
                if (values[11].startsWith("\"")) {
                    values[11] = values[11].substring(1, values[11].length());
                }
                if (values[23].equals("") || values[23].length() != 4) {
                    values[23] = "N/A";
                }
                if (values[25].length() != 3 || values[25].charAt(1) != '.') {
                    values[25] = "0.0";
                }

                movieInfo.add(new MovieInfo(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8],
                    values[9], values[10], values[11], values[12], values[13], values[14], values[15], values[16], values[17], values[18],
                    values[19], values[20], values[21], values[22], values[23], values[24], values[25], values[26], values[27]));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return movieInfo;
    }
}