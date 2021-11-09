package imecontroller.icommand;

import java.util.Map;
import java.util.Scanner;

import imemodel.ImageModel;
import imeview.IMEView;


/**
 * This command is triggered via user input and will greyscale the associated image by its pixels'
 * luma component.
 */
public class LumaValueCommand extends AbstractCommand {
  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc, Map<String, ICommand> commands)
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
