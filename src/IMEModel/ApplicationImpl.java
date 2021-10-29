package IMEModel;

/**
 * ApplicationImpl represents a class that implements the Application interface and has helper
 * methods to apply the effects to the Image's pixels while returning an Image object.
 */
public class ApplicationImpl implements Application {

  @Override
  public Image applyMultipliedEffect(Image image, double[][] effect) {
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

  @Override
  public Image applyAddedEffect(Image image, double[][] effect) {
    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] newImage = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          newImage[i][j][k] = clamp((int) Math.round(addEffect(image.getPixels()[i][j], effect[k])));
        }
      }
    }
    return new ImageImpl(newImage);
  }

  @Override
  public Image applySetEffect(Image image, String component) {
    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] newImage = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          newImage[i][j][k] = clamp((int) Math.round(setEffect(image.getPixels()[i][j], component)));
        }
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
  private double multiplyEffect(int[] pixel, double[] effect) {
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
  private double addEffect(int[] pixel, double[] effect) {
    double newPixel = 0;
    for (int i = 0; i < effect.length; i++) {
      newPixel += pixel[i] + effect[i];
    }
    return newPixel;
  }

  /**
   * Helper method that helps to set a component greyscale to an image.
   * @param pixel the given pixel's rgb values.
   * @param component the component by which to set the image's rgb values.
   * @return the new pixel by which the rest of the rgb should be set equal.
   */
  private double setEffect(int[] pixel, String component) {
    double newPixel = 0;
    for (int i = 0; i < pixel.length; i++) {
      switch (component) {
        case "red":
        newPixel = pixel[0];
        case "green":
          newPixel= pixel[1];
        case "blue":
          newPixel = pixel[2];
        case "intensity":
          newPixel = ((pixel[1] + pixel[2] + pixel[3]) / 3);
        case "value":
          if (pixel[i] > newPixel) {
            newPixel = pixel[i];
          }
      }
    }
    return newPixel;
  }

  /**
   * Private helper method that determines if a given pixel is out of its required range.
   *
   * @param pixel pixel to be adjusted/checked.
   */
  private int clamp(int pixel) {
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
