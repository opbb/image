package imecontroller.iguicommand;


import java.util.Map;

import imemodel.ExtraFilters;
import imemodel.ExtraFiltersImpl;
import imemodel.ImageModel;
import imeview.IMEGUIView;

/**
 * This class represents the command to give an image the Sepia tone.
 */
public class SepiaGUICommand extends AbstractGUICommand {
  @Override
  public void execute(ImageModel model, IMEGUIView view, Map<String,
          IGUICommand> commands, String imageName) {
    ExtraFilters filter = new ExtraFiltersImpl(model);
      filter.toSepia(imageName);
  }

  @Override
  public String commandText() {
    return "sepia";
  }
}


