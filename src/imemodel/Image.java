package imemodel;


/**
 * This interface represents an Image object, in which it has simple methods to receive the pixel
 * data, the height of the image, and the width of the image.
 */
public interface Image {


  /**
   * This method gets the 3d array of integer values representing the rgb values of
   * each pixel in an Image object.
   *
   * @return a 3d array of integers representing the pixels of an image.
   */
  int[][][] getPixels();

  /**
   * Sets the pixels to a new 3d array of integers.
   *
   * @param newList a 3d integer array to which the pixels of the image will be set to.
   */
  void setPixels(int[][][] newList) throws IllegalArgumentException;

  /**
   * This method gets the height of the Image object, as in how many pixels tall the image is.
   * @return the height of the image file as an integer.
   */
  int getHeight();

  /**
   * This method gets the width of the Image object, as in how many pixels wide the image is.
   * @return the width of the image file as an integer.
   */
  int getWidth();

  /**
   * This method sets the height of the image, used only in resizing an image.
   * @param height the number by which to set the height dimension of an image to.
   */
  void setHeight(int height);

  /**
   * This method sets the width of the image, used in resizing an image.
   * @param width the number by which to set the new width of an image.
   */
  void setWidth(int width);

}
