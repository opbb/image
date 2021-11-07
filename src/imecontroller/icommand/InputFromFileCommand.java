package imecontroller.icommand;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

import imecontroller.IMEFileController;
import imemodel.ImageModel;
import imeview.IMEView;

public class InputFromFileCommand extends AbstractCommand {
  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc, Map<String, ICommand> commands)
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
