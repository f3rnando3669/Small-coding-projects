import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
public class airconditioned {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        int minions = scanner.nextInt();
        scanner.nextLine();
        //we store temps in an array
        Minion[] minion = new Minion[minions];
        for(int i=0; i <minions;i++){
        int  lowerbound = scanner.nextInt();
        int  highbound = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character after reading two ints

            // Create a Minion object and store it in the array
            minion[i] = new Minion(lowerbound, highbound);
        }
         // Sort minions based on their upper bounds in ascending order
        Arrays.sort(minion, Comparator.comparingInt(m -> m.upperBound));

        int rooms = 0;
        int currentMaxUpperBound = Integer.MIN_VALUE;

        for (int i = 0; i < minions; i++) {
            // Check if the current minion needs a new room
            if (minion[i].lowerBound > currentMaxUpperBound) {
                rooms++;
                currentMaxUpperBound = minion[i].upperBound;
            }
        }
        System.out.println(rooms);


    }

}
class Minion {
    int lowerBound;
    int upperBound;

    Minion(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
}
