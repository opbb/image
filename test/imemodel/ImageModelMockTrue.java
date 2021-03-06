package imemodel;


import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * A mock which logs the function that was called and the inputs it was given.
 * This version returns true for the hasImage method.
 */
public class ImageModelMockTrue implements ImageModel {

  protected final StringBuilder log;

  /**
   * Mock constructor.
   *
   * @param log Stringbuilder object.
   */
  public ImageModelMockTrue(StringBuilder log) {
    this.log = log;
  }

  @Override
  public int[][][] getImageValues(String name) {
    log.append("getImageValues) name: " + name + "\n");
    int[][][] values = new int[1][1][3];
    values[0][0][0] = 1;
    values[0][0][1] = 2;
    values[0][0][2] = 3;
    return values;
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
    int[][][] values = new int[1][1][3];
    values[0][0][0] = 1;
    values[0][0][1] = 2;
    values[0][0][2] = 3;
    return new ImageImpl(values);
  }

  @Override
  public boolean hasImage(String name) {
    log.append("hasImage) name: " + name + "\n");
    return true;
  }

  @Override
  public void getByComponent(String name, String comp) {
    log.append("getByComponent) name: " + name + " comp: " + comp + "\n");
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
    Set set = new TreeSet<String>();
    set.add("key0");
    set.add("key1");
    set.add("key2");
    return set;
  }

  @Override
  public void resize(int newHeight, int newWidth, String imageName) {

  }

  @Override
  public void maskedImagify(String name, String maskedImage) {

  }

  @Override
  public void applyMaskedChanges(String name, String maskedImage) {

  }

  @Override
  public String toString() {
    return "toString) This is a mock model.\n";
  }
}
