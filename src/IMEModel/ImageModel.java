package IMEModel;

/**
 * This interface acts as the interface to the main Model of the M,V,C design.
 */
public interface ImageModel {

  /**
   * Returns a 3 dimensional integer array, representing the rgb values of each pixel.
   * @return a 3d array of integers displaying the rgb values of each pixel.
   */
  public int[][][] getImageValues();

  /**
   * Sets the image correctly and loads it.
   * @param image the image as an Image Object.
   */
  void loadImage(Image image);

  /**
   * Brightens the image by the given amount, either increasing or decreasing if positive/negative.
   * @param increase the integer by which to brighten the image.
   */
  void brighten(int increase);

  /**
   * Vertically flips the image.
   */
  void flipVertical();

  /**
   * Horizontally flips the image.
   */
  void flipHorizontal();

  /**
   * Converts each pixel of the image to its greyscale value using its luma.
   */
  void greyscaleByLuma();




}
