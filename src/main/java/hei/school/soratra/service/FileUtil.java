package hei.school.soratra.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
}
