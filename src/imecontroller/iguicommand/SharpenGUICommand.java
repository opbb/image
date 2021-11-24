package imecontroller.iguicommand;


import imemodel.ExtraFilters;
import imemodel.ExtraFiltersImpl;
import imemodel.ImageModel;
import imeview.IMEGUIView;

/**
 * This command represents the class by which the sharpened filter is applied to an image.
 */
public class SharpenGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view,
                      String imageName) {
    ExtraFilters filter = new ExtraFiltersImpl(model);
      filter.sharpen(imageName);
  }

  @Override
  public String commandText() {
    return "sharpen";
  }
}


