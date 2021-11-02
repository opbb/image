package imeview;

public interface IMEView {

  /**
   * Renders a message to the output stream.
   * @param message the message to be rendered.
   */
  void renderMessage(String message);

  /**
   * Returns a string representing the state of the model this view represents.
   * @return a string representing the state of the model.
   */
  String toString();
}
