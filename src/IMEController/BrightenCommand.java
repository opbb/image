package IMEController;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import IMEModel.ImageModel;
import IMEView.IMEView;

/**
 * Command which brightens a specific Image in the given model by an amount.
 * The amount to brighten, image to brighten, and name to store the brightened image under
 * are all supplied by the user as input.
 */
public class BrightenCommand extends AbstractCommand {
  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
    try {
      double amount = getDoubleInput(view, sc);
      String fromName = getStringInput(sc);
      String toName = getStringInput(sc);

      model.duplicateImage(fromName,toName);
      model.brighten(toName, amount);
    } catch (InputMismatchException e) {
      // An error message has already been rendered, so we simply ignore the exception.
    }
  }

  @Override
  public String helpMessage() {
    return "brighten [amount to brighten] [image to brighten] [new image name]";
  }
}
