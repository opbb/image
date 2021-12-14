package imecontroller.icommand;


import java.util.Map;
import java.util.Scanner;

import imemodel.ExtraFilters;
import imemodel.ExtraFiltersImpl;
import imemodel.ImageModel;
import imeview.IMEView;

/**
 * This class represents the command to give an image the Sepia tone.
 */
public class SepiaCommand extends AbstractCommand {
  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc, Map<String, ICommand> commands)
          throws IllegalStateException {

    String fromImage = getStringInput(sc);


    ExtraFilters filter = new ExtraFiltersImpl(model);

    String maskedOrToImage = getStringInput(sc);
    if (sc.hasNext()) {
      String toImage = getStringInput(sc);
      editMaskedImage(model, view, fromImage, toImage, maskedOrToImage,
              (ImageModel m) -> {new ExtraFiltersImpl(m).toSepia("masked-copy");});


    }

    else {
      if (setUpImage(model, view, fromImage, maskedOrToImage)) {
        filter.toSepia(maskedOrToImage);
      }
    }
  }

  @Override
  public String helpMessage() {
    return "sepia [image to add sepia filter] [new image name]";
  }

  @Override
  public String commandText() {
    return "sepia";
  }
}


