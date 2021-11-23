package imecontroller.iguicommand;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import imecontroller.InputUtils;
import imecontroller.icommand.ICommand;
import imemodel.ImageModel;
import imeview.IMEGUIView;
import imeview.IMEView;

/**
 * An abstract class for commands which defines some helpers.
 */
public abstract class AbstractGUICommand implements IGUICommand {

  /**
   * Returns the inputted double, or renders an error message and/or passes on any exceptions.
   *
   * @param view the view to which error messages will be rendered
   * @param sc   the scanner from which inputs are read
   * @return the inputted double
   * @throws InputMismatchException if something other than a double is inputted
   * @throws IllegalStateException  if there are no more inputs or the scanner is closed
   */
  protected double getDoubleInput(IMEGUIView view)
          throws InputMismatchException, IllegalStateException {
    view.getInput();
  }

  /**
   * Returns the inputted String, or throws an exception if there are issues with the scanner.
   *
   * @return the inputted string
   * @throws IllegalStateException if there are no more inputs or the scanner is closed
   */
  protected String getStringInput(IMEGUIView view) throws IllegalStateException {
    return InputUtils.getStringInput();
  }

  /**
   * Ensures that the given image to edit exists, and returns false if it doesn't.
   * If the given image exists, then it makes a renamed duplicate of it to execute the command on.
   *
   * @param model     the model in which the images are stored
   * @param view      the view to which error messages are rendered
   * @param fromImage the given image name of the image to edit
   * @param toImage   the given image name for the resulting image
   * @return true if the given image to edit exists, false otherwise
   */
  protected boolean setUpImage(ImageModel model, IMEGUIView view, String fromImage, String toImage) {
    if (model.hasImage(fromImage)) {
      if (!(fromImage.equals(toImage))) {
        model.duplicateImage(fromImage, toImage);

      }
      return true;
    } else {
      view.renderMessage("The given image name " + fromImage + " does not exist.\n\n");
      return false;
    }
  }
}
