package dv700.cipherApp.decryption;

import java.util.ArrayList;
import java.util.HashMap;

public class SubstitutionDecrypter {
  public String letters = "abcdefghijklmnopqrstuvwxyz";
  private LetterCollection collection;
  HashMap<Character, Character> map;

  public SubstitutionDecrypter() {
    this.collection = new LetterCollection(letters);
    this.map = new HashMap<>();
  }

  public ArrayList<String> getSentences(String cipherText) {
    ArrayList<String> list = new ArrayList<>();
    cipherText = cipherText.toLowerCase();

    for (int i = 0; i < letters.length(); i++) {
      shiftNext();
      String decryptedSentence = "";
      for (char c : cipherText.toCharArray()) {
          decryptedSentence += map.getOrDefault(c, c);
      }

      list.add(decryptedSentence);
      decryptedSentence = "";
    }
    
    return list;
  }

  private void shiftNext() {
    ArrayList<Character> shiftedLetters = collection.shiftKeyForward();
    char[] originalLetters = letters.toCharArray();

    for (int i = 0; i < originalLetters.length; i++) {
      map.put(originalLetters[i], shiftedLetters.get(i));
    }
  }
}
