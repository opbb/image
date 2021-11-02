package imecontroller.icommand;

import java.io.IOException;
import java.util.Scanner;
import imemodel.Image;
import imemodel.ImageImpl;
import imemodel.ImageModel;
import imemodel.ImageUtil;
import imeview.IMEView;

/**
 * This command will load an image to the application by calling the readPPM method with the
 * associated file and loads it into the model.
 */
public class LoadCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
    String fileName = getStringInput(sc);
    String toImage = getStringInput(sc);

    try {
      Image img = new ImageImpl(ImageUtil.readPPM(fileName));
      model.loadImage(toImage, img);
    } catch (IOException e) {
      view.renderMessage("The given file name " + fileName + " does not exist.\n\n");
    }
  }

  @Override
  public String helpMessage() {
    return "load [file name] [new image name]";
  }

  @Override
  public String commandText() {
    return "load";
  }
}
