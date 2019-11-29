package com.example.demo;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class Controller {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    String currentWord = null;
    String original = null;

    @RequestMapping(value = "/game", method = RequestMethod.POST, produces = "application/json")
    public Word greeting(@RequestBody WordChangeJava wordChange) {
        if (original == null) {
            original = Scrabble.generateWord();
            currentWord = Scrabble.scramble(new Random(),original);
        } else {
            currentWord = swap(currentWord, wordChange);
        }
        int percentage = percentage(currentWord, original);
        if (currentWord.equals(original)) {
            percentage = 100;
            original = null;
        }
        Word wordToReturn = new Word();
        wordToReturn.succesPercentage = percentage;
        wordToReturn.word = currentWord;
        return wordToReturn;
    }

    public String swap(String str, WordChangeJava wordChange) {
        StringBuilder sb = new StringBuilder(str);
        char l = sb.charAt(wordChange.getFirstLetter()), r = sb.charAt(wordChange.getLastLetter());
        sb.setCharAt(wordChange.getFirstLetter(), r);
        sb.setCharAt(wordChange.getLastLetter(), l);
        return sb.toString();
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

