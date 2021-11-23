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
   * @return the inputted double
   * @throws InputMismatchException if something other than a double is inputted
   * @throws IllegalStateException  if there are no more inputs or the scanner is closed
   */
  protected double getDoubleInput(IMEGUIView view, String message, double def)
          throws InputMismatchException, IllegalStateException {
    return view.getDoubleInput(message, def);
  }
}
