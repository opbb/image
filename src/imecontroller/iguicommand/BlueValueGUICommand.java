package imecontroller.iguicommand;

import java.util.Map;

import imecontroller.icommand.ICommand;
import imemodel.ImageModel;


/**
 * Command which gets the blue component of a specific Image in the given model.
 * The command to get the blue component, image to change, and name to store the blue component
 * image under are all supplied by the user as input.
 */
public class BlueValueGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view, Map<String, ICommand> commands)
          throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String toImage = getStringInput(sc);

    if (setUpImage(model, view, fromImage, toImage)) {
      model.getByComponent(toImage, "blue");
    }
  }

  @Override
  public String helpMessage() {
    return "blue-value [image to get blue-value of] [new image name]";
  }

  @Override
  public String commandText() {
    return "blue-value";
  }


}
