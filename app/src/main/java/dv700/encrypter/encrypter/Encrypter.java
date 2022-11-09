package dv700.encrypter.encrypter;

/**
 * Encrypter
 */
public interface Encrypter<T> {

  public String encrypt(String plainMessage);
  public String decrypt(T key, String encodedMessage);
}