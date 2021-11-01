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
  static Image applyMultipliedEffect(Image image, double[][] effect) {
    if (image == null) {
      throw new IllegalArgumentException("The given image cannot be null!");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] newImage = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          newImage[i][j][k] = clamp((int) Math.round(multiplyEffect(image.getPixels()[i][j],
                  effect[k])));
        }
      }
    }
    return new ImageImpl(newImage);
  }


  /**
   * This method adds to the pixels the given effect.
   * @param image the given image to which its pixels will be affected.
   * @param effect the effect as a 2d integer array.
   * @return the image changed by the given effect.
   */
  static Image applyAddedEffect(Image image, double effect) {
    if (image == null) {
      throw new IllegalArgumentException("The given image cannot be null!");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] newImage = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        newImage[i][j][0] = clamp((int) Math.round(addEffect(image.getPixels()[i][j][0], effect)));
        newImage[i][j][1] = clamp((int) Math.round(addEffect(image.getPixels()[i][j][1], effect)));
        newImage[i][j][2] = clamp((int) Math.round(addEffect(image.getPixels()[i][j][2], effect)));
      }
    }
    return new ImageImpl(newImage);
  }


  /**
   * This method sets the given component to the pixels.
   * @param image the given image to which its pixels will be affected.
   * @param component the component by which to change/set the image by.
   * @return the image changed by the given effect.
   */
  static Image applySetEffect(Image image, String component) {
    if (image == null) {
      throw new IllegalArgumentException("The given image cannot be null!");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] newImage = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        newImage[i][j][0] = clamp((int) Math.round(setEffect(image.getPixels()[i][j], component)));
        newImage[i][j][1] = clamp((int) Math.round(setEffect(image.getPixels()[i][j], component)));
        newImage[i][j][2] = clamp((int) Math.round(setEffect(image.getPixels()[i][j], component)));
      }
    }
    return new ImageImpl(newImage);
  }

  /**
   * Helper method that helps to mathematically multiplies effects to an image.
   * @param pixel the given pixel and its rgb values to be affected.
   * @param effect the effect as an array of what to add to the pixels.
   * @return
   */
  private static double multiplyEffect(int[] pixel, double[] effect) {
    double newPixel = 0;
    for (int i = 0; i < effect.length; i++) {
      newPixel += pixel[i] * effect[i];
    }
    return newPixel;
  }

  /**
   * Helper method that helps to mathematically add effects to an image.
   * @param pixel the given pixel and its rgb values to be affected.
   * @param effect the effect as an array of what to add to the pixels.
   * @return
   */
  private static double addEffect(int pixel, double effect) {
    double newPixel = 0;
    newPixel = pixel + effect;
    return newPixel;
  }

  /**
   * Helper method that helps to set a component greyscale to an image.
   * @param pixel the given pixel's rgb values.
   * @param component the component by which to set the image's rgb values.
   * @return the new pixel by which the rest of the rgb should be set equal.
   */
  private static double setEffect(int[] pixel, String component) {
    double newPixel = 0;
    for (int i = 0; i < 3; i++) {
      switch (component) {
        case "red":
          newPixel = pixel[0];
          break;
        case "green":
          newPixel= pixel[1];
          break;
        case "blue":
          newPixel = pixel[2];
          break;
        case "intensity":
          newPixel = ((pixel[0] + pixel[1] + pixel[2]) / 3);
          break;
        case "value":
          if (pixel[i] > newPixel) {
            newPixel = pixel[i];
          }
          break;
      }
    }
    return newPixel;
  }

  /**
   * Private helper method that determines if a given pixel is out of its required range.
   *
   * @param pixel pixel to be adjusted/checked.
   */
  private static int clamp(int pixel) {
    int min = 0;
    int max = 255;
    if (pixel < min) {
      pixel = min;
    } else if (pixel > max) {
      pixel = max;
    }
    return pixel;
  }
}
