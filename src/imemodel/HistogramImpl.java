package imemodel;


import java.util.Set;


/**
 * This class represents a use of composition in extending the functionality of the ImageModelImpl
 * class. Within this class only method has been changed, in which we receive histogram data
 * of the RGB and intensity values of an image and store it within a 2d Integer array.
 */
public class HistogramImpl implements Histogram {

  private final ImageModel delegate;

  /**
   * Main constructor takes in a type of ImageModel and delegates it to this class's delegate
   * object/field.
   * @param model the ImageModel to be set as this class' delegate.
   */
  public HistogramImpl(ImageModel model) {
    this.delegate = model;
  }

  @Override
  public int[][] getHistogramData(int[][][] pixels) {

    int[][][] image = pixels;

    int[][] result = new int[256][4];
    int red = 0;
    int blue = 0;
    int green = 0;
    int intensity = 0;

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {

        blue = image[i][j][2];
        green = image[i][j][1];
        red = image[i][j][0];
        intensity = ((blue + green + red) / 3);
        result[red][0]++;
        result[green][1]++;
        result[blue][2]++;
        result[intensity][3]++;
      }

    }
    return result;
  }



  @Override
  public int[][][] getImageValues(String name) {
    return delegate.getImageValues(name);
  }

  @Override
  public void loadImage(String name, Image image) {

    delegate.loadImage(name, image);
  }

  @Override
  public void duplicateImage(String name, String newName) {

    delegate.duplicateImage(name, newName);
  }

  @Override
  public Image getImage(String name) {
    return delegate.getImage(name);
  }

  @Override
  public boolean hasImage(String name) {
    return delegate.hasImage(name);
  }

  @Override
  public void getByComponent(String name, String comp) {

    delegate.getByComponent(name, comp);
  }

  @Override
  public void brighten(String name, double increase) {

    delegate.brighten(name, increase);
  }

  @Override
  public void closeImage(String name) {

    delegate.closeImage(name);
  }

  @Override
  public void flipVertical(String name) {
    delegate.flipVertical(name);

  }

  @Override
  public void flipHorizontal(String name) {

    delegate.flipHorizontal(name);
  }

  @Override
  public void greyscaleByLuma(String name) {

    delegate.greyscaleByLuma(name);
  }

  @Override
  public Set<String> getKeys() {
    return delegate.getKeys();
  }
}
