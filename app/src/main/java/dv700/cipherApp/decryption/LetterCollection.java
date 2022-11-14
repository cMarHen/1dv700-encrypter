package dv700.cipherApp.decryption;

import java.util.ArrayList;
import java.util.LinkedList;

public class LetterCollection {
  String charactersAsString;
  private ArrayList<Character> characters;

  public LetterCollection(String charactersToUse) {
    this.charactersAsString = charactersToUse;
    this.characters = new ArrayList<>();
    restoreShift();
  }

  public ArrayList<Character> getCharacters() {
    return characters;
  }

  public ArrayList<Character> shiftKeyForward() {
    char container = characters.get(characters.size() - 1);
    characters.remove(characters.size() - 1);
    characters.add(0, container);

    return characters;
  }

  public ArrayList<Character> shiftKeyBackwards() {
    char container = characters.get(0);
    characters.remove(0);
    characters.add(container);

    return characters;
  }

  public void restoreShift() {
    char[] characterList = charactersAsString.toCharArray();
    for (Character c : characterList) {
      characters.add(c);
    }
  }
}
