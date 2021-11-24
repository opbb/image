package imeview;

import java.util.List;

/**
 * This interface represents the View of the M, V, C design and has for now two methods. The first
 * method renders a message and the other is an overridden toString. The view is responsible for
 * relaying back messages to the user in notifying them of errors from their inputs or the images
 * they have loaded so far.
 */
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
