package IMEModel;

import java.io.IOException;


/**
 * This class represents an implemented class object of the Image interface, and contains
 * two constructors in which to receive image data directly or preconfigured image data.
 */
public class ImageImpl implements Image {

  //The pixels with the most inner list being the rgb values
  private int[][][] pixels;

  //the height of the image
  private int height;

  //the width of the image
  private int width;


  /**
   * This constructor takes in a predetermined list of list of list of integers to represent the
   * pixels of an image.
   *
   * @param image a list of list of list of integers that will act as a preconfigured list of
   *              pixels.
   */
  public ImageImpl(int[][][] image) {
    this.pixels = image;
    this.height = image.length;
    this.width = image[0].length;
  }

  public ImageImpl(int[][][] image, int height, int width) {
    this.pixels = image;
    this.height = height;
    this.width = width;
  }

  /**
   * This constructor takes in a file type as string that will be read and instantiate the fields
   * of this class correspondent to that of the file/image.
   *
   * @param file the source path of the file as a String.
   */
  public ImageImpl(String file) throws IllegalArgumentException {
    try {
      this.pixels = ImageUtil.readPPM(file);
      this.height = ImageUtil.getHeight(file);
      this.width = ImageUtil.getWidth(file);
    } catch (IOException e) {
      throw new IllegalArgumentException("Was not able to read this file.");
    }

  }


  public int[][][] getPixels() {
    return this.pixels;
  }

  public void setPixels(int[][][] newList) {
    pixels = newList;
  }

  public int getHeight() {
    return this.height;
  }

  public int getWidth() {
    return this.width;
  }

  public int getValue() {
    int result = 0;
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        for (int k = 0; k < pixels[i][j].length; k++) {
          if (pixels[i][j][k] > result) {
            result = pixels[i][j][k];
          }
        }
      }
    }
    return result;
  }

  public int getIntensity() {

    int result = 0;
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        for (int k = 0; k < pixels[i][j].length; k++) {
          result = ((pixels[i][j][k] + pixels[i][j][1] +
                  pixels[i][j][2]) / 3);
        }
      }
    }
    return result;
  }


  public int getLuma() {
    double result = 0;
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        for (int k = 0; k < pixels[i][j].length; k++) {
          result = ((pixels[i][j][k] * 0.2126) + (pixels[i][j][1] * 0.7152) +
                  (pixels[i][j][2] * 0.0722));
        }
      }
    }
    return (int) result;
  }


}