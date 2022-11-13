package dv700.encrypter.encryption;

/**
 * Encrypter
 */
public interface Encrypter<T> {

  public String encrypt(T key, String plainMessage);
  public String decrypt(T key, String encodedMessage);
}