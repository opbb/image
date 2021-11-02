package imemodel;

import java.util.Arrays;
import java.util.Set;

/**
 * A mock which logs the function that was called and the inputs it was given.
 * This version returns true for the hasImage method.
 */
public class ImageModelMockTrue implements ImageModel {

  protected final StringBuilder log;

  public ImageModelMockTrue(StringBuilder log) {
    this.log = log;
  }

  @Override
  public int[][][] getImageValues(String name) {
    log.append("getImageValues) name: " + name + "\n");
    return new int[0][][];
  }

  @Override
  public void loadImage(String name, Image image) {
    log.append("loadImage) name: " + name + " image: "
            + Arrays.deepToString(image.getPixels()) + "\n");
  }

  @Override
  public void duplicateImage(String name, String newName) {
    log.append("duplicateImage) name: " + name + " newName: " + newName + "\n");
  }

  @Override
  public Image getImage(String name) {
    log.append("getImage) name: " + name + "\n");
    return null;
  }

  @Override
  public boolean hasImage(String name) {
    log.append("hasImage) name: " + name + "\n");
    return true;
  }

  @Override
  public void getByComponent(String name, String comp) {
    log.append("getByComponent) name: " + name + " comp: " +  comp + "\n");
  }

  @Override
  public void brighten(String name, double increase) {
    log.append("brighten) name: " + name + " increase: " + increase + "\n");
  }

  @Override
  public void closeImage(String name) {
    log.append("closeImage) name: " + name + "\n");
  }

  @Override
  public void flipVertical(String name) {
    log.append("flipVertical) name: " + name + "\n");
  }

  @Override
  public void flipHorizontal(String name) {
    log.append("flipHorizontal) name: " + name + "\n");
  }

  @Override
  public void greyscaleByLuma(String name) {
    log.append("greyscaleByLuma) name: " + name + "\n");
  }

  @Override
  public Set<String> getKeys() {
    log.append("getKeys)\n");
    return null;
  }
}
