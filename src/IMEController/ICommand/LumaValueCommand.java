package IMEController.ICommand;

import java.util.Scanner;

import IMEModel.ImageModel;
import IMEView.IMEView;

public class LumaValueCommand extends AbstractCommand {
  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String toImage = getStringInput(sc);

    if(setUpImage(model, view, fromImage, toImage)) {
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