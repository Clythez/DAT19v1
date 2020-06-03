import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] numbers = createArray();

        System.out.println("Unsorted array with 20 random numbers: ");
        System.out.println(Arrays.toString(numbers));

        insertionSort(numbers);

        System.out.println("Sorted array: ");
        System.out.println(Arrays.toString(numbers));


    }

    static void insertionSort(int[] numbers) {
        int counter = 0;

        for (int i = 1; i < numbers.length; i++) {
            int current = numbers[i]; // Number we might move
            int j = i - 1; // Number before current

            while (j >= 0 && numbers[j] > current) { // As long as number before current is greater, move current forward by 1
                numbers[j + 1] = numbers[j];
                j = j - 1;
                counter++;
            }
            numbers[j + 1] = current;

        }
        System.out.println("\nTotal required steps to sort: " + counter + "\n");
    }

    static int[] createArray() {
        int[] numbers = new int[20];
        Random rng = new Random();

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rng.nextInt(100);
        }
        return numbers;
    }
}
