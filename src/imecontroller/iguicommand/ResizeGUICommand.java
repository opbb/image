package imecontroller.iguicommand;

import imemodel.ImageModel;
import imeview.IMEGUIView;

/**
 * This GUICommand allows the user to resize an image and prompts them the exact height and width
 * they would like to downsize an image by.
 */
public class ResizeGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view, String imageName) throws IllegalStateException {
    double height = getDoubleInput(view,
            "Input the height of which to downsize an image to",
            0);
    double width = getDoubleInput(view,
            "Input the width of which to downsize an image to",
            0);
    if (height > model.getImageValues(imageName).length ||
            width > model.getImageValues(imageName)[0].length) {
      view.renderMessage("Sorry the inputted values are too high.");
    }
    model.resize((int) height, (int) width, imageName);
  }

  @Override
  public String commandText() {
    return "resize";
  }
}
