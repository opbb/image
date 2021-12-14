package imecontroller.icommand;


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

    ExtraFilters filter = new ExtraFiltersImpl(model);

    String maskedOrToImage = getStringInput(sc);
    if (sc.hasNext()) {
      String toImage = getStringInput(sc);
      editMaskedImage(model, view, fromImage, toImage, maskedOrToImage,
              (ImageModel m) -> {new ExtraFiltersImpl(m).blur("masked-copy");});


    }

    else {
      if (setUpImage(model, view, fromImage, maskedOrToImage)) {
        filter.blur(maskedOrToImage);
      }
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
