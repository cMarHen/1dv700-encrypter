package dv700.cipherApp.encryption;

public class SubstitutionCipher implements Encrypter<Integer> {
  private String LETTERS = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!.?@"; // Length: 65

  public SubstitutionCipher() {
  }

  public SubstitutionCipher(String lettersToUse) {
    this.LETTERS = lettersToUse;
  }
  /**
   * 
   */
  @Override
  public String encrypt(Integer key, String plainMessage) {
    String encryptedMessage = "";
    for (int i = 0; i < plainMessage.length(); i++) {
      // Keep white spaces non-encrypted
      if (LETTERS.indexOf(plainMessage.charAt(i)) == -1) {
        encryptedMessage += plainMessage.charAt(i);
      } else {
      int pos = LETTERS.indexOf(plainMessage.charAt(i));
      int keyValue = (key + pos) % LETTERS.length();
      char replaceValue = LETTERS.charAt(keyValue);
      encryptedMessage += replaceValue;
      }
    }

    return encryptedMessage;
  }

  @Override
  public String decrypt(Integer key, String encodedMessage) {
    String decryptedMessage = "";

    for (int i = 0; i < encodedMessage.length(); i++) {
      if (LETTERS.indexOf(encodedMessage.charAt(i)) == -1) {
        decryptedMessage += encodedMessage.charAt(i);
      } else {
        int pos = LETTERS.indexOf(encodedMessage.charAt(i));
        int keyValue = (pos - key) % LETTERS.length();

        if (keyValue < 0) {
          keyValue = LETTERS.length() + keyValue;
        }

        char replaceValue = LETTERS.charAt(keyValue);
        decryptedMessage += replaceValue;
      }
    }

    return decryptedMessage;
  }  
}
