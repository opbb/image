package imecontroller.iguicommand;

import java.util.Map;

import imecontroller.icommand.ICommand;
import imemodel.ImageModel;
import imeview.IMEGUIView;


/**
 * Command which gets the blue component of a specific Image in the given model.
 * The command to get the blue component, image to change, and name to store the blue component
 * image under are all supplied by the user as input.
 */
public class BlueValueGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view, Map<String,
          IGUICommand> commands, String imageName) {
      model.getByComponent(imageName, "blue");
    }

  @Override
  public String commandText() {
    return "blue-value";
  }
}
