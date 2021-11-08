package imemodel;


/**
 * This interface represents the use of composition in adding new image filters, specifically
 * the ability to blur, sharpen, and sepia version of an image.
 */
public interface ExtraFilters extends ImageModel {

  /**
   * This image uses matrix manipulation to blur the given image.
   * @param name the name of the image as represented as the key within the Model's hashmap.
   */
  void blur(String name);

  /**
   * This image uses matrix manipulation to sharpen the given image.
   * @param name the name of the image as represented as the key within the Model's hashmap.
   */
  void sharpen(String name);

  /**
   * This image uses matrix manipulation to display the sepia tone of the given image.
   * @param name the name of the image as represented as the key within the Model's hashmap.
   */
  void toSepia(String name);


}
