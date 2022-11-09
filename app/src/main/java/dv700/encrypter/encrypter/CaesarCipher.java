package dv700.encrypter.encrypter;

public class CaesarCipher implements Encrypter {
  private char lowerBound;
  private char upperBound;
  private int key;

  public CaesarCipher(int key, char upperBound, char lowerBound) {
    this.key = key;
    this.lowerBound = lowerBound < '!' ? '!' : lowerBound;
    this.upperBound = upperBound > '}' ? '}' : upperBound;
  }

  @Override
  public String encrypt(String plainMessage) {
    char[] arr = plainMessage.toCharArray();
    String encryptedWord = "";

    for (int i = 0; i < arr.length; i++) {
      char char1= arr[i];  
      if (char1 == ' ') {
        encryptedWord += " ";
      } else {
        if (char1 + this.key > upperBound) {
          int increments = this.key;
          while (increments > 0) {
            if (char1 == upperBound) {
              char1 = lowerBound;
            } else {
              char1 ++;
            }
            increments--;
          }
          
        } else {
          // BÖRJAR ÄNDRA
          char1 = (char) (char1 + this.key);  
        }
      }

      encryptedWord += char1;
    }

    return encryptedWord;
  }

  @Override
  public String decrypt(String encodedMessage) {
    char[] arr = encodedMessage.toCharArray();
    String decryptedWord = "";

    for (int i = 0; i < arr.length; i++) {
      char char1= arr[i];  

      if (char1 == ' ') {
        decryptedWord += " ";
      } else {
      if (char1 - this.key < lowerBound) {
        int increments = this.key;
        while (increments > 0) {
          if (char1 == lowerBound) {
            char1 = upperBound;
          } else {
            char1 --;
          }
          increments--;
        }
        
      } else {
        char1 = (char) (char1 - this.key);  
      }
    }

      decryptedWord += char1;
    }

    return decryptedWord;
  }
  
}
