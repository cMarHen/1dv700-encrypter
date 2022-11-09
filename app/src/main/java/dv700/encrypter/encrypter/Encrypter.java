package dv700.encrypter.encrypter;

/**
 * Encrypter
 */
public interface Encrypter {

  public String encrypt(String plainMessage);
  public String decrypt(String encodedMessage);
}