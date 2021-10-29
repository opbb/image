package IMEModel;

/**
 * This interface represents a set of methods to which effects can be applied to images.
 */
public interface Application {


  /**
   * This method multiplies the pixels by the given effect.
   * @param image the given image to which its pixels will be affected.
   * @param effect the effect as a 2d integer array.
   * @return the image changed by the given effect.
   */
  Image applyMultipliedEffect(Image image, double[][] effect);


  /**
   * This method adds to the pixels the given effect.
   * @param image the given image to which its pixels will be affected.
   * @param effect the effect as a 2d integer array.
   * @return the image changed by the given effect.
   */
  Image applyAddedEffect(Image image, double effect);


  /**
   * This method sets the given component to the pixels.
   * @param image the given image to which its pixels will be affected.
   * @param component the component by which to change/set the image by.
   * @return the image changed by the given effect.
   */
  Image applySetEffect(Image image, String component);
}
