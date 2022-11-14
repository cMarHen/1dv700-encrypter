package dv700.cipherApp.filehandling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

public class TextReader {
  private String projectPath;
  private String relativePathToProject;

  /**
   * @param relativePath - Relative path to file. Ex: /src/main/java/files/example.txt
   */
  public TextReader(String relativePath) {
    this.projectPath = relativePath;
    this.relativePathToProject = new File("").getAbsolutePath();
  }

  public void writeToFile(String text) {
    try {
      if (text == null) {
        throw new Exception("Text to file can't be null");
      }

      BufferedWriter bw = new BufferedWriter(
        new FileWriter(relativePathToProject + projectPath, StandardCharsets.UTF_8));

      bw.write(text);
      bw.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public String readFromFile() {
    try {
      BufferedReader br = new BufferedReader(
        new FileReader(relativePathToProject + projectPath, StandardCharsets.UTF_8)); // NOTE: You may need to change to other encodings.

      String textFromFile = "";
      String line = null;
      while ((line = br.readLine()) != null) {
        textFromFile += line + "\n";
      }

      br.close();
      return textFromFile;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
}
