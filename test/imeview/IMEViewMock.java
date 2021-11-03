package imeview;

/**
 * This class is a mock of the IMEView.
 */
public class IMEViewMock implements IMEView {

  private final StringBuilder log;

  /**
   * Mock constructor.
   *
   * @param log stringbuilder object.
   */
  public IMEViewMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void renderMessage(String message) {
    log.append("renderMessage) message: " + message + "\n");
  }

  @Override
  public String toString() {
    log.append("toString) This is a mock view.\n");
    return null;
  }
}
