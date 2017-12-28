package util.file;

import org.apache.commons.io.FilenameUtils;

/**
 * @author litz-a
 */
public class FilenameUtilsExample1 {

  public static void main(String[] args) {

    filenameComponents_();
    System.out.println("");
    normalize_();
    System.out.println("");
    equals_();
    System.out.println("");
    concat_();
    System.out.println("");
    separators_();
  }

  private static void filenameComponents_() {

    String filename =
        "X:\\JCG\\articles\\org.apache.commons.io.FilenameUtils Example\\notes.txt";

    System.out.println("*** File name components ***");
    System.out.println("File name: " + filename);

    String prefix = FilenameUtils.getPrefix(filename);
    System.out.println("Prefix: " + prefix);

    String path = FilenameUtils.getPath(filename);
    System.out.println("Path: " + path);

    String fullPath = FilenameUtils.getFullPath(filename);
    System.out.println("Full path: " + fullPath);

    String name = FilenameUtils.getName(filename);
    System.out.println("Name: " + name);

    String baseName = FilenameUtils.getBaseName(filename);
    System.out.println("Base name: " + baseName);

    String extension = FilenameUtils.getExtension(filename);
    System.out.println("Extension: " + extension);
  }

  private static void normalize_() {

    System.out.println("*** Normalization ***");

    String filename =
        "X:\\JCG\\.\\org.apache.commons.io.FilenameUtils Example\\notes.txt";
    System.out.println("Before: " + filename);
    String normalized = FilenameUtils.normalize(filename);
    System.out.println("After single dot normalization: " + normalized);

    filename = "X:\\JCG\\articles\\..\\notes.txt";
    System.out.println("Before: " + filename);
    normalized = FilenameUtils.normalize(filename);
    System.out.println("After double dot normalization: " + normalized);
  }

  private static void equals_() {

    System.out.println("*** File name equality ***");

    String filename1 = "FilenameUtilsExample.java";
    String filename2 = "FilenameUtilsExample.java";
    System.out.println("Filename 1: " + filename1);
    System.out.println("Filename 2: " + filename2);
    boolean result = FilenameUtils.equals(filename1, filename2);
    System.out.println("Equals: " + result);

    filename1 = null;
    System.out.println("Filename 1: " + filename1);
    System.out.println("Filename 2: " + filename2);
    result = FilenameUtils.equals(filename1, filename2);
    System.out.println("Equals: " + result);
  }

  private static void concat_() {

    System.out.println("*** Concatenation ***");

    // base and added names are paths
    String filename1 = "X:\\JCG\\Examples\\org.apache.commons.io.FilenameUtils";
    String filename2 = "articles\\";
    String concatenatedPath = FilenameUtils.concat(filename1, filename2);
    System.out.println("Filename 1: " + filename1);
    System.out.println("Filename 2: " + filename2);
    System.out.println("Concatenated: " + concatenatedPath);

    // base is path and added name is file name
    filename1 = "X:\\JCG\\Examples\\org.apache.commons.io.FilenameUtils";
    filename2 = "FilenameUtilsExample.java";
    concatenatedPath = FilenameUtils.concat(filename1, filename2);
    System.out.println("Filename 1: " + filename1);
    System.out.println("Filename 2: " + filename2);
    System.out.println("Concatenated: " + concatenatedPath);

    // base is reative path and added name is file name
    filename1 = "org.apache.commons.io.FilenameUtils";
    filename2 = "FilenameUtilsExample.java";
    concatenatedPath = FilenameUtils.concat(filename1, filename2);
    System.out.println("Filename 1: " + filename1);
    System.out.println("Filename 2: " + filename2);
    System.out.println("Concatenated: " + concatenatedPath);
  }

  private static void separators_() {

    System.out.println("*** Separator conversion ***");

    String filename =
        "X:\\JCG\\articles\\org.apache.commons.io.FilenameUtils Example\\notes.txt";
    System.out.println("File name: " + filename);
    filename = FilenameUtils.separatorsToUnix(filename);
    System.out.println("File name after separatorsToUnix(): " + filename);

    filename = "/JCG/articles/org.apache.commons.io.FilenameUtils Example/notes.txt";
    System.out.println("File name: " + filename);
    filename = FilenameUtils.separatorsToSystem(filename);
    System.out.println("File name after separatorsToSystem(): " + filename);
  }
}