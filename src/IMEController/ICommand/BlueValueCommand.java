package IMEController.ICommand;

import java.util.Scanner;

import IMEModel.ImageModel;
import IMEView.IMEView;

public class BlueValueCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
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
