package imecontroller.iguicommand;

import java.util.Map;
import java.util.Scanner;

import imecontroller.icommand.ICommand;
import imemodel.ImageModel;
import imeview.IMEView;


/**
 * This command horizontally flips the image when advised by the user to do so, via user inputs
 * and will call for the model to use its flipHorizontal method.
 */
public class HoriFlipGUICommand extends AbstractGUICommand {
  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc, Map<String, ICommand> commands)
          throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String toImage = getStringInput(sc);

    if (setUpImage(model, view, fromImage, toImage)) {
      model.flipHorizontal(toImage);
    }
  }

  @Override
  public String helpMessage() {
    return "horizontal-flip [image to flip] [new image name]";
  }

  @Override
  public String commandText() {
    return "horizontal-flip";
  }
}
