package dv700.cipherApp.encryption;

import java.util.Hashtable;

public class Hasher {
  private int bitSize;

  public Hasher(int bitSize) {
    this.bitSize = bitSize;
  }

  public String hash(String message){
    int hashContainer = 0;
    String[] strArr = message.split("");
    for (String s : strArr) {
      hashContainer += getNumberFromHashModule(s);
    }

    for (int i = 1; i < message.length(); i++) {
      hashContainer += (byte) (i * message.charAt(i));
    }

    String hashedString = String.format("%03d", (hashContainer & 0xFF) % bitSize);

    return hashedString;
  }

  public void verify(String message) throws Exception {
    // TODO:
  }

  private int getNumberFromHashModule (String s) {
    Hashtable<String, Integer> table = new Hashtable<String, Integer>();
    table.put("a", 43);
    table.put("b", 230);
    table.put("f", 2);
    table.put("r", 64);
    table.put("c", 125);
    table.put("v", 92);
    table.put("t", 23);
    table.put("n", 120);
    table.put("p", 5);
    table.put("s", 84);
    table.put("g", 33);

    int res = 1;
    if (table.containsKey(s)) {
      int value = table.get(s);
      res = value;
    }

    return res;
  }
}
