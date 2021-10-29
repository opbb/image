package IMEModel;

import java.util.List;


/**
 * This interface represents an Image object, in which it has simple methods to receive the pixel
 * data, the height of the image, and the width of the image.
 */
public interface Image {


  /**
   * This method returns the list of list of list of integer values representing the rgb values of
   * each pixel in an Image object.
   * @return a list of list of list of integers as the pixels in an object.
   */
  int[][][] getPixels();

  /**
   * Sets the pixels to a new 3d array of integers.
   * @param newList a 3d integer array to which the pixels of the image will be set to.
   */
  void setPixels(int[][][] newList);

  /**
   * This method returns the height of the Image object, as in how many pixels tall the image is.
   * @return an integer representing the height of the image.
   */
  int getHeight();

  /**
   * This method returns the width of the Image object, as in how many pixels wide the image is.
   * @return an integer representing the width of the image.
   */
  int getWidth();


  /**
   * This method returns the maximum value of the three rgb components of the Image for each pixel.
   * @return the maximum value of the three rgb components of each pixel as an integer.
   */
  int getValue();

  /**
   * This method returns the average of the three components for each pixel.
   * @return the average of the three rgb component values of each pixel.
   */
  int getIntensity();

  /**
   * This method returns the luma, a weigthed sum of a formula.
   * @return the luma of a pixel.
   */
  int getLuma();



}
