package imemodel;

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
   * @throws IllegalArgumentException if the 3d array of integers is null.
   */
  public ImageImpl(int[][][] image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("The given 3d array must not be null!");
    }
    this.pixels = image;
    this.height = image.length;
    this.width = image[0].length;
  }

  /**
   * Default Constructor used for test cases.
   */
  public ImageImpl() {
    this.pixels = new int[][][]{{{2, 255, 0}}, {{90, 180, 200}}};
    this.height = new int[][][]{{{2, 255, 0}}, {{90, 180, 200}}}.length;
    this.width = new int[][][]{{{2, 255, 0}}, {{90, 180, 200}}}[0].length;
  }

  /**
   * This constructor allows for a different sized image, in which the array of integers is
   * completely customizable and therefore subject to change.
   *
   * @param image  the array of integers expressing the coordinates and rgb values of each pixel.
   * @param height the height of the image as an integer.
   * @param width  the width of the image as an integer.
   * @throws IllegalArgumentException if the image (3d array) is null or the height or width is
   *                                  less than zero.
   */
  public ImageImpl(int[][][] image, int height, int width) throws IllegalArgumentException {
    if (image == null || height < 0 || width < 0) {
      throw new IllegalArgumentException("The 3d array cannot be null and the height and " +
              "weight must not be less than zero!");
    }
    this.pixels = image;
    this.height = height;
    this.width = width;
  }

  /**
   * This constructor takes in a file type as string that will be read and instantiate the fields
   * of this class correspondent to that of the file/image.
   *
   * @param file the source path of the file as a String.
   * @throws IllegalArgumentException if the given filename is null or empty.
   */
  public ImageImpl(String file) throws IllegalArgumentException {

    if (file == null || file.equals("")) {
      throw new IllegalArgumentException("The given filename must not be null!");
    }

    if ((file.substring(file.lastIndexOf(".") + 1)).equals("ppm")) {

      try {
        this.pixels = ImageUtil.readPPM(file);
        this.height = ImageUtil.getHeight(file);
        this.width = ImageUtil.getWidth(file);
      } catch (IOException e) {
        throw new IllegalArgumentException("Was not able to read this file.");
      }

    } else {

      try {
        this.pixels = Formats.readImageFIle(file);
        //this.height = Formats.getImageFileHeight(file);
        this.height = pixels.length;
        //this.width = Formats.getImageFIleWidth(file);
        this.width = pixels[0].length;
      } catch (IOException e) {
        throw new IllegalArgumentException("Was not able to read this file.");
      }

    }
  }


  @Override
  public int[][][] getPixels() {
    return this.pixels;
  }

  @Override
  public void setPixels(int[][][] newList) throws IllegalArgumentException {
    if (newList == null) {
      throw new IllegalArgumentException("The new given list must not be null!");
    }
    pixels = newList;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public void setWidth(int width) {
    this.width = width;
  }


}