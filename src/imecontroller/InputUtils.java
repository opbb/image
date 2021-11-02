package imecontroller;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import imeview.IMEView;

/**
 * Contains some utility methods for getting input.
 */
public interface InputUtils {

  /**
   * Returns the inputted double, or renders an error message and/or passes on any exceptions.
   * @param view the view to which error messages will be rendered
   * @param sc the scanner from which inputs are read
   * @return the inputted double
   * @throws InputMismatchException if something other than a double is inputted
   * @throws IllegalStateException if there are no more inputs or the scanner is closed
   */
  static double getDoubleInput(IMEView view, Scanner sc)
          throws InputMismatchException, IllegalStateException {
    try {
      return sc.nextDouble();
    } catch (InputMismatchException e) {
      view.renderMessage("Invalid input, expected a double.\n");
      throw e; // Propagates the exception up so that the invalid input can be addressed.
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("Ran out of inputs.");
    }
  }

  /**
   * Returns the inputted String, or throws an exception if there are issues with the scanner.
   * @param sc the scanner from which inputs are read
   * @return the inputted string
   * @throws IllegalStateException if there are no more inputs or the scanner is closed
   */
  static String getStringInput(Scanner sc) throws IllegalStateException{
    try {
      return sc.next();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("Ran out of inputs.");
    }
  }
}
