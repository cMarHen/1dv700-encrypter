package dv700.encrypter.app;

import java.util.ArrayList;
import java.util.List;

import dv700.encrypter.encrypter.CaesarCipher;
import dv700.encrypter.encrypter.Encrypter;
import dv700.encrypter.fileHandler.TextReader;

public class Main {
  String[] arrFromFile;
  CaesarCipher caesarEncrypter;

  public Main() {
    TextReader exampleText = new TextReader("/src/main/java/dv700/encrypter/fileHandler/files/example.txt");
    this.arrFromFile = exampleText.readFromFile().split("\n");
    this.caesarEncrypter = new CaesarCipher(2, 'z', 'a'); 
  }


  public void run() {
    // Current setup is trying to descipher below encrypted text.
    String message = "XEM CKSX MEET MEKBT Q MEET SXKSA SXKSA YV Q MEET SXKSA SEKBT SXKSA MEET".toLowerCase();
    ArrayList<String> caesarString = getDecipheredMessage(26, message, caesarEncrypter);

    for (String s : caesarString) {
      if (isContainingValidWord(s)) {
        System.out.println(s);
      }
    }
  }

  private ArrayList<String> getDecipheredMessage(int cycles, String message, CaesarCipher encrypter) {
    ArrayList<String> arr = new ArrayList<String>();
    for (int i = 0; i < cycles; i++) {
      arr.add(encrypter.decrypt(i, message));
    }
    return arr;
  }

  private boolean isContainingValidWord(String sentence) {
    String[] s = sentence.split(" ");
    for (String w : s) {
      if (isValidWord(w)) {
        return true;
      }
    }
    return false;
  }

  private boolean isValidWord(String word) {
    for (String s : arrFromFile) {
      if (s.equals(word)) {
        return true;
      }
    }
    return false;
  }
}
