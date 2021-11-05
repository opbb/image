package imemodel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Formats extends ImageUtil {


  public static int[][][] readPNG(String filename) throws IOException {

    try {
      InputStream input = new FileInputStream(filename);

      BufferedImage buff = ImageIO.read(input);
      int[][][] pixels = new int[buff.getHeight()][buff.getWidth()][3];

      for (int i = 0; i < buff.getWidth(); i++) {
        for (int j = 0; j < buff.getHeight(); j++) {
          Color colour = new Color(buff.getRGB(i, j));
          pixels[i][j][0] = colour.getRed();
          pixels[i][j][1] = colour.getGreen();
          pixels[i][j][2] = colour.getBlue();

        }
      }
      return pixels;
    } catch (FileNotFoundException e) {

      return new int[][][]{{{0}}};
    } catch (IOException e) {

      return new int[][][]{{{1}}};
    }

  }

  public static int getPNGHeight(String filename) {
    try {
      InputStream input = new FileInputStream(filename);

      BufferedImage buff = ImageIO.read(input);

      return buff.getHeight();

    } catch (IOException e) {
      return -1;
    }

  }

  public static int getPNGWidth(String filename) {
    try {
      InputStream input = new FileInputStream(filename);

      BufferedImage buff = ImageIO.read(input);

      return buff.getWidth();

    } catch (IOException e) {
      return -1;
    }

  }

  public static void writePNG(Image image, String name) throws IOException {


    try {
      int height = getFormatHeight(image);
      int width = getFormatWidth(image);
      BufferedImage img1 = new BufferedImage(width, height,
              BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          img1.setRGB(j, i, (image.getPixels()[i][j][0] << 16) |
                  (image.getPixels()[i][j][1] << 8) | image.getPixels()[i][j][2]);
        }
      }
      File outputfile = new File(name);
      ImageIO.write(img1, "JPEG", outputfile);
    } catch (IOException e) {
      System.out.println("Error writing file");
    }

  }

  public static void writeJPEG(Image image, String name) throws IOException {


    try {
      int height = getFormatHeight(image);
      int width = getFormatWidth(image);
      BufferedImage img1 = new BufferedImage(width, height,
              BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          img1.setRGB(j, i, (image.getPixels()[i][j][0] << 16) |
                  (image.getPixels()[i][j][1] << 8) | image.getPixels()[i][j][2]);
        }
      }
      File outputfile = new File(name);
      ImageIO.write(img1, "jpeg", outputfile);
    } catch (IOException e) {
      System.out.println("Error writing file");
    }

  }

  public static void writeBMP(Image image, String name) throws IOException {


    try {
      int height = getFormatHeight(image);
      int width = getFormatWidth(image);
      BufferedImage img1 = new BufferedImage(width, height,
              BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          img1.setRGB(j, i, (image.getPixels()[i][j][0] << 16) |
                  (image.getPixels()[i][j][1] << 8) | image.getPixels()[i][j][2]);
        }
      }
      File outputfile = new File(name);
      ImageIO.write(img1, "bmp", outputfile);
    } catch (IOException e) {
      System.out.println("Error writing file");
    }

  }


  public static int getFormatWidth(Image image) {
    return image.getWidth();
  }

  public static int getFormatHeight(Image image) {
    return image.getHeight();
  }
}
