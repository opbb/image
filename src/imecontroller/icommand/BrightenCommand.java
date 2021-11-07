package imecontroller.icommand;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import imemodel.ImageModel;
import imeview.IMEView;

/**
 * Command which brightens a specific Image in the given model by an amount.
 * The amount to brighten, image to brighten, and name to store the brightened image under
 * are all supplied by the user as input.
 */
public class BrightenCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc, Map<String, ICommand> commands)
          throws IllegalStateException {
    try {
      double amount = getDoubleInput(view, sc);
      String fromImage = getStringInput(sc);
      String toImage = getStringInput(sc);


      if (setUpImage(model,view,fromImage,toImage)) {
        model.brighten(toImage, amount);
      }
    } catch (InputMismatchException e) {


      // Throw out the next 3 inputs from the Scanner so they don't get interpreted as commands.
      getStringInput(sc);
      getStringInput(sc);
      getStringInput(sc);

      // An error message has already been rendered, so we simply ignore the exception.

    }
  }

  @Override
  public String helpMessage() {
    return "brighten [amount to brighten] [image to brighten] [new image name]";
  }

  @Override
  public String commandText() {
    return "brighten";
  }
}
