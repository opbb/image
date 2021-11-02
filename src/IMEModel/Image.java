package IMEModel;




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
  void setPixels(int[][][] newList) throws IllegalArgumentException;

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

}
