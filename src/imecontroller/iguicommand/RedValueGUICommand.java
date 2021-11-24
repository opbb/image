package imecontroller.iguicommand;

import imemodel.ImageModel;
import imeview.IMEGUIView;


/**
 * Command which gets the red component of a specific Image in the given model.
 * The command to get the red component, image to change, and name to store the red component
 * image under are all supplied by the user as input.
 */
public class RedValueGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view, String imageName) {
      model.getByComponent(imageName, "red");
  }

  @Override
  public String commandText() {
    return "red-value";
  }
}

