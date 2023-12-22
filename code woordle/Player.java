import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class Player {
    static StringFixedArrayList mylist = new StringFixedArrayList(15000);
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
      File inputFile = new File("/Users/user/Documents/code woordle/wordle-nd.txt");
      Scanner scan = null;

      try {
        scan = new Scanner(inputFile);
      } catch (FileNotFoundException e) {
        System.err.println(e);
        System.exit(1);
      } 
      int count = 0;
      while (scan.hasNext()) {
        String word = scan.next();
        mylist.add(word);
        count++;
      } 

        System.out.println("Do you want me to choose a word?");
        System.out.print("Enter (1) for me to pick, or (2) to let someone enter the word: ");
        int option = input.nextInt();

        String secretWord = "";
        if (option == 1) {
          Random rn = new Random();
          option = rn.nextInt(mylist.length());
          secretWord = mylist.get(option);
          System.out.println("I've chosen a word...");
        } else {
          secretWord = findWord("Enter a secret word");
          for (int i = 0; i < 100; i++)
            System.out.println(); 
          System.out.println("OK. The word has now been chosen. Good luck!");
        }

        int guesses = 1;
        while (guesses < 7) {
          String guess = findWord("Enter guess #" + guesses);
          String clue = printGuessWord(secretWord, guess);
          System.out.println(clue);
          if (secretWord.equals(guess))
            break; 
          guesses++;
        }
         
        if (guesses < 7) {
          System.out.println("You did it! Good job.");
        } else {
          System.out.println("Too bad: you didn't guess the word.");
          System.out.println("The word was '" + secretWord + "'.");
          System.out.println("Better luck next time!");
        } 

       
    }

    //made a new function for findWord
    //tells if word is too short, too long, or not in the list
    //checks for the length of each length of guess
    public static String findWord(String prompt) {
        String guess = "";
        boolean done = false;
        while (!done) {
            System.out.print(String.valueOf(prompt) + ": ");
            guess = input.next();
            if (guess.length() < 5) {
                System.out.println("That word is too short. Try again.");
                continue;
            }

            if (guess.length() > 5) {
                System.out.println("That word is too long. Try again.");
                continue;
            }

            if (!mylist.contains(guess)) {
                System.out.println("That word is not in the allowed list. Try again.");
                continue;
            }
            done = true;           
        }
        return guess;
    }

    public static String printGuessWord (String secretWord, String guess) {
        String[] clue = { "", "", "", "", "" };
        String[] secretArray = secretWord.split("");
        String[] guessArray = guess.split("");
        
    int i;
    for ( i = 0; i < 5; i++) {
      if (secretArray[i].equals(guessArray[i])) {
        clue[i] = "|" + secretArray[i] + "|";
        secretArray[i] = "";
        guessArray[i] = "";
      } 
    } 
    for ( i = 0; i < 5; i++) {
      if (!guessArray[i].isEmpty())
        for (int k = 0; k < 5; k++) {
          if (!secretArray[k].isEmpty())
            if (guessArray[i].equals(secretArray[k])) {
              clue[i] = "*" + guessArray[i] + "*";
              guessArray[i] = "";
              secretArray[k] = "";
            }  
        }  
    } 
    for ( i = 0; i < 5; i++) {
      if (!guessArray[i].isEmpty())
        clue[i] = " " + guessArray[i] + " "; 
    } 
    String retval = clue[0];
    for (int j = 1; j < 5; j++)
      retval = String.valueOf(retval) + " " + clue[j]; 
    return retval;
  }
  
  public static String getWord(String prompt) {
    String guess = "";
    boolean done = false;
    while (!done) {
      System.out.print(String.valueOf(prompt) + ": ");
      guess = input.next();
      if (guess.length() < 5) {
        System.out.println("That word is too short. Try again.");
        continue;
      } 
      if (guess.length() > 5) {
        System.out.println("That word is too long. Try again.");
        continue;
      } 
      if (!mylist.contains(guess)) {
        System.out.println("That word is not in the allowed list. Try again.");
        continue;
      } 
      done = true;
    } 
    return guess;
}
}


