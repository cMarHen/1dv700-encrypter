package dv700.encrypter.encrypter;

public class CaesarCipher implements Encrypter<Integer> {
  public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

  @Override
  public String encrypt(Integer key, String plainMessage) {
    plainMessage = plainMessage.toLowerCase();
    String encryptedMessage = "";

    for (int i = 0; i < plainMessage.length(); i++) {
      int pos = ALPHABET.indexOf(plainMessage.charAt(i));
      int keyValue = (key + pos) % 26;
      char replaceValue = ALPHABET.charAt(keyValue);
      encryptedMessage += replaceValue;
    }

    return encryptedMessage;
  }

  @Override
  public String decrypt(Integer keyToUse, String encodedMessage) {
    encodedMessage = encodedMessage.toLowerCase();
    String decryptedWord = "";

    for (int i = 0; i < encodedMessage.length(); i++) {
      if (encodedMessage.charAt(i) == ' ') {
        decryptedWord += " ";
      } else {
        int pos = ALPHABET.indexOf(encodedMessage.charAt(i)); // Index of the alphabet letter
        int keyValue = (pos - keyToUse) % ALPHABET.length(); // Modulus of difference of alphabet letter and key

        if (keyValue < 0) {
          keyValue = ALPHABET.length() + keyValue;
        }

        char replaceValue = ALPHABET.charAt(keyValue);
        decryptedWord += replaceValue;
      }
    }

    return decryptedWord;
  }
  
}
