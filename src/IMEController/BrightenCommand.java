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
  public void excecute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
    try {
      double amount = sc.nextDouble();
      String fromName = sc.next();
      String toName = sc.next();

      model.duplicateImage(fromName,toName);
      model.brighten();
    } catch (InputMismatchException e) {
      view.renderMessage("Please input a double for [amount to brighten], and the command should"
              + "be structured as shown below:\nbrighten [amount to brighten] [image to brighten] "
              + "[new image name]\n\n");
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("Ran out of inputs.");
    }
  }

  @Override
  public String helpMessage() {
    return "brighten [amount to brighten] [image to brighten] [new image name]";
  }
}
