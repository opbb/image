package IMEModel;

/**
 * This interface acts as the interface to the main Model of the M,V,C design.
 */
public interface ImageModel {

  /**
   * Returns a 3 dimensional integer array, representing the rgb values of each pixel.
   * @return a 3d array of integers displaying the rgb values of each pixel.
   */
  public int[][][] getImageValues(String name, Image image);

  /**
   * Sets the image correctly and loads it.
   * @param image the image as an Image Object.
   */
  void loadImage(String name, Image image);

  Image getImage(String name);

  /**
   * Brightens the image by the given amount, either increasing or decreasing if positive/negative.
   * @param increase the integer by which to brighten the image.
   */
  void brighten(String name, Image image, double increase);

  /**
   * Vertically flips the image.
   */
  void flipVertical(String name, Image image);

  /**
   * Horizontally flips the image.
   */
  void flipHorizontal(String name, Image image);

  /**
   * Converts each pixel of the image to its greyscale value using its luma.
   */
  void greyscaleByLuma(String name, Image image);




}
