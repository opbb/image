package imecontroller.iguicommand;

import java.util.Map;

import imecontroller.icommand.ICommand;
import imemodel.ImageModel;
import imeview.IMEGUIView;

/**
 * This command is triggered via user input and will greyscale the associated image by its pixels'
 * value component.
 */
public class ValueGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view, Map<String,
          IGUICommand> commands, String imageName) {
      model.getByComponent(imageName, "value");
  }

  @Override
  public String commandText() {
    return "value";
  }
}
