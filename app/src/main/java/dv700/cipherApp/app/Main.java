package dv700.cipherApp.app;

import java.util.Hashtable;
import java.util.Scanner;
import dv700.cipherApp.encryption.Encrypter;
import dv700.cipherApp.encryption.Hasher;
import dv700.cipherApp.encryption.SubstitutionCipher;
import dv700.cipherApp.encryption.TranspositionCipher;
import dv700.cipherApp.filehandling.TextReader;
import dv700.cipherApp.ui.Console;

public class Main {
  private Hasher hasher;
  private Console ui;
  private Scanner scan;
  private int bitSize = 256;
  
  public Main() {
    this.hasher = new Hasher(bitSize);
    this.scan = new Scanner(System.in, "UTF8");
    this.ui = new Console(scan);
  }


  public void run() {

    Boolean isRunning = true;
    do {
      MenuEvent event = ui.getMainMenuChoice();

      if (event == MenuEvent.ENCRYPT) {
        doEncryptMenu();
      }
      if (event == MenuEvent.DECRYPT) {
        doDecryptMenu();
      }
      if (event == MenuEvent.HASH) {
        doHashMenu();
      }
      if (event == MenuEvent.TESTHASH_OVERVIEW) {
        String textToFile = "";
        for (int i = 0; i < bitSize; i++) {
          textToFile += i + ": " + hasher.hash(String.valueOf(i)) + "\n";
        };
        TextReader fileHandler = new TextReader("/src/filecontainer/hashOverview.txt");
        fileHandler.writeToFile(textToFile);
        System.out.println("Testing successful. You can find the result in app\\src\\filecontainer\\hashOverview.txt");
      }
      if (event == MenuEvent.TESTHASH) {
        int iterations = ui.promptForInt("How many times do you want to iterate the hash function? :");
        System.out.println("Testing hash function...");
        Boolean res = testHashFrequency(iterations);

        if (res) {
          System.out.println("Testing successful. You can find the result in app\\src\\filecontainer\\hashFrequency.txt");
        } else {
          System.out.println("Testing failed.");
        }
      }
      if (event == MenuEvent.QUIT) {
        isRunning = false;
      }
    } while (isRunning);

    scan.close();
  }

  private void doEncryptMenu() {
    Boolean isRunning = true;
    do {
      MenuEvent event = ui.getEncryptionMenuChoice();
      Encrypter<Integer> encrypter = new SubstitutionCipher();
      Integer key = -1;
      if (event == MenuEvent.SUBSTITUTION) {
        key = ui.promptForInt("Enter a encryption key (1- 65): ");
      }
      if (event == MenuEvent.TRANSPOSITION) {
        encrypter = new TranspositionCipher();
        key = ui.promptForInt("Enter a encryption key (number): ");
      }
      if (event == MenuEvent.QUIT) {
        isRunning = false;
        return;
      }

      String pathway = ui.promptForString("Enter a relative pathway from /src (example: /src/filecontainer/example.txt ): ");

      if (encrypter != null && key != -1 && pathway != null) {
        TextReader fileHandler = new TextReader(pathway);
        try {
          String textToEncrypt = fileHandler.readFromFile();
          String encryptedText = encrypter.encrypt(key, textToEncrypt);
          fileHandler.writeToFile(encryptedText);
          System.out.println("The file was successfully encrypted!");
        } catch (Exception e) {
          System.err.println(e);
        }
      } else {
        System.out.println("Something went wrong! Try again please.");
      }
    } while (isRunning);
  }

  private void doDecryptMenu() {
    Boolean isRunning = true;
    do {
      MenuEvent event = ui.getEncryptionMenuChoice();
      Encrypter<Integer> encrypter = new SubstitutionCipher();
      Integer key = -1;
      if (event == MenuEvent.SUBSTITUTION) {
        key = ui.promptForInt("Enter a encryption key (1- 65): ");
      }
      if (event == MenuEvent.TRANSPOSITION) {
        encrypter = new TranspositionCipher();
        key = ui.promptForInt("Enter a encryption key (number): ");
      }
      if (event == MenuEvent.QUIT) {
        isRunning = false;
        return;
      }

      String pathway = ui.promptForString("Enter a relative pathway from /src (example: /src/filecontainer/example.txt ): ");

      if (encrypter != null && key != -1 && pathway != null) {
        TextReader fileHandler = new TextReader(pathway);
        try {
          String textToEncrypt = fileHandler.readFromFile();
          String encryptedText = encrypter.decrypt(key, textToEncrypt);
          fileHandler.writeToFile(encryptedText);
          System.out.println("The file was successfully decrypted! ");
        } catch (Exception e) {
          System.err.println(e);
        }
      } else {
        System.out.println("Something went wrong! Try again please.");
      }


    } while (isRunning);
  }

  private void doHashMenu() {
    String plainMessage = ui.promptForString("Enter a message to hash: ");
    String hashedMessage = hasher.hash(plainMessage);
    System.out.println("Hash signature: " + hashedMessage);
  }

  public static enum MenuEvent {
    SUBSTITUTION,
    TRANSPOSITION,
    ENCRYPT,
    DECRYPT,
    HASH,
    TESTHASH,
    TESTHASH_OVERVIEW,
    QUIT
  }

  private Boolean testHashFrequency(int iterations) {
    try {
      Hashtable<String, Integer> table = new Hashtable<String, Integer>();
      for (int i = 0; i < 256; i++) {
        table.put(String.format("%03d", i), 0);
      }
      for (int i = 0; i < iterations; i++) {
        String hashed = hasher.hash(String.valueOf(i));
        int freqValue = table.getOrDefault(hashed, -1);
      
        if (freqValue != -1) {
          table.replace(hashed, freqValue + 1);
        }
      }
      
      String result = "";
      for (int i = 0; i < 256; i++) {
        String keyToUse = String.format("%03d", i);
        int val = table.get(keyToUse);

        result += keyToUse + ": " + val + "\n";
      }
      
      TextReader fileHandler = new TextReader("/src/filecontainer/hashFrequency.txt");
      fileHandler.writeToFile(result);
      
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
