package imecontroller.iguicommand;

import imemodel.ImageModel;
import imeview.IMEGUIView;

/**
 * Command which gets the green component of a specific Image in the given model.
 * The command to get the green component, image to change, and name to store the green component
 * image under are all supplied by the user as input.
 */
public class GreenValueGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view, String imageName) {
    model.getByComponent(imageName, "green");
  }

  @Override
  public String commandText() {
    return "green-value";
  }
}


