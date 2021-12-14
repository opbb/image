package imecontroller.icommand;

import java.util.InputMismatchException;
import java.util.Scanner;

import imecontroller.InputUtils;
import imemodel.ImageModel;
import imeview.IMEView;

/**
 * An abstract class for commands which defines some helpers.
 */
public abstract class AbstractCommand implements ICommand {

  /**
   * Returns the inputted double, or renders an error message and/or passes on any exceptions.
   *
   * @param view the view to which error messages will be rendered
   * @param sc   the scanner from which inputs are read
   * @return the inputted double
   * @throws InputMismatchException if something other than a double is inputted
   * @throws IllegalStateException  if there are no more inputs or the scanner is closed
   */
  protected double getDoubleInput(IMEView view, Scanner sc)
          throws InputMismatchException, IllegalStateException {
    try {
      return InputUtils.getDoubleInput(view, sc);
    } catch (InputMismatchException e) {
      view.renderMessage("The command should be structured as shown below:\n"
              + this.helpMessage() + "\n\n");
      throw e; // Propagates the exception up so that the invalid input can be addressed.
    }
  }

  /**
   * Returns the inputted String, or throws an exception if there are issues with the scanner.
   *
   * @param sc the scanner from which inputs are read
   * @return the inputted string
   * @throws IllegalStateException if there are no more inputs or the scanner is closed
   */
  protected String getStringInput(Scanner sc) throws IllegalStateException {
    return InputUtils.getStringInput(sc);
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
  protected boolean setUpImage(ImageModel model, IMEView view, String fromImage, String toImage) {
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


  /**
   * Sets up a masked image taking in the original image, the new edited image, and the masked
   * image, as well as the method call to what filter to be applied.
   *
   * @param model       the Image model we get an effect from.
   * @param view        the view to show that the program has duplicated/loaded an image.
   * @param toImage     the name of the new image.
   * @param maskedImage the name of the masked image.
   * @param meth        the name of the method/filter/effect to be applied.
   */
  protected void editMaskedImage(ImageModel model, IMEView view, String fromImage,
                                 String toImage, String maskedImage, MethodCaller meth) {

    if (setUpImage(model, view, fromImage, toImage)) {


      model.maskedImagify(toImage, maskedImage);
      meth.execute(model);
      model.applyMaskedChanges(toImage, maskedImage);

    }


  }
}


