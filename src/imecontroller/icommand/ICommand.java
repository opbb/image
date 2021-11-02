package imecontroller.icommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import imemodel.ImageModel;
import imeview.IMEView;

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
  void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException;

  /**
   * Returns a message explaining how to use this command.
   * @return a message explaining how to use this command
   */
  String helpMessage();

  /**
   * Returns the text used to call this command.
   * @return the text used to call this command.
   */
  String commandText();

  /**
   * Generates a map of ICommands and their keys from a list of ICommands.
   * @param list list of ICommands to generate from
   * @return a map of ICommands and their keys
   */
  static Map<String, ICommand> generateMapFromList(List<ICommand> list) {
    Map<String, ICommand> map = new HashMap<String, ICommand>();
    for (ICommand command : list) {
      map.put(command.commandText(), command);
    }
    return map;
  }
}
