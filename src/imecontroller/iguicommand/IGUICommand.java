package imecontroller.iguicommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import imecontroller.icommand.ICommand;
import imemodel.ImageModel;
import imeview.IMEGUIView;

public interface IGUICommand {

  void execute(ImageModel model, IMEGUIView view, Map<String, IGUICommand> commands)
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
