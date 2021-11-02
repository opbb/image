package IMEModel;

import java.util.Arrays;
import java.util.Set;

public class ImageModelMock implements ImageModel {

  private final StringBuilder log;

  public ImageModelMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public int[][][] getImageValues(String name) {
    log.append("name: " + name + "\n");
    return new int[0][][];
  }

  @Override
  public void loadImage(String name, Image image) {
    log.append("name: " + name + " image:" + Arrays.deepToString(image.getPixels()) + "\n");
  }

  @Override
  public void duplicateImage(String name, String newName) {
    log.append("name: " + name + " newName: " + newName + "\n");
  }

  @Override
  public Image getImage(String name) {
    log.append("name: " + name + "\n");
    return null;
  }

  @Override
  public boolean hasImage(String name) {
    log.append("name: " + name + "\n");
    return false;
  }

  @Override
  public void getByComponent(String name, String comp) {

  }

  @Override
  public void brighten(String name, double increase) {
    log.append("name: " + name + " increase: " + increase + "\n");
  }

  @Override
  public void closeImage(String name) {

  }

  @Override
  public void flipVertical(String name) {
    log.append("name: " + name + "\n");
  }

  @Override
  public void flipHorizontal(String name) {
    log.append("name: " + name + "\n");
  }

  @Override
  public void greyscaleByLuma(String name) {
    log.append("name: " + name + "\n");
  }

  @Override
  public Set<String> getKeys() {
    return null;
  }
}
