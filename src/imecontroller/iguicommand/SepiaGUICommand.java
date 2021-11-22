package imecontroller.iguicommand;


import java.util.Map;

import imecontroller.icommand.ICommand;
import imemodel.ExtraFilters;
import imemodel.ExtraFiltersImpl;
import imemodel.ImageModel;

/**
 * This class represents the command to give an image the Sepia tone.
 */
public class SepiaGUICommand extends AbstractGUICommand {
  @Override
  public void execute(ImageModel model, IMEGUIView view, Map<String, ICommand> commands)
          throws IllegalStateException {

    String fromImage = getStringInput(sc);
    String toImage = getStringInput(sc);
    ExtraFilters filter = new ExtraFiltersImpl(model);


    if (setUpImage(model, view, fromImage, toImage)) {
      filter.toSepia(toImage);
    }
  }

  @Override
  public String helpMessage() {
    return "sepia [image to add sepia filter] [new image name]";
  }

  @Override
  public String commandText() {
    return "sepia";
  }
}


