package imecontroller.icommand;

import java.util.InputMismatchException;
import java.util.Scanner;

import imemodel.ExtraFilters;
import imemodel.ExtraFiltersImpl;
import imemodel.ImageModel;
import imeview.IMEView;

public class BlurCommand extends AbstractCommand {


  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
    try {
      String fromImage = getStringInput(sc);
      String toImage = getStringInput(sc);
      ExtraFilters filter = new ExtraFiltersImpl(model);


      if (setUpImage(model, view, fromImage, toImage)) {
        filter.blur(toImage);
      }
    } catch (InputMismatchException e) {
      getStringInput(sc);
      getStringInput(sc);
      getStringInput(sc);
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
