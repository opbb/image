package imecontroller.iguicommand;

import java.util.Map;

import javax.swing.*;

import imemodel.ImageModel;
import imeview.IMEGUIView;

/**
 * Command which brightens a specific Image in the given model by an amount.
 * The amount to brighten, image to brighten, and name to store the brightened image under
 * are all supplied by the user as input.
 */
public class BrightenGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view, Map<String,
          IGUICommand> commands, String imageName)
          throws IllegalStateException {
    double amount = getDoubleInput(view,
            "Input amount to brighten (A number from -255 to 255):",
            0);

    model.brighten(imageName, amount);
  }

  @Override
  public String commandText() {
    return "brighten";
  }
}
