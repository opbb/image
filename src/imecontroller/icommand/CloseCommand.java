package imecontroller.icommand;

import java.util.Map;
import java.util.Scanner;

import imemodel.ImageModel;
import imeview.IMEView;

/**
 * This class holds acts as the close command to be given to the map of commands, effectively
 * closing out an image from the application.
 */
public class CloseCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc, Map<String, ICommand> commands)
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
