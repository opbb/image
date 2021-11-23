package imecontroller.iguicommand;

import java.util.Map;

import imecontroller.icommand.ICommand;
import imemodel.ImageModel;

/**
 * Command which gets the green component of a specific Image in the given model.
 * The command to get the green component, image to change, and name to store the green component
 * image under are all supplied by the user as input.
 */
public class GreenValueGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view, Map<String, ICommand> commands)
          throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String toImage = getStringInput(sc);

    if (setUpImage(model, view, fromImage, toImage)) {
      model.getByComponent(toImage, "green");
    }
  }

  @Override
  public String helpMessage() {
    return "green-value [image to get green-value of] [new image name]";
  }

  @Override
  public String commandText() {
    return "green-value";
  }
}


