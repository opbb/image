package imecontroller.iguicommand;

import java.util.Map;

import imecontroller.icommand.ICommand;
import imemodel.ImageModel;

/**
 * This class holds acts as the close command to be given to the map of commands, effectively
 * closing out an image from the application.
 */
public class CloseGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view, Map<String, ICommand> commands)
          throws IllegalStateException {
    String fromImage = getStringInput(sc);


    if (model.hasImage(fromImage)) {
      model.closeImage(fromImage);
    } else {
      view.renderMessage("The given image name " + fromImage + " does not exist.\n\n");
    }
  }

  @Override
  public String helpMessage() {
    return "close [image to close]";
  }

  @Override
  public String commandText() {
    return "close";
  }
}
