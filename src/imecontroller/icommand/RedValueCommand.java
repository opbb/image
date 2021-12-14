package imecontroller.icommand;

import java.util.Map;
import java.util.Scanner;

import imemodel.ImageModel;
import imeview.IMEView;


/**
 * Command which gets the red component of a specific Image in the given model.
 * The command to get the red component, image to change, and name to store the red component
 * image under are all supplied by the user as input.
 */
public class RedValueCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc, Map<String, ICommand> commands)
          throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String maskedOrToImage = getStringInput(sc);
    if (sc.hasNext()) {
      String toImage = getStringInput(sc);
      editMaskedImage(model, view, fromImage, toImage, maskedOrToImage,
              (ImageModel m) -> {m.getByComponent("masked-copy", "red");});


    }

    //      model.duplicateImage(fromImage, toImage);
//      model.maskedImagify(toImage, maskedOrToImage);
//      model.getByComponent("masked-copy", "red");
//      model.applyMaskedChanges(toImage, maskedOrToImage);

    else {
      if (setUpImage(model, view, fromImage, maskedOrToImage)) {
        model.getByComponent(maskedOrToImage, "red");
      }
    }
  }

  @Override
  public String helpMessage() {
    return "red-value [image to get red-value of] [new image name]";
  }

  @Override
  public String commandText() {
    return "red-value";
  }
}

