package dv700.cipherApp.encryption;

import java.util.ArrayList;

public class TranspositionCipher implements Encrypter<Integer> {

  @Override
  public String encrypt(Integer key, String plainMessage) {
    ArrayList<Character> list = new ArrayList<>();
    for (Character c : plainMessage.toCharArray()) {
      list.add(c);
    }

    for (int i = 0; i <= key * plainMessage.length(); i++) {
      char container = list.get(i % plainMessage.length());
      list.remove(i % plainMessage.length());
      list.add(container);
    }
    
    String encrypted = "";
    for (Character c : list) {
      encrypted += c;
    }

    return encrypted;
  }

  @Override
  public String decrypt(Integer key, String encodedMessage) {
    ArrayList<Character> list = new ArrayList<>();
    for (Character c : encodedMessage.toCharArray()) {
      list.add(c);
    }

    for (int i = key * encodedMessage.length(); i >= 0; i--) {
      char container = list.get(encodedMessage.length() - 1);
      list.remove(encodedMessage.length() - 1);
      
      list.add(i % encodedMessage.length(), container);
    }

    String encryptedText = "";
    for (Character c : list) {
      encryptedText += c;
    }

    return encryptedText;
  }
  
}
