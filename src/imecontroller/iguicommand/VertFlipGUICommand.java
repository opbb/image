package imecontroller.iguicommand;

import imemodel.ImageModel;
import imeview.IMEGUIView;


/**
 * This command vertically flips the image when advised by the user to do so, via user inputs
 * and will call for the model to use its flipVertical method.
 */
public class VertFlipGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view,
                      String imageName) {
      model.flipVertical(imageName);
  }

  @Override
  public String commandText() {
    return "vertical-flip";
  }
}
