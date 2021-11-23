package imecontroller.iguicommand;

import java.util.Map;

import imecontroller.icommand.ICommand;
import imemodel.ImageModel;
import imeview.IMEGUIView;


/**
 * This command horizontally flips the image when advised by the user to do so, via user inputs
 * and will call for the model to use its flipHorizontal method.
 */
public class HoriFlipGUICommand extends AbstractGUICommand {
  @Override
  public void execute(ImageModel model, IMEGUIView view,
                      Map<String, IGUICommand> commands, String imageName) {
      model.flipHorizontal(imageName);
  }

  @Override
  public String commandText() {
    return "horizontal-flip";
  }
}
