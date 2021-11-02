package imecontroller.icommand;

import java.util.Scanner;

import imemodel.ImageModel;
import imeview.IMEView;

public class IntensityValueCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String toImage = getStringInput(sc);

    if (setUpImage(model, view, fromImage, toImage)) {
      model.getByComponent(toImage, "intensity");
    }
  }

  @Override
  public String helpMessage() {
    return "intensity-value [image to get intensity-value of] [new image name]";
  }

  @Override
  public String commandText() {
    return "intensity-value";
  }
}
