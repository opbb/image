package imecontroller.iguicommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import imemodel.ImageModel;
import imeview.IMEGUIView;

/**
 * An interface for command objects designed to be used with a GUI,
 * which take their own user input and execute themselves on a given ImageModel.
 */
public interface IGUICommand {

  /**
   * Executes the command.
   *
   * @param model the ImageModel on which to execute the command
   * @param view the view to get input from
   * @param imageName the image to execute the command on
   * @throws IllegalStateException
   */
  void execute(ImageModel model, IMEGUIView view, String imageName)
          throws IllegalStateException;

  /**
   * Returns the text used to call this command.
   * @return the text used to call this command.
   */
  String commandText();

  /**
   * Generates a map of IGUICommands and their keys from a list of IGUICommands.
   * @param list list of IGUICommands to generate from
   * @return a map of IGUICommands and their keys
   */
  static Map<String, IGUICommand> generateMapFromList(List<IGUICommand> list) {
    Map<String, IGUICommand> map = new HashMap<String, IGUICommand>();
    for (IGUICommand command : list) {
      map.put(command.commandText(), command);
    }
    return map;
  }
}
