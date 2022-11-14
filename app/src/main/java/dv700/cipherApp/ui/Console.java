package dv700.cipherApp.ui;

import java.util.Scanner;

import dv700.cipherApp.app.Main.MenuEvent;

public class Console {
  private Scanner scan;

  public Console(Scanner scan) {
    this.scan = scan;
  }

  public MenuEvent getMainMenuChoice() {
    System.out.println("------------------------");
    System.out.println("-- SuperSecretService --");
    System.out.println("------------------------");
    System.out.println("1. Move to substitution section");
    System.out.println("2. Move to transposition section");
    System.out.println("3. Hash message");
    System.out.println("0. Quit ");
    System.out.println("------------------------");

    int choice = -1;

    do {
      choice = promptForInt("Select from menu: ");
    } while (choice >= MenuEvent.values().length || choice < 0);


    if (choice == 1) {
      return MenuEvent.SUBSTITUTION;
    } else if (choice == 2) {
      return MenuEvent.TRANSPOSITION;
    } else if (choice == 3) {
      return MenuEvent.HASH;
    } else {
      return MenuEvent.QUIT;
    }
  }

  public MenuEvent getSubstitutionMenuChoice() {
    int choice = -1;
    System.out.println("-----------------------------");
    System.out.println("-- Substitution encryption --");
    System.out.println("-----------------------------");
    System.out.println("1. Encrypt");
    System.out.println("2. Decrypt");
    System.out.println("0. Go back");

    do {
      choice = promptForInt("Enter a choice: ");
    } while (choice > 2 || choice < 0);

    if (choice == 1) {
      return MenuEvent.ENCRYPT_SUB;
    } else if (choice == 2) {
      return MenuEvent.DECRYPT_SUB;
    } else {
      return MenuEvent.QUIT;
    }
  }

  public int promptForInt(String question) {
    int input = -1;
    boolean validInput;

    do {
      try {
        System.out.print(question);
        input = scan.nextInt();
        scan.nextLine();
        validInput = true;
      } catch (Exception exeption) {
        scan.nextLine();
        validInput = false;
      }
    } while (!validInput);

    return input;
  }

  public String promptForString(String message) {
    String input = "";
    boolean validInput;

    do {
      try {
        System.out.print(message);
        input = scan.nextLine();
        validInput = true;
      } catch (Exception exeption) {
        validInput = false;
      }
    } while (!validInput);

    return input;
  }

}