package imecontroller.iguicommand;

import java.util.Map;

import imecontroller.icommand.ICommand;
import imemodel.ImageModel;


/**
 * This command is triggered via user input and will greyscale the associated image by its pixels'
 * luma component.
 */
public class LumaValueGUICommand extends AbstractGUICommand {
  @Override
  public void execute(ImageModel model, IMEGUIView view, Map<String, ICommand> commands)
          throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String toImage = getStringInput(sc);

    if (setUpImage(model, view, fromImage, toImage)) {
      model.greyscaleByLuma(toImage);
    }
  }

  @Override
  public String helpMessage() {
    return "luma-value [image to get luma-value of] [new image name]";
  }

  @Override
  public String commandText() {
    return "luma-value";
  }
}
