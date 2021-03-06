package imemodel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static int[][][] readPPM(String filename) throws IOException {

    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw e;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    int[][][] results = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        results[i][j][0] = r;
        results[i][j][1] = g;
        results[i][j][2] = b;
      }
    }
    return results;
  }


  /**
   * This method saves/writes a ppm file of the given image to the given directory.
   *
   * @param image    the Image by which to save/write.
   * @param filename the file name/path by which to save/write the ppm to.
   * @throws IOException when checking if the filename is valid.
   */
  public static void writePPM(Image image, String filename) throws IOException {

    FileOutputStream out = new FileOutputStream(filename);
    PrintStream p = new PrintStream(out);

    p.println("P3");
    p.println(image.getWidth() + " " + image.getHeight());
    p.println(255);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int[] vals = image.getPixels()[i][j];
        p.println((vals[0]) + " " + (vals[1]) + " " + (vals[2]) + " ");
      }
    }
  }


  /**
   * Returns the width of the given file.
   *
   * @param filename the given path of the file as a string
   * @return an int representing the width of pixels of the image as an integer.
   * @throws IOException if the file could not be found.
   */
  public static int getWidth(String filename) throws IOException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return -1;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    return width;
  }


  /**
   * Returns the height of the given file.
   *
   * @param filename the given path of the file as a string
   * @return an int representing the height of pixels of the image as an integer.
   * @throws IOException if the file could not be found.
   */
  public static int getHeight(String filename) throws IOException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return -1;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    return height;
  }


}

