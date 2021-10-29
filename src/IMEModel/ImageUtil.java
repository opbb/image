package IMEModel;


import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents. Feel free to change this method
 * as required.
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
      System.out.println("File " + filename + " not found!");
      return new int[1][1][3];
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
//    ArrayList<ArrayList<ArrayList<Integer>>> results = new ArrayList<ArrayList<ArrayList<Integer>>>();
    int[][][] results = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
//        results = new ArrayList<ArrayList<ArrayList<Integer>>>(3);
//        results.get(i).get(j).add(r);
//        results.get(i).get(j).add(g);
//        results.get(i).get(j).add(b);
        results[i][j][0] = r;
        results[i][j][1] = g;
        results[i][j][2] = b;
        System.out.println(results[i][j][0] + ", " + results[i][j][1] + ", " + results[i][j][2]);
      }
    }
    return results;
  }

  public static void writePPM(ImageModelImpl model, String filename) throws IOException {

//    Scanner sc;
//
//    File file = new File(filename);
//    try {
//      sc = new Scanner(new FileInputStream(file));
//    } catch (FileNotFoundException e) {
//      System.out.println("File " + filename + " not found!");
//      return;
//    }
//    int[][][] newList = new int[image.getWidth()][image.getHeight()][3];
//    StringBuilder builder = new StringBuilder();
//    //read the file line by line, and populate a string. This will throw away any comment lines
//    while (sc.hasNextLine()) {
//      String s = sc.nextLine();
//      if (s.charAt(0) != '#') {
//        builder.append(s + System.lineSeparator());
//      }
//
//    }
    try {

      OutputStream out = new PrintStream(filename);
      out.write(model.toString().getBytes());
      out.close();
    } catch (IOException e) {
      throw new IllegalStateException("failed at cat2");
    }





//    //now set up the scanner to read from the string we just built
//    sc = new Scanner(builder.toString());
//
//    String token;
//
//    token = sc.next();
//    if (!token.equals("P3")) {
//      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
//    }
//    int width = sc.nextInt();
//    System.out.println("Width of image: " + width);
//    int height = sc.nextInt();
//    System.out.println("Height of image: " + height);
//    int maxValue = sc.nextInt();
//    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);
////    ArrayList<ArrayList<ArrayList<Integer>>> results = new ArrayList<ArrayList<ArrayList<Integer>>>();
//    int[][][] results = new int[height][width][3];
//    for (int i = 0; i < height; i++) {
//      for (int j = 0; j < width; j++) {
//
//        int r = sc.nextInt();
//        int g = sc.nextInt();
//        int b = sc.nextInt();
////        results = new ArrayList<ArrayList<ArrayList<Integer>>>(3);
////        results.get(i).get(j).add(r);
////        results.get(i).get(j).add(g);
////        results.get(i).get(j).add(b);
//        results[i][j][0] = r;
//        results[i][j][1] = g;
//        results[i][j][2] = b;
//
//      }
//    }
    return;
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

  /**
   * Saves the given image back to its former directory with any updated changes.
   **/
  public static void saveImage(Image image, String filename) throws IllegalStateException {

    File file = new File(filename);
    int color = 0;
    BufferedImage bf = new BufferedImage(image.getWidth(), image.getWidth(), BufferedImage.TYPE_3BYTE_BGR);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        color = bf.getRGB(i, j);
        image.getPixels()[i][j][0] = (color & 0xff0000) >> 16;
        image.getPixels()[i][j][1] = (color & 0xff00) >> 8;
        image.getPixels()[i][j][2] = color & 0xff;
        color = (image.getPixels()[i][j][0] << 16) + (image.getPixels()[i][j][1] << 8) + image.getPixels()[i][j][2];
        bf.setRGB(i, j, color);
      }
    }

    try {
      ImageIO.write(bf, "ppm", new FileOutputStream(filename));
      System.out.println("worked");
    } catch (IOException e) {
      throw new IllegalStateException("sorry account closed");
    }

  }


//    Scanner sc;
//    try {
//      sc = new Scanner(new FileInputStream(filename));
//    } catch (FileNotFoundException e) {
//      System.out.println("File " + filename + " not found!");
//      return;
//    }

//      }
//    }
//    try {
//      FileWriter writer = new FileWriter(filename);
//      writer.write(builder.toString());
//      writer.close();
//    } catch (IOException e) {
//      throw new IllegalStateException("physical products");
//    }
//  }
//    //now set up the scanner to read from the string we just built
//    sc = new Scanner(builder.toString());
//
//    String token;
//
//    token = sc.next();
//    if (!token.equals("P3")) {
//      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
//    }
//    int width = sc.nextInt();
//
//    int height = sc.nextInt();
//
//    int maxValue = sc.nextInt();
//
//
//    int[][][] results = new int[height][width][3];
//    for (int i = 0; i < height; i++) {
//      for (int j = 0; j < width; j++) {
//
//        int r = sc.nextInt();
//        int g = sc.nextInt();
//        int b = sc.nextInt();
////        results = new ArrayList<ArrayList<ArrayList<Integer>>>(3);
////        results.get(i).get(j).add(r);
////        results.get(i).get(j).add(g);
////        results.get(i).get(j).add(b);
//        results[i][j][0] = r;
//        results[i][j][1] = g;
//        results[i][j][2] = b;
//        System.out.println(results[i][j][0] + ", " + results[i][j][1] + ", " + results[i][j][2]);
//      }
//    }
//    return results;
//  }

//    try {
//      File file = new File(filename);
//      ImageModel img = new ImageModelImpl(filename);
//      BufferedImage bw = ImageIO.read(file);
//
//      String header = String.format("P6\n%d %d\n255\n", getWidth(filename), getHeight(filename));
//      bw.write(header.getBytes(StandardCharsets.US_ASCII));
//      for (int i = 0; i < getHeight(filename); i++) {
//        for (int j = 0; j < getWidth(filename); j++) {
//
//          bw.write(img.getImageValues()[i][j][0]);
//          bw.write(img.getImageValues()[i][j][1]);
//          bw.write(img.getImageValues()[i][j][2]);
//        }
//      }
//    } catch (FileNotFoundException e) {
//      throw new IllegalStateException("ur mom sucks.");
//    } catch (IOException e) {
//      throw new IllegalStateException("ur mom sucks 2");
//    }
//  }


//    FileOutputStream file;
//
//    try {
//      file = new FileOutputStream(filename);
//
//    } catch (FileNotFoundException e) {
//      return;
//    }
//
//    try {
//      file.close();
//    } catch (IOException e) {
//      throw new IOException("Was not able to save image correctly.");
//    }


  //demo main
  public static void main(String[] args) {
    String filename;

//    if (args.length > 0) {
//      filename = args[0];
//    } else {
    filename = "/Users/thomasgrbic/Downloads/code (10)/koala-vertical.ppm";
    //   }


    if (args[0].equals("flipvertical")) {
      Image img = new ImageImpl(filename);
      ImageModelImpl model = new ImageModelImpl(img);
      model.brighten(300);

      try {
        ImageUtil.writePPM(model, filename);
      } catch (IOException e) {
        System.out.println("failed at dog");
      }
      System.out.println("successful!");


//    try {
//      ImageUtil.readPPM(filename);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    }
  }

}

