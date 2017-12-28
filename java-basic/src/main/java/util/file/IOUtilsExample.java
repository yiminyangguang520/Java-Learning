package util.file;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

/**
 * @author litz-a
 */
public class IOUtilsExample {

  public static void main(String[] args) {
    //Declaring all Objects

    BufferedReader in = null;
    PrintWriter out = null;
    InputStream is = null;
    FileInputStream fin = null, fin2 = null;
    StringWriter swr = null;
    LineIterator lt = null;
    FileWriter fw = null;
    FileReader fr = null, fr2 = null;

    try {
      in = IOUtils.toBufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out, true);

      //Creating InputStream from String.
      out.println("Enter Something. Enter stop to stop");
      String s = "";
      String s1;
      do {
        s1 = in.readLine();
        if ((s1.compareToIgnoreCase("stop")) != 0) {
          s += s1 + "\n";
        }
      } while ((s1.compareToIgnoreCase("stop")) != 0);
      is = IOUtils.toInputStream(s, "utf-8");

      //Writing to file by Copying the Contents of
      //InputStream to FileOutputStream
      OutputStream o = new FileOutputStream("D:\\Java_Workspace\\IOUtilsExample\\abc.txt");
      int bytes = IOUtils.copy(is, o);
      out.println("File Written with " + bytes + " bytes");
      IOUtils.closeQuietly(o);

      //Printing the File Containings
      //by using the LineIterator
      out.println("\n Printing The Containings:-");
      fin = new FileInputStream("D:\\Java_Workspace\\IOUtilsExample\\abc.txt");
      lt = IOUtils.lineIterator(fin, "utf-8");
      while (lt.hasNext()) {
        String line = lt.nextLine();
        out.println(line);
      }
      lt.close();

      //Another way to print the file containings (Using StringWriter)
      out.println("\n Printing The Containings 2nd Way (Using StringWriter):-");
      fin2 = new FileInputStream("D:\\Java_Workspace\\IOUtilsExample\\abc.txt");
      List<String> ls = IOUtils.readLines(fin2, "utf-8");
      swr = new StringWriter();
      IOUtils.writeLines(ls, IOUtils.LINE_SEPARATOR_WINDOWS, swr);

      String fcontain = swr.toString();
      out.println(fcontain);
      //3rd way to print the file containings (using IOUtils.toString() )
      out.println("\n Printing The Containings 3rd Way (Using IOUtils.toString()):-");
      FileReader fr3 = new FileReader("D:\\Java_Workspace\\IOUtilsExample\\abc.txt");
      String st = IOUtils.toString(fr3);
      IOUtils.closeQuietly(fr3);
      out.println(st);
      //Copying the File
      fw = new FileWriter("D:\\Java_Workspace\\IOUtilsExample\\abc2.txt");
      IOUtils.write(fcontain, fw);

      IOUtils.closeQuietly(fw);

      //Checking the Equality of newly created File with the Older One
      fr2 = new FileReader("D:\\Java_Workspace\\IOUtilsExample\\abc2.txt");
      fr = new FileReader("D:\\Java_Workspace\\IOUtilsExample\\abc.txt");

      if (IOUtils.contentEqualsIgnoreEOL(fr, fr2)) {
        out.println("File Copied Successfully");
      } else {
        out.println("There Must be an Error in Copying the Files");
      }

      //We may try to close all Objects here
      //or Skip to the finally block.
    } catch (Exception e) {
      e.printStackTrace(out);
    } finally {

      //The closeQuetly() method doesn't throw any exceptions.

      IOUtils.closeQuietly(in);
      IOUtils.closeQuietly(is);
      IOUtils.closeQuietly(fin);
      IOUtils.closeQuietly(out);
      IOUtils.closeQuietly(fr);
      IOUtils.closeQuietly(swr);
      IOUtils.closeQuietly(fin2);
      IOUtils.closeQuietly(fr2);
    }
  }
}
