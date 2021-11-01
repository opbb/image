package IMEModel;

import java.util.Set;

/**
 * This interface acts as the interface to the main Model of the M,V,C design.
 */
public interface ImageModel {

  /**
   * Returns a 3 dimensional integer array, representing the rgb values of each pixel.
   *
   * @return a 3d array of integers displaying the rgb values of each pixel.
   */
  int[][][] getImageValues(String name);

  /**
   * Sets the image correctly and loads it.
   *
   * @param image the image as an Image Object.
   */
  void loadImage(String name, Image image);

  /**
   * Duplicates the Image with the given name, and gives the duplicate the new name.
   *
   * @param name    name of image to duplicate
   * @param newName name of new, duplicated image
   */
  void duplicateImage(String name, String newName);

  /**
   * Retrieves the image by name, this being the key in map of images.
   * @param name the given name to which to get its associated image.
   * @return the image associated with the given name within the hashmap field.
   */
  Image getImage(String name);

  /**
   * Determines whether this model has an image under the given name.
   *
   * @param name the name to check for
   * @return whether this model has an image of the given name
   */
  boolean hasImage(String name);


  /**
   * Gets the greyscale component by which to alter the image and performs this change, the first
   * string being that of the file name, or of which we are associating the image with, and the
   * second being what to grayscale the image by (component).
   *
   * @param name the name of the file.
   * @param comp the name of the greyscale component.
   */
  void getByComponent(String name, String comp);

  /**
   * Brightens the image by the given amount, either increasing or decreasing if positive/negative.
   *
   * @param increase the integer by which to brighten the image.
   */
  void brighten(String name, double increase);

  /**
   * Removes the image from the hashmap, and offering better UX in that the user maybe able to
   * close an image out from the application.
   * @param name the name associated with the image to get rid of.
   */
  void closeImage(String name);

  /**
   * Vertically flips the image, by rearranging the top half of the pixels with its lower half.
   */
  void flipVertical(String name);

  /**
   * Horizontally flips the image, by rearranging the left half of the pixels with its right half.
   */
  void flipHorizontal(String name);

  /**
   * Converts each pixel of the image to its greyscale value using its luma.
   */
  void greyscaleByLuma(String name);

  /**
   * Converts the model to a String of image names and arrays.
   * @return a String of the image name and values in arrays.
   */
  String toString();

  /**
   * Returns a set of the keys/names of the files associated with the images.
   * @return a set of the keys/names from the hashmap.
   */
  Set<String> getKeys();
}
