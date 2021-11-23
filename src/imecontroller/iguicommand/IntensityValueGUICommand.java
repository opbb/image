package imecontroller.iguicommand;

import java.util.Map;

import imecontroller.icommand.ICommand;
import imemodel.ImageModel;
import imeview.IMEGUIView;

/**
 * This command is triggered via user input and will greyscale the associated image by its pixels'
 * intensity component.
 */
public class IntensityValueGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view, Map<String,
          IGUICommand> commands, String imageName) {
      model.getByComponent(imageName, "intensity");
  }

  @Override
  public String commandText() {
    return "intensity-value";
  }
}
