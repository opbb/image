package imecontroller.icommand;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import imemodel.ExtraFilters;
import imemodel.ExtraFiltersImpl;
import imemodel.ImageModel;
import imeview.IMEView;

/**
 * This class represents the command to blur a given image.
 */
public class BlurCommand extends AbstractCommand {


  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc, Map<String, ICommand> commands)
          throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String toImage = getStringInput(sc);
    ExtraFilters filter = new ExtraFiltersImpl(model);

    if (setUpImage(model, view, fromImage, toImage)) {
      filter.blur(toImage);
    }
  }

  @Override
  public String helpMessage() {
    return "blur [image to blur] [new image name]";
  }

  @Override
  public String commandText() {
    return "blur";
  }
}
