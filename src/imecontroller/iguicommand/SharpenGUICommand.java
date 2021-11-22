package imecontroller.iguicommand;


import java.util.Map;

import imecontroller.icommand.ICommand;
import imemodel.ExtraFilters;
import imemodel.ExtraFiltersImpl;
import imemodel.ImageModel;

/**
 * This command represents the class by which the sharpened filter is applied to an image.
 */
public class SharpenGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view, Map<String, ICommand> commands)
          throws IllegalStateException {

    String fromImage = getStringInput(sc);
    String toImage = getStringInput(sc);
    ExtraFilters filter = new ExtraFiltersImpl(model);

    if (setUpImage(model, view, fromImage, toImage)) {
      filter.sharpen(toImage);
    }
  }

  @Override
  public String helpMessage() {
    return "sharpen [image to sharpen] [new image name]";
  }

  @Override
  public String commandText() {
    return "sharpen";
  }
}


