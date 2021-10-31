package IMEController;

import java.util.Scanner;
import IMEModel.ImageModel;
import IMEView.IMEView;

/**
 * An interface for command objects, which take their own user input and execute themselves on
 * a given ImageModel.
 */
public interface ICommand {

  /**
   * Executes the command.
   * @param model the ImageModel on which to execute the command
   * @param sc the scanner with which to get input
   */
  public void excecute(ImageModel model, IMEView view, Scanner sc);

  /**
   * Returns a message explaining how to use this command.
   * @return a message explaining how to use this command
   */
  public String helpMessage();

}
