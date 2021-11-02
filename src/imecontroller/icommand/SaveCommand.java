package imecontroller.icommand;

import java.io.IOException;
import java.util.Scanner;

import imemodel.Image;
import imemodel.ImageModel;
import imemodel.ImageUtil;
import imeview.IMEView;

/**
 * This command will load an image to the application by calling the writePPM method with the
 * associated file and saves/writes it to the desired destination.
 */
public class SaveCommand extends AbstractCommand {
  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String fileName = getStringInput(sc);
    Image image = model.getImage(fromImage);

    try {
      if (image != null) {
        ImageUtil.writePPM(image, fileName);
      } else {
        view.renderMessage("The image " + fromImage + " is null.\n\n");
      }
    } catch (IOException e) {
      view.renderMessage("The image cannot be saved to " + fileName + ".\n\n");
    }
  }

  @Override
  public String helpMessage() {
    return "save [image to save] [file name to save as]";
  }

  @Override
  public String commandText() {
    return "save";
  }
}
