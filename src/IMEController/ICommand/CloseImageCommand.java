package IMEController.ICommand;

import java.util.Scanner;

import IMEModel.ImageModel;
import IMEView.IMEView;

public class CloseImageCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
    String fromImage = getStringInput(sc);


    if (model.hasImage(fromImage)) {
      model.closeImage(fromImage);
    }
  }

  @Override
  public String helpMessage() {
    return "close-image [image to close] [new image name]";
  }

  @Override
  public String commandText() {
    return "close-image";
  }


}
