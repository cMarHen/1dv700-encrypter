package dv700.cipherApp.app;

import java.util.ArrayList;
import java.util.Scanner;

import dv700.cipherApp.decryption.SubstitutionDecrypter;
import dv700.cipherApp.encryption.Encrypter;
import dv700.cipherApp.encryption.Hasher;
import dv700.cipherApp.encryption.SubstitutionCipher;
import dv700.cipherApp.filehandling.TextReader;
import dv700.cipherApp.ui.Console;

public class Main {
  private String[] arrFromFile;
  private Encrypter<Integer> substitutionEncrypter;
  private Hasher hasher;
  private Console ui;
  private Scanner scan;
  
  public Main() {
    TextReader exampleText = new TextReader("/src/main/java/dv700/cipherApp/filehandling/files/example.txt");
    this.arrFromFile = exampleText.readFromFile().split("\n");
    this.substitutionEncrypter = new SubstitutionCipher(); 
    this.hasher = new Hasher(256);
    this.scan = new Scanner(System.in, "UTF8");
    this.ui = new Console(scan);
  }


  public void run() {
    Boolean isRunning = true;
    do {
      MenuEvent event = ui.getMainMenuChoice();

      if (event == MenuEvent.TRANSPOSITION) {
        System.out.println("Trans..");
      }
      if (event == MenuEvent.SUBSTITUTION) {
        doSubstitutionMenu();
      }
      if (event == MenuEvent.HASH) {
        doHashMenu();
      }
      if (event == MenuEvent.QUIT) {
        isRunning = false;
      }
    } while (isRunning);

    /* SubstitutionDecrypter decipher = new SubstitutionDecrypter();
    ArrayList<String> listOfSentences = decipher.getSentences("XEM CKSX MEET MEKBT Q MEET SXKSA SXKSA YV Q MEET SXKSA SEKBT SXKSA MEET");
    for (String sentence : listOfSentences) {

      if (isContainingValidWord(sentence)) {
        System.out.println(sentence);
      }
    } */

    // Run hasher
    /* Hasher hasher = new Hasher(256);

    String str = "a";
    for (int i = 0; i < 10; i++) {
      System.out.println("Hash: " + hasher.hash(str));
      str += "b";
    } */

    // String cipher = caesarEncrypter.encrypt(26, "hej Martin, jag heter martin och har ett efternamn som är ganska långt!");
    /* String cipher = caesarEncrypter.encrypt(4, "hello you pig!!");
    String plain = caesarEncrypter.decrypt(69, cipher);

    System.out.println(cipher);
    System.out.println(plain); */



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

    scan.close();
  }

  private void doSubstitutionMenu() {
    Boolean isRunning = true;
    do {
      MenuEvent event = ui.getSubstitutionMenuChoice();

      if (event == MenuEvent.ENCRYPT_SUB) {
        String plainMessage = ui.promptForString("Enter message to encrypt: ");
        int key = ui.promptForInt("Enter a encryption key (1- 65): ");
        System.out.println(key);

        String encryptedMessage = substitutionEncrypter.encrypt(key, plainMessage);
        System.out.println(encryptedMessage);
      }
      if (event == MenuEvent.DECRYPT_SUB) {
        String cipherText = ui.promptForString("Enter message to decrypt: ");
        int key = ui.promptForInt("Enter a encryption key (1- 65): ");

        String decryptedMessage = substitutionEncrypter.decrypt(key, cipherText);
        System.out.println(decryptedMessage);
      }
      if (event == MenuEvent.QUIT) {
        isRunning = false;
      }
    } while (isRunning);
  }

  private void doTranspositionMenu() {

  }

  private void doHashMenu() {
    String plainMessage = ui.promptForString("Enter a message to hash: ");
    String hashedMessage = hasher.hash(plainMessage);
    System.out.println("Hash signature: " + hashedMessage);
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

  public static enum MenuEvent {
    SUBSTITUTION,
    TRANSPOSITION,
    ENCRYPT_TRAN,
    ENCRYPT_SUB,
    DECRYPT_TRAN,
    DECRYPT_SUB,
    HASH,
    QUIT
  }
}
