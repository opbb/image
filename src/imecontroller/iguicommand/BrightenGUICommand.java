package imecontroller.iguicommand;

import java.util.InputMismatchException;
import java.util.Map;

import imecontroller.icommand.ICommand;
import imemodel.ImageModel;

/**
 * Command which brightens a specific Image in the given model by an amount.
 * The amount to brighten, image to brighten, and name to store the brightened image under
 * are all supplied by the user as input.
 */
public class BrightenGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view, Map<String, ICommand> commands)
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
