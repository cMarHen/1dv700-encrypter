package dv700.cipherApp.encryption;

/**
 * Encrypter
 */
public interface Encrypter<T> {

  public String encrypt(T key, String plainMessage);
  public String decrypt(T key, String encodedMessage);
}