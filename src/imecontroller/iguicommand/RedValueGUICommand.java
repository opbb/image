package imecontroller.iguicommand;

import java.util.Map;

import imecontroller.icommand.ICommand;
import imemodel.ImageModel;


/**
 * Command which gets the red component of a specific Image in the given model.
 * The command to get the red component, image to change, and name to store the red component
 * image under are all supplied by the user as input.
 */
public class RedValueGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view, Map<String, ICommand> commands)
          throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String toImage = getStringInput(sc);

    if (setUpImage(model, view, fromImage, toImage)) {
      model.getByComponent(toImage, "red");
    }
  }

  @Override
  public String helpMessage() {
    return "red-value [image to get red-value of] [new image name]";
  }

  @Override
  public String commandText() {
    return "red-value";
  }
}

