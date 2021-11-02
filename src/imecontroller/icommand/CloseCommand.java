package imecontroller.icommand;

import java.util.Scanner;

import imemodel.ImageModel;
import imeview.IMEView;

public class CloseCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
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
