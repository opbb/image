package IMEController.ICommand;

import java.io.IOException;
import java.util.Scanner;

import IMEModel.Image;
import IMEModel.ImageImpl;
import IMEModel.ImageModel;
import IMEModel.ImageUtil;
import IMEView.IMEView;

public class SaveCommand extends AbstractCommand {
  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String fileName = getStringInput(sc);

    try {
      ImageUtil.writePPM(model.getImage(fromImage), fileName);
    } catch (IOException e) {
      view.renderMessage("The image cannot be saved to " + fileName + ".\n\n");
    }
  }

  @Override
  public String helpMessage() {
    return "save [image to save] [file name to save as]";
  }

  @Override
  public String commandText() {
    return "save";
  }
}
