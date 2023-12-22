import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.36
// 

public class Main
{
    public static void main(final String[] args) {
        final Random rnd = new Random();
        System.out.println(rnd.nextInt() % 100);
        final StringFixedArrayList mylist = new StringFixedArrayList(15000);
        final File inputFile = new File("../Texts/wordle-random.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(inputFile);
        }
        catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }
        int count = 0;
        while (scan.hasNext()) {
            final String word = scan.next();
            mylist.add(word);
            ++count;
        }
        System.out.println("Read in " + count + " words.");
        for (int i = 0; i < mylist.length(); ++i) {
            final String s = mylist.get(i);
            if (s.contains("m") && s.contains("k") && !s.contains("i") && !s.contains("e") && !s.contains("y") && s.matches("[^m].[^k]..")) {
                System.out.println(s);
            }
        }
    }
}
