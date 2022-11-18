package dv700.cipherApp.encryption;

public class Hasher {
  private int bitSize;

  public Hasher(int bitSize) {
    this.bitSize = bitSize;
  }

  public String hash(String message){
    int hashContainer = message.length();
    char[] characters = message.toCharArray();

    for (int i = 0; i < characters.length; i++) {
      int value = (int) characters[i];
      hashContainer += value * ((i + 1) * value);
    }

    hashContainer = hashContainer % bitSize;
    // Format the string to 3 characters and padstart with zeroes. I.e 42 => 042
    String hashedString = String.format("%03d", hashContainer);

    return hashedString;
  }
}
