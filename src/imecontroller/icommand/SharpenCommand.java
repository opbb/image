package imecontroller.icommand;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import imemodel.ExtraFilters;
import imemodel.ExtraFiltersImpl;
import imemodel.ImageModel;
import imeview.IMEView;

/**
 * This command represents the class by which the sharpened filter is applied to an image.
 */
public class SharpenCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc, Map<String, ICommand> commands)
          throws IllegalStateException {
    try {
      String fromImage = getStringInput(sc);
      String toImage = getStringInput(sc);
      ExtraFilters filter = new ExtraFiltersImpl(model);


      if (setUpImage(model, view, fromImage, toImage)) {
        filter.sharpen(toImage);
      }
    } catch (InputMismatchException e) {
      getStringInput(sc);
      getStringInput(sc);
      getStringInput(sc);
    }
  }

  @Override
  public String helpMessage() {
    return "sharpen [image to sharpen] [new image name]";
  }

  @Override
  public String commandText() {
    return "sharpen";
  }
}


