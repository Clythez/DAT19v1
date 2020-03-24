import java.util.ArrayList;
import java.util.Scanner;

public class main {

    static Scanner input;

    public static void main(String[] args) {
        input = new Scanner(System.in);
        ReadJSON readJSON = new ReadJSON();


        System.out.println("Menu: ");
        System.out.println("1: See total number of Keys and their names");
        System.out.println("2: Print Country and Population for every year 2000-2016 ");
        System.out.println("3: Print Country and Population only with the latest information");
        System.out.println("4: Print to new file ");
        System.out.println("5: Exit: ");

        int choice = input.nextInt();
        if (choice > 0 && choice < 6) {
            menuSelection(readJSON, choice);
        } else {
            System.out.println("Please input a number between 1-5");
            choice = input.nextInt();
            menuSelection(readJSON, choice);
        }

    }

    private static void menuSelection(ReadJSON readJSON, int choice) {
        switch (choice) {
            case 1:
                readJSON.numberOfKeys();
                break;
            case 2:
                readJSON.printVital();
                break;
            case 3:
                ArrayList<Country> vitaUpdated = readJSON.getLatestResult();
                for (Country country : vitaUpdated) {
                    System.out.printf("Country: %-50s\t\t\t Year: %-20s\t\t\t\t Population: %-70s\n", country.getCountryName(), country.getYear(), country.getTotalPopulation());
                }
                break;
            case 4:
                readJSON.writeToFile();
                break;
            case 5:
                System.exit(0);
        }
    }

}
