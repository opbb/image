package imemodel;

public class FilterMock extends ImageModelMockTrue implements ExtraFilters {

  /**
   * Mock constructor.
   *
   * @param log Stringbuilder object.
   */
  public FilterMock(StringBuilder log) {
    super(log);
  }

  @Override
  public void blur(String name) {
    log.append("blur) name: " + name + "\n");
  }

  @Override
  public void sharpen(String name) {
    log.append("sharpen) name: " + name + "\n");
  }

  @Override
  public void toSepia(String name) {
    log.append("toSepia) name: " + name + "\n");
  }
}
