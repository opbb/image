package imemodel;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


/**
 * This class represents a utility class that extends the ImageUtil class, offering new public
 * static methods that help to read, write, and get size info of other formats/extensions of an
 * image file.
 */
public class Formats extends ImageUtil {


  /**
   * Reads an Image File of any MIME type that is supported through BufferedImage. Reads the file
   * if supported, and retrieves a 3d integer array of the pixels and their values of an image.
   * Using the Color class, the method is able to retrieve the red, green, and blue values of each
   * pixel.
   *
   * @param filename The name of the file to be read.
   * @return a 3d integer array representing the pixels of an image.
   * @throws IOException if the ImageIO class has an issue reading the file.
   */
  public static int[][][] readImageFIle(String filename) throws IOException {

    try {
      InputStream input = new FileInputStream(filename);

      BufferedImage buff = ImageIO.read(input);
      int[][][] pixels = new int[buff.getHeight()][buff.getWidth()][3];

      for (int i = 0; i < buff.getHeight(); i++) {
        for (int j = 0; j < buff.getWidth(); j++) {
          Color colour = new Color(buff.getRGB(j, i));
          pixels[i][j][0] = colour.getRed();
          pixels[i][j][1] = colour.getGreen();
          pixels[i][j][2] = colour.getBlue();

        }
      }
      return pixels;
    } catch (IOException e) {
      throw e;
    }

  }

  /**
   * Retrieves the height of the given image through the use of the ImageIO class.
   *
   * @param filename the filename of the image.
   * @return an integer representing the height of the given image in pixels.
   */
  public static int getImageFileHeight(String filename) {
    try {
      InputStream input = new FileInputStream(filename);

      BufferedImage buff = ImageIO.read(input);

      return buff.getHeight();

    } catch (IOException e) {
      return -1;
    }

  }

  /**
   * Retrieves the height of the given image through the use of the ImageIO class.
   *
   * @param filename the filename of the image.
   * @return an integer representing the height of the given image in pixels.
   */
  public static int getImageFIleWidth(String filename) {
    try {
      InputStream input = new FileInputStream(filename);

      BufferedImage buff = ImageIO.read(input);

      return buff.getWidth();

    } catch (IOException e) {
      return -1;
    }

  }

  /**
   * This method writes an Image file of the given type, which is the MIME extension of the file,
   * only offering support that is offered by the ImageIO class. Utilizing helper methods, this
   * method is able to write an image file. (types required for now: png, jpeg, bmp).
   *
   * @param image the image object that has been changed, that will be saved.
   * @param name  the name of the file destination, containing the MIME extension.
   * @throws IOException if the ImageIO class has any issue in writing the file.
   */
  public static void writeImageFile(Image image, String name) throws IOException {

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
      String type = name.substring(name.lastIndexOf(".") + 1);
      ImageIO.write(img1, type, outputfile);
    } catch (IOException e) {
      System.out.println("Error writing file");
    }

  }

  /**
   * Retrieves the width of the given image in pixels.
   *
   * @param image the given image object by which to get the width of.
   * @return the width of an image as an integer.
   */
  private static int getFormatWidth(Image image) {
    return image.getWidth();
  }

  /**
   * Retrieves the height of the given image in pixels.
   *
   * @param image the given image object by which to get the height of.
   * @return the height of an image as an integer.
   */
  private static int getFormatHeight(Image image) {
    return image.getHeight();
  }
}
