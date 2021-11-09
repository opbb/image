package imemodel;

/**
 * This interface represents a set of methods to which effects can be applied to images.
 */
public class Application {


  //Stuck with this implementation for Luma (and sepia) since it had already satisfied the assignment
  //requirement.

  /**
   * This method multiplies the pixels by the given effect, used now for mostly luma.
   *
   * @param image  the given image to which its pixels will be affected.
   * @param effect the effect as a 2d double array.
   * @return the image changed by the given effect.
   */
  static Image applyMultipliedTransformation(Image image, double[][] effect) {
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
   * This method multiplies the pixels by the given effect.
   *
   * @param image  the given image to which its pixels will be affected.
   * @param effect the effect as a 2d double array.
   * @return the image changed by the given effect.
   */
  public static Image applyMultipliedEffect(Image image, double[][] effect) {
    if (image == null) {
      throw new IllegalArgumentException("The given image cannot be null!");
    }
    int height = image.getHeight();
    int width = image.getWidth();
    int[][][] toImage = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        toImage[i][j] = multiplyEffect(getKernels(i, j, image.getPixels(), effect.length), effect);
      }
    }
    return new ImageImpl(clampPixel(toImage));


  }


  /**
   * Private helper method that retrieves the kernels of an image, used for matrix manipulation
   * to support the use of multiple filters.
   *
   * @param row    the row of a pixel within the kernel.
   * @param col    the column of a pixel within the kernel.
   * @param image  the 3d array that contains the pixels of the image.
   * @param matrix the matrix size, designated as the size of the filter length.
   * @return the image kernel displayed by a 3d array.
   */
  private static int[][][] getKernels(int row, int col, int[][][] image, int matrix) {

    int[][][] kernel = new int[matrix][matrix][3];
    int kernelCoord = (int) Math.floor(matrix / 2.0);


    for (int i = 0; i < matrix; i++) {
      for (int j = 0; j < matrix; j++) {
        try {
          kernel[i][j][0] = image[i + (row - kernelCoord)][j + (col - kernelCoord)][0];
          kernel[i][j][1] = image[i + (row - kernelCoord)][j + (col - kernelCoord)][1];
          kernel[i][j][2] = image[i + (row - kernelCoord)][j + (col - kernelCoord)][2];

        } catch (IndexOutOfBoundsException e) {
          kernel[i][j][0] = 0;
          kernel[i][j][1] = 0;
          kernel[i][j][2] = 0;
        }
      }
    }
    return kernel;

  }


  /**
   * This method adds to the pixels the given effect.
   *
   * @param image  the given image to which its pixels will be affected.
   * @param effect the effect as a 2d integer array.
   * @return the image changed by the given effect.
   */
  public static Image applyAddedEffect(Image image, double effect) {
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
   *
   * @param image     the given image to which its pixels will be affected.
   * @param component the component by which to change/set the image by.
   * @return the image changed by the given effect.
   */
  public static Image applySetEffect(Image image, String component) {
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


  //Kept this original method as it still is a helper method to the applyTransformation method
  //changed purpose statement slightly

  /**
   * Helper method that helps to mathematically multiplies effects to an image.
   *
   * @param pixel  the given pixel and its rgb values to be affected.
   * @param effect the effect as an array of what to add to the pixels.
   * @return a double representing the value of the rgb values within a pixel.
   */
  private static double multiplyEffect(int[] pixel, double[] effect) {
    double newPixel = 0;
    for (int i = 0; i < effect.length; i++) {
      newPixel += pixel[i] * effect[i];
    }
    return newPixel;
  }

  /**
   * Helper method that helps to mathematically multiplies effects to an image.
   *
   * @param kernel the given pixel and its rgb values to be affected.
   * @param effect the effect as an array of what to add to the pixels.
   * @return an int array representing the new rgb values of a pixel.
   */
  private static int[] multiplyEffect(int[][][] kernel, double[][] effect) {
    int[] newRGB = new int[3];
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel.length; j++) {
        newRGB[0] = newRGB[0] + (int) Math.round(kernel[i][j][0] * effect[i][j]);
        newRGB[1] = newRGB[1] + (int) Math.round(kernel[i][j][1] * effect[i][j]);
        newRGB[2] = newRGB[2] + (int) Math.round(kernel[i][j][2] * effect[i][j]);
      }
    }
    return newRGB;


  }

  /**
   * Helper method that helps to mathematically add effects to an image.
   *
   * @param pixel  the given pixel and its rgb values to be affected.
   * @param effect the effect as an array of what to add to the pixels.
   * @return a double value by which the rgb of a pixel should be set to.
   */
  private static double addEffect(int pixel, double effect) {
    double newPixel = 0;
    newPixel = pixel + effect;
    return newPixel;
  }

  /**
   * Helper method that helps to set a component greyscale to an image.
   *
   * @param pixel     the given pixel's rgb values.
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
          newPixel = pixel[1];
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
        default:
          //nothing required
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


  /**
   * Constrains the pixels of an image to a set size, ranging from 0 to 255.
   *
   * @param image the 3d array of integers representing the image's pixels.
   * @return a 3d array acting as the image, but its values constrained to a min and max value.
   */
  private static int[][][] clampPixel(int[][][] image) {
    int min = 0;
    int max = 255;
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        for (int k = 0; k < 3; k++) {
          if (image[i][j][k] < min) {
            image[i][j][k] = min;
          }
          if (image[i][j][k] > max) {
            image[i][j][k] = max;
          }
        }

      }
    }
    return image;
  }
}
