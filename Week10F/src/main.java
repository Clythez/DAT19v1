import java.util.ArrayList;

public class main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        ReadFromCSV filler = new ReadFromCSV();
        ArrayList<MovieInfo> print4 = filler.readFromCSV();
        // menu.searchForActor();
         menu.print4();
        // System.out.println(print4.get(484).toString());
    }
}
