package imecontroller.interaction;

/**
 * Represents the printing of a sequence of lines to output.
 */
public class PrintInteraction implements Interaction {
  String[] lines;

  /**
   * Basic constructor.
   * @param lines is the text to print
   */
  public PrintInteraction(String... lines) {
    this.lines = lines;
  }

  @Override
  public void apply(StringBuilder in, StringBuilder out) {
    for (String line : lines) {
      out.append(line).append("\n");
    }
  }
}