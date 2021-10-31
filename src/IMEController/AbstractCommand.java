package IMEController;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import IMEView.IMEView;

public abstract class AbstractCommand implements ICommand {

  protected double getDoubleInput(IMEView view, Scanner sc)
          throws InputMismatchException, IllegalStateException {
    try {
      return sc.nextDouble();
    } catch (InputMismatchException e) {
      throw e; // Acknowledges the Exception and propagates it up.
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("Ran out of inputs.");
    }
  }
}
