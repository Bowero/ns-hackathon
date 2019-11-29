package loco;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Scrabble {

  public static String generateWord() {
    /* Generate a random word at the beginning of the game */
    List<String> wordList = new ArrayList<String>();

    try {
      Scanner scanner = new Scanner(new File("words.txt"));
      while (scanner.hasNext()) {
        wordList.add(scanner.nextLine());
      }
      scanner.close();
    } catch (Exception e) {
      System.err.println(e);
    }
    Collections.shuffle(wordList);
    return wordList.get(0).toLowerCase();
  }

  public static String scramble(Random random, String inputString) {
    // Convert your string into a simple char array:
    char a[] = inputString.toCharArray();

    // Scramble the letters using the standard Fisher-Yates shuffle,
    for (int i = 0; i < a.length; i++) {
      int j = random.nextInt(a.length);
      // Swap letters
      char temp = a[i];
      a[i] = a[j];
      a[j] = temp;
    }

    return new String(a);
  }

  public static int percentage(String inputString, String originalWord) {
    int perc = 0;
    
    for (int i = 0; i < inputString.length(); i++) {
      char c = inputString.charAt(i);
      if (c == originalWord.charAt(i)) {
        perc += Math.round(100 / inputString.length());
      }
    }
    return perc;
  }
}