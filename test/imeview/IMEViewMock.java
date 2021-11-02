package imeview;

public class IMEViewMock implements IMEView {

  private final StringBuilder log;

  public IMEViewMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void renderMessage(String message) {
    log.append("renderMessage) message: " + message + "\n");
  }

  @Override
  public String toString() {
    log.append("toString)\n");
    return null;
  }
}
