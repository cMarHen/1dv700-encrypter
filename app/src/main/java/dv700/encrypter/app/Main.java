package dv700.encrypter.app;

import java.util.ArrayList;

import dv700.encrypter.encryption.Encrypter;
import dv700.encrypter.encryption.Hasher;
import dv700.encrypter.encryption.SubstitutionCipher;
import dv700.encrypter.fileHandler.TextReader;

public class Main {
  String[] arrFromFile;
  Encrypter<Integer> caesarEncrypter;

  public Main() {
    TextReader exampleText = new TextReader("/src/main/java/dv700/encrypter/fileHandler/files/example.txt");
    this.arrFromFile = exampleText.readFromFile().split("\n");
    this.caesarEncrypter = new SubstitutionCipher(); 
  }


  public void run() {

    // Run hasher
    /* Hasher hasher = new Hasher(256);

    String str = "a";
    for (int i = 0; i < 10; i++) {
      System.out.println("Hash: " + hasher.hash(str));
      str += "b";
    } */

    // String cipher = caesarEncrypter.encrypt(26, "hej Martin, jag heter martin och har ett efternamn som är ganska långt!");
    String cipher = caesarEncrypter.encrypt(4, "hello you pig!!");
    String plain = caesarEncrypter.decrypt(69, cipher);

    System.out.println(cipher);
    System.out.println(plain);



    // Demonstrate encryption and decryption using a shift key.
    // String zebre = caesarEncrypter.encrypt(3, "zebra"); // => "cheud"
    // System.out.println(caesarEncrypter.decrypt(3, zebre)); // => "zebra"

    // Trying to descipher below encrypted text.
    // Will print every line that has a "valid word", to be verified manually.
    /* String message = "XEM CKSX MEET MEKBT Q MEET SXKSA SXKSA YV Q MEET SXKSA SEKBT SXKSA MEET".toLowerCase();
    ArrayList<String> caesarString = getDecipheredMessage(26, message, caesarEncrypter);

    for (String s : caesarString) {
      if (isContainingValidWord(s)) {
        System.out.println(s);
      }
    } */
  }

  private ArrayList<String> getDecipheredMessage(int cycles, String message, Encrypter<Integer> encrypter) {
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
