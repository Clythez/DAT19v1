import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToCSV {

    FileWriter writeCSV;

    public void convertToCSV() {
        ReadFromCSV filler = new ReadFromCSV();
        MovieInfo movinfo = new MovieInfo();
        ArrayList<MovieInfo> writeList = filler.readFromCSV();

        try {

            writeCSV = new FileWriter("new.csv");

            for (MovieInfo movieInfo: writeList) {
                String dirName = movieInfo.getDirectorName();
                String actName = movieInfo.getActorName();
                String movYear = movieInfo.getYear();
                String movName = movieInfo.getMovieName();

                String toCSV = dirName + "," + actName + "," + movYear + "," + movName;

                writeCSV.write(toCSV + "\n");
            }

            writeCSV.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
