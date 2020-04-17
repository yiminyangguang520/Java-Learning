package util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import org.apache.commons.io.FileUtils;

/**
 * @author min
 * As you can see FileChannels is the best way to copy large files.
 * If you work with even larger files you will notice a much bigger speed difference
 */
public class FileCopyUtils {
  public static void copyFileUsingFileStreams(File source, File dest) throws IOException {
    InputStream input = null;
    OutputStream output = null;
    try {
      input = new FileInputStream(source);
      output = new FileOutputStream(dest);
      byte[] buf = new byte[1024];
      int bytesRead;
      while ((bytesRead = input.read(buf)) > 0) {
        output.write(buf, 0, bytesRead);
      }
    } finally {
      input.close();
      output.close();
    }
  }

  public static void copyFileUsingFileChannels(File source, File dest) throws IOException {
    FileChannel inputChannel = null;
    FileChannel outputChannel = null;
    try {
      inputChannel = new FileInputStream(source).getChannel();
      outputChannel = new FileOutputStream(dest).getChannel();
      outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
    } finally {
      inputChannel.close();
      outputChannel.close();
    }
  }

  public static void copyFileUsingApacheCommonsIO(File source, File dest) throws IOException {
    FileUtils.copyFile(source, dest);
  }

  public static void copyFileUsingJava7Files(File source, File dest) throws IOException {
    Files.copy(source.toPath(), dest.toPath());
  }

  public static void main(String[] args) throws IOException {

    File source = new File("C:\\Users\\nikos7\\Desktop\\files\\sourcefile1.txt");
    File dest = new File("C:\\Users\\nikos7\\Desktop\\files\\destfile1.txt");

    // copy file using FileStreams
    long start = System.nanoTime();
    long end;
    copyFileUsingFileStreams(source, dest);
    System.out.println("Time taken by FileStreams Copy = " + (System.nanoTime() - start));

    // copy files using java.nio.FileChannel
    source = new File("C:\\Users\\nikos7\\Desktop\\files\\sourcefile2.txt");
    dest = new File("C:\\Users\\nikos7\\Desktop\\files\\destfile2.txt");
    start = System.nanoTime();
    copyFileUsingFileChannels(source, dest);
    end = System.nanoTime();
    System.out.println("Time taken by FileChannels Copy = " + (end - start));

    // copy file using Java 7 Files class
    source = new File("C:\\Users\\nikos7\\Desktop\\files\\sourcefile3.txt");
    dest = new File("C:\\Users\\nikos7\\Desktop\\files\\destfile3.txt");
    start = System.nanoTime();
    copyFileUsingJava7Files(source, dest);
    end = System.nanoTime();
    System.out.println("Time taken by Java7 Files Copy = " + (end - start));

    // copy files using apache commons io
    source = new File("C:\\Users\\nikos7\\Desktop\\files\\sourcefile4.txt");
    dest = new File("C:\\Users\\nikos7\\Desktop\\files\\destfile4.txt");
    start = System.nanoTime();
    copyFileUsingApacheCommonsIO(source, dest);
    end = System.nanoTime();
    System.out.println("Time taken by Apache Commons IO Copy = " + (end - start));

  }
}
