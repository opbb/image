package imemodel;

/**
 * A mock which logs the function that was called and the inputs it was given.
 * This version returns false for the hasImage method.
 */
public class ImageModelMockFalse extends ImageModelMockTrue {
  public ImageModelMockFalse(StringBuilder log) {
    super(log);
  }

  @Override
  public boolean hasImage(String name) {
    log.append("hasImage) name: " + name + "\n");
    return false;
  }

  @Override
  public Image getImage(String name) {
    log.append("getImage) name: " + name + "\n");
    return null;
  }

  @Override
  public int[][][] getImageValues(String name) {
    log.append("getImageValues) name: " + name + "\n");
    return null;
  }
}
