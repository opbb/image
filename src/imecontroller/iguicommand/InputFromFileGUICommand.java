package imecontroller.iguicommand;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Map;

import imecontroller.IMEFileController;
import imecontroller.icommand.ICommand;
import imemodel.ImageModel;

/**
 * This command supports the feature of loading and executing commands from a .txt file.
 */
public class InputFromFileGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view, Map<String, ICommand> commands)
          throws IllegalStateException {
    String fromFile = getStringInput(sc);

    try {
      Readable input = new InputStreamReader(new FileInputStream(fromFile));
      new IMEFileController(commands, model, view, input).run();
    } catch (FileNotFoundException e) {
      view.renderMessage("Couldn't find a file at " + fromFile + ".\n\n");
    }
  }

  @Override
  public String helpMessage() {
    return "input-from-file [input file path]";
  }

  @Override
  public String commandText() {
    return "input-from-file";
  }
}
