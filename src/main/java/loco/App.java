package loco;

import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        Random rand = new Random();

        Word wordObject = new Word();
        String word = Scrabble.generateWord();
        String newWord = Scrabble.scramble(rand, word);
        System.out.println(word);

        while (true) {
            int n = rand.nextInt(7);
            int n2 = rand.nextInt(7);
            String jsonString = "{\"firstLetter\":" + n + ", \"secondLetter\":" + n2 + "}";
            try {
                WordChange change = mapper.readValue(jsonString, WordChange.class);
                newWord = change.swap(word);
                System.out.println(newWord);
                wordObject.word = newWord;
                try {
                    String json = mapper.writeValueAsString(wordObject);
                    System.out.println(json);
                    System.out.println(Scrabble.percentage(newWord, word) + "%");
                } catch (Exception e) {
                    System.err.println(e);
                }
                
            } catch (Exception e) {
                System.err.println(e);
            }

            /* Check if word is correct */
            if (newWord.equals(word)) {
                System.out.println("Game has been won!");
                System.exit(1);
            }
        }
    }
}