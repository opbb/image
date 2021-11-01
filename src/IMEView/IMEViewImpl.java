package IMEView;

import java.io.IOException;
import java.io.PrintStream;

import IMEModel.Image;
import IMEModel.ImageModel;

/**
 * A view class for rendering messages to the user.
 */
public class IMEViewImpl implements IMEView {

  private final Appendable out;

  ImageModel model;

  public IMEViewImpl(ImageModel model) {
    this.model = model;
  }

  /**
   * A basic constructor. Sets the output stream to System.out.
   */
  public IMEViewImpl() {
    this.out = new PrintStream(System.out);
  }

  /**
   * A testing constructor. Sets the output stream to the given appendable.
   * @param out
   */
  public IMEViewImpl(Appendable out) {
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
    String str = "|==============\n|Loaded images:\n";
    for
  }
}
