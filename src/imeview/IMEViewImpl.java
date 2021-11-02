package imeview;

import java.io.IOException;
import java.io.PrintStream;

import imemodel.ImageModel;

/**
 * The view class representative of the View in the M, V, C design. This View class for now has
 * two methods, rendering a given message and an overridden toString method to display the images
 * loaded and/or available to the user.
 */
public class IMEViewImpl implements IMEView {

  //Appendable object for messages.
  private final Appendable out;

  //The ImageModel object.
  private final ImageModel model;


  /**
   * Main constructor that sets a default PrintStream to System.out.
   *
   * @param model the ImageModel object by which the view displays needed info.
   * @throws IllegalArgumentException if the model object is null.
   */
  public IMEViewImpl(ImageModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The given model must not be null!");
    }
    this.model = model;
    this.out = new PrintStream(System.out);
  }

  /**
   * A testing constructor. Sets the output stream to the given appendable.
   *
   * @param model the ImageModel object by which to display its info given the View's methods.
   * @param out   the appendable object in which the view's methods will be displayed.
   * @throws IllegalArgumentException if either the model or appendable object are null.
   */
  public IMEViewImpl(ImageModel model, Appendable out) throws IllegalArgumentException {
    if (model == null || out == null) {
      throw new IllegalArgumentException("The given model or appendable object must not be null!");
    }
    this.model = model;
    this.out = out;
  }

  @Override
  public void renderMessage(String message) {
    try {
      out.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("Cannot print out. Appendable is broken.");
    }
  }

  @Override
  public String toString() {
    String str = "|===============\n| Loaded images:\n";
    for (String image : model.getKeys()) {
      str += "| " + image + "\n";
    }
    str += "|===============";
    return str;
  }
}
