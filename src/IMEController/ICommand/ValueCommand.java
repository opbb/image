package IMEController.ICommand;

import java.util.Scanner;

import IMEModel.ImageModel;
import IMEView.IMEView;

public class ValueCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
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
