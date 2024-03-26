package hei.school.soratra.service;

import java.io.*;

import org.springframework.stereotype.Component;

@Component
public class FileUtil {
  public static File convertByteArrayToFile(String prefix, byte[] file, String suffix)
      throws IOException {
    File tempFile = File.createTempFile(prefix, suffix);
    FileOutputStream fileOutputStream = new FileOutputStream(tempFile.getName());
    try (fileOutputStream) {
      fileOutputStream.write(file);
    }
    return tempFile;
  }
  public static void transformFileToUpperCase(File file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuilder content = new StringBuilder();
    String line;

    // Read the file line by line and append to content
    while ((line = reader.readLine()) != null) {
      content.append(line).append("\n");
    }
    reader.close();

    // Convert the content to uppercase
    String upperCaseContent = content.toString().toUpperCase();

    // Write the uppercase content back to the file
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    writer.write(upperCaseContent);
    writer.close();
  }
}
