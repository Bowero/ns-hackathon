package com.example.demo;

import java.io.File;
import java.util.*;

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
}