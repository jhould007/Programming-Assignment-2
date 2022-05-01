import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hotel {
    public static void main(String[] args) throws FileNotFoundException {

        // Get and process the input
        File f = new File(args[0]);
        Scanner sc = new Scanner(f);
        int m = Integer.parseInt(sc.nextLine());
        // System.out.println("Optimal number of miles to travel in a day: " + m);
        int[] locations = convertToIntArr(sc.nextLine().split("\\s+"));
        // System.out.println("Hotel locations: " + Arrays.toString(locations));

        // Calculate the optimal path
        findOptimalRoute(locations, m);

        sc.close(); 
    }

    // Method for calculating the optimal path and total penalty
    public static void findOptimalRoute(int[] locations, int m) {
        int[] path = new int[locations.length];
        int[] penalties = new int[locations.length];

        for (int i = 0; i < locations.length; i++) {
            penalties[i] = (int) Math.pow(m - locations[i], 2);
            path[i] = 0;
            for (int j = 0; j < i; j++) {
                int temp = penalties[j] + (int) Math.pow((m - (locations[i] - locations[j])), 2);
                if (temp < penalties[i]) {
                    penalties[i] = temp;
                    path[i] = j + 1;
                }
            }
        }

        ArrayList<Integer> finalPath = new ArrayList<Integer>();
        int index = path.length - 1;

        while (index >= 0) {
            finalPath.add(0, index + 1);
            index = path[index] - 1;
        }

        // Remove the final hotel from the optimal path, since it is common across all
        // optimal paths and is not intermediate
        // finalPath.remove(finalPath.size() - 1);

        System.out.println("The optimal path is " + finalPath);
        System.out.println("Minimum penalty is " + penalties[locations.length - 1]);
    }

    // Method to add a value to the front of an array
    // MAY BE UNNECESSARY
    public static int[] addFront(int[] arr, int value) {
        int[] newArr = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i + 1] = arr[i];
        }
        newArr[0] = value;
        return newArr;
    }

    // Method for converting a String array into an int array
    public static int[] convertToIntArr(String[] sArr) {
        int[] arr = new int[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }
        return arr;
    }
}
