package IMEModel;

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
}
