package IMEModel;

import java.util.Arrays;

public class ImageModelMock implements ImageModel {

  private final StringBuilder log;

  public ImageModelMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public int[][][] getImageValues(String name) {
    log.append("name: " + name);
    return new int[0][][];
  }

  @Override
  public void loadImage(String name, Image image) {
    log.append("name: " + name + " image:" + Arrays.deepToString(image.getPixels()));
  }

  @Override
  public Image getImage(String name) {
    log.append("name: " + name);
    return null;
  }

  @Override
  public void brighten(String name, double increase) {
    log.append("name: " + name + " increase: " + increase);
  }

  @Override
  public void flipVertical(String name) {
    log.append("name: " + name);
  }

  @Override
  public void flipHorizontal(String name) {
    log.append("name: " + name);
  }

  @Override
  public void greyscaleByLuma(String name) {
    log.append("name: " + name);
  }
}
