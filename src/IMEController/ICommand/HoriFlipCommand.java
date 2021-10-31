package IMEController.ICommand;

import java.util.Scanner;

import IMEModel.ImageModel;
import IMEView.IMEView;

public class HoriFlipCommand extends AbstractCommand {
  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String toImage = getStringInput(sc);

    if (setUpImage(model, view, fromImage, toImage)) {
      model.flipHorizontal(toImage);
    }
  }

  @Override
  public String helpMessage() {
    return "horizontal-flip [image to flip] [new image name]";
  }

  @Override
  public String commandText() {
    return "horizontal-flip";
  }
}
