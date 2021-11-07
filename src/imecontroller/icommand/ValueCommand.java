package imecontroller.icommand;

import java.util.Map;
import java.util.Scanner;

import imemodel.ImageModel;
import imeview.IMEView;

/**
 * This command is triggered via user input and will greyscale the associated image by its pixels'
 * value component.
 */
public class ValueCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc, Map<String, ICommand> commands) throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String toImage = getStringInput(sc);

    if (setUpImage(model, view, fromImage, toImage)) {
      model.getByComponent(toImage, "value");
    }
  }

  @Override
  public String helpMessage() {
    return "value [image to get value of] [new image name]";
  }

  @Override
  public String commandText() {
    return "value";
  }
}
