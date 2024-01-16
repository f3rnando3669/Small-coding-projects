import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class abc {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);

        // Read input numbers
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();

        // Read desired order
        String order = scanner.next();

        // Create a mapping
        Map<Character, Integer> mapping = new HashMap<>();
        mapping.put('A', Math.min(x, Math.min(y, z)));
        mapping.put('B', Arrays.stream(new int[]{x, y, z}).sorted().toArray()[1]);
        mapping.put('C', Math.max(x, Math.max(y, z)));

        // Sort the numbers based on the mapping
        int[] result = new int[]{mapping.get(order.charAt(0)), mapping.get(order.charAt(1)), mapping.get(order.charAt(2))};

        // Print the result
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }
}
    