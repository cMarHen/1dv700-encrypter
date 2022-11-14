package dv700.cipherApp.app;

import java.util.ArrayList;
import java.util.Scanner;

import dv700.cipherApp.decryption.SubstitutionDecrypter;
import dv700.cipherApp.encryption.Encrypter;
import dv700.cipherApp.encryption.Hasher;
import dv700.cipherApp.encryption.SubstitutionCipher;
import dv700.cipherApp.encryption.TranspositionCipher;
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
    TranspositionCipher cipher = new TranspositionCipher();
    String str = cipher.encrypt(20, "Martin");
    System.out.println(str);
    System.out.println(cipher.decrypt(20, str));
    /* try {
      TextReader fileHandler = new TextReader("/src/main/java/dv700/cipherApp/filehandling/files/mh225wd.txt");
      String textToEncrypt = fileHandler.readFromFile();
      String encryptedText = substitutionEncrypter.encrypt(42, textToEncrypt);
      fileHandler.writeToFile(encryptedText);
    } catch (Exception e) {
      System.out.println(e);
    } */

    
    /* Boolean isRunning = true;
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
    } while (isRunning); */

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
