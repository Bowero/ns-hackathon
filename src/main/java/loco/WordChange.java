package loco;

public class WordChange {
    public int firstLetter, secondLetter;

    public String swap(String str) {
      StringBuilder sb = new StringBuilder(str);
      char l = sb.charAt(firstLetter), r = sb.charAt(secondLetter);
      sb.setCharAt(firstLetter, r);
      sb.setCharAt(secondLetter, l);
      return sb.toString();
  }
}