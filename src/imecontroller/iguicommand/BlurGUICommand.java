package imecontroller.iguicommand;


import imemodel.ExtraFilters;
import imemodel.ExtraFiltersImpl;
import imemodel.ImageModel;
import imeview.IMEGUIView;

/**
 * This class represents the command to blur a given image.
 */
public class BlurGUICommand extends AbstractGUICommand {


  @Override
  public void execute(ImageModel model, IMEGUIView view, String imageName) {
    ExtraFilters filter = new ExtraFiltersImpl(model);
    filter.blur(imageName);
  }

  @Override
  public String commandText() {
    return "blur";
  }
}
