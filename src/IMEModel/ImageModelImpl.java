package IMEModel;


import java.io.IOException;

/**
 * This class represents the model in the M,V,C format, and implements the ImageModel interface.
 * It is responsible for several actions, some simple such as returning the rgb values of all
 * the pixels and loading the image. Along with brightening or darkening, along with flipping the
 * image.
 */
public class ImageModelImpl implements ImageModel {

  // Image object
  private Image image;

  /**
   * Defualt constructor for test cases.
   */
  public ImageModelImpl() {
    this.image = new ImageImpl(new int[1][1][3]);
  }

  /**
   * Constructor for manipulating a file that will be taken in as a String.
   *
   * @param filename the file path.
   */
  public ImageModelImpl(String filename) {
    this.image = new ImageImpl(filename);
  }

  public ImageModelImpl(Image image) {
    this.image = image;
  }

  @Override
  public int[][][] getImageValues() {
    int[][][] values = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        values[i][j] = this.image.getPixels()[i][j];
      }
    }
    return values;
  }


  @Override
  public void loadImage(Image image) {

    this.image = image;

  }

  @Override
  public void greyscaleByLuma() {
     double[][] greyscale = {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}, {0.2126,
                0.7152, 0.0722}};
        this.image = new ApplicationImpl().applyMultipliedEffect(this.image, greyscale);

  }


  @Override
  public void brighten(double increase) {

    if (increase > 0) {
      double brighten = increase;
      this.image = new ApplicationImpl().applyAddedEffect(this.image, brighten);
    } else {
      double darken = increase;
      this.image = new ApplicationImpl().applyAddedEffect(this.image, darken);

    }


  }



  @Override
  public void flipVertical() {

    int[][][] pixels = image.getPixels();
    int width = image.getWidth();
    int height = image.getHeight();

    for (int i = 0; i < height/2; i++) {
      for (int j = 0; j < width; j++) {
       pixels[i][j] = pixels[height - i - 1][j];
      }
    }

    image.setPixels(pixels);

  }

  @Override
  public void flipHorizontal() {
    int[][][] pixels = image.getPixels();
    int width = image.getWidth();
    int height = image.getHeight();


    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width/2; j++) {
        pixels[i][j] = pixels[i][width - j - 1];
      }
    }

    image.setPixels(pixels);

  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("P3\n");
    str.append(image.getWidth()).append(" ").append(image.getHeight()).append("\n");
    str.append("255").append("\n");

//      str.append("255\n");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int[] vals = getImageValues()[i][j];
        str.append(vals[0]).append(" ").append(vals[1]).append(" ").append(vals[2]).append(" ");
      }
      str.append("\n");
    }
    return str.toString();
  }
}
