package imecontroller.icommand;

import java.util.Scanner;

import imemodel.ImageModel;
import imeview.IMEView;


/**
 * This command vertically flips the image when advised by the user to do so, via user inputs
 * and will call for the model to use its flipVertical method.
 */
public class VertFlipCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String toImage = sc.next();

    if (setUpImage(model, view, fromImage, toImage)) {
      model.flipVertical(toImage);
    }
  }

  @Override
  public String helpMessage() {
    return "vertical-flip [image to flip] [new image name]";
  }

  @Override
  public String commandText() {
    return "vertical-flip";
  }
}
