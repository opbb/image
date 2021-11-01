package IMEView;

/**
 * This interface represents the view of the M,V,C design. For now, it simply has one method that
 * will render a message to the user of the application, or whatever output stream is used.
 */
public interface IMEView {

  /**
   * Renders a message to the output stream.
   * @param message the message to be rendered.
   */
  void renderMessage(String message);
}
