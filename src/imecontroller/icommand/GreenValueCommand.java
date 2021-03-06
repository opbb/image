package imecontroller.icommand;

import java.util.Map;
import java.util.Scanner;

import imemodel.ImageModel;
import imeview.IMEView;

/**
 * Command which gets the green component of a specific Image in the given model.
 * The command to get the green component, image to change, and name to store the green component
 * image under are all supplied by the user as input.
 */
public class GreenValueCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc, Map<String, ICommand> commands)
          throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String maskedOrToImage = getStringInput(sc);
    if (sc.hasNext()) {
      String toImage = getStringInput(sc);
      editMaskedImage(model, view, fromImage, toImage, maskedOrToImage,
              (ImageModel m) -> {
                m.getByComponent("masked-copy", "green");
              });


    } else {
      if (setUpImage(model, view, fromImage, maskedOrToImage)) {
        model.getByComponent(maskedOrToImage, "green");
      }
    }
  }

  @Override
  public String helpMessage() {
    return "green-value [image to get green-value of] [new image name]";
  }

  @Override
  public String commandText() {
    return "green-value";
  }
}


