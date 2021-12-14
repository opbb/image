package imecontroller.icommand;

import java.util.Map;
import java.util.Scanner;

import imemodel.ImageModel;
import imeview.IMEView;

/**
 * This command helps in resizing (downsizing) an image down in the script based program.
 */
public class ResizeCommand extends AbstractCommand {


  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc, Map<String, ICommand> commands)
          throws IllegalStateException {
    int height = (int) getDoubleInput(view, sc);
    int width = (int) getDoubleInput(view, sc);


    String fromImage = getStringInput(sc);
    String toImage = getStringInput(sc);
    if (height > model.getImageValues(fromImage).length ||
            width > model.getImageValues(fromImage)[0].length) {
      view.renderMessage("Sorry the inputted values are too large.");
    }

    if (setUpImage(model, view, fromImage, toImage)) {
      model.resize(height, width, toImage);
    }

  }

  @Override
  public String helpMessage() {
    return "resize [number-to-adjust-height] [number-to-adjust-width] [image to resize of] " +
            "[new image name]";
  }

  @Override
  public String commandText() {
    return "resize";
  }
}
