package imecontroller.icommand;


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

    String fromImage = getStringInput(sc);
    ExtraFilters filter = new ExtraFiltersImpl(model);

    String maskedOrToImage = getStringInput(sc);
    if (sc.hasNext()) {
      String toImage = getStringInput(sc);
      editMaskedImage(model, view, fromImage, toImage, maskedOrToImage,
              (ImageModel m) -> {new ExtraFiltersImpl(m).sharpen("masked-copy");});


    }

    else {
      if (setUpImage(model, view, fromImage, maskedOrToImage)) {
        filter.sharpen(maskedOrToImage);
      }
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


