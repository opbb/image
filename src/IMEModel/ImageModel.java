package IMEModel;

/**
 * This interface acts as the interface to the main Model of the M,V,C design.
 */
public interface ImageModel {

  /**
   * Returns a 3 dimensional integer array, representing the rgb values of each pixel.
   * @return a 3d array of integers displaying the rgb values of each pixel.
   */
  public int[][][] getImageValues(String name);

  /**
   * Sets the image correctly and loads it.
   * @param image the image as an Image Object.
   */
  void loadImage(String name, Image image);

  /**
   * Duplicates the Image with the given name, and gives the duplicate the new name.
   * @param name name of image to duplicate
   * @param newName name of new, duplicated image
   */
  void duplicateImage(String name, String newName);

  Image getImage(String name);

  /**
   * Determines whether this model has an image under the given name.
   * @param name the name to check for
   * @return whether this model has an image of the given name
   */
  boolean hasImage(String name);


  /**
   * Gets the greyscale component by which to alter the image and performs this change.
   * @param name the name of the file.
   * @param comp the name of the greyscale component.
   */
  void getByComponent(String name, String comp);

  /**
   * Brightens the image by the given amount, either increasing or decreasing if positive/negative.
   * @param increase the integer by which to brighten the image.
   */
  void brighten(String name, double increase);

  void closeImage(String name);

  /**
   * Vertically flips the image.
   */
  void flipVertical(String name);

  /**
   * Horizontally flips the image.
   */
  void flipHorizontal(String name);

  /**
   * Converts each pixel of the image to its greyscale value using its luma.
   */
  void greyscaleByLuma(String name);

  /**
   * Converts the model to a String of image names and arrays.
   */
  String toString();


}
