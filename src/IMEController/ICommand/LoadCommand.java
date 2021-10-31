package IMEController.ICommand;

import java.io.IOException;
import java.util.Scanner;
import IMEModel.Image;
import IMEModel.ImageImpl;
import IMEModel.ImageModel;
import IMEModel.ImageUtil;
import IMEView.IMEView;

public class LoadCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
    String fileName = getStringInput(sc);
    String toImage = getStringInput(sc);

    try {
      Image img = new ImageImpl(ImageUtil.readPPM(fileName));
      model.loadImage(toImage, img);
    } catch (IOException e) {
      view.renderMessage("The given file name " + fileName + " does not exist.\n\n");
    }
  }

  @Override
  public String helpMessage() {
    return "load [file name] [new image name]";
  }

  @Override
  public String commandText() {
    return "load";
  }
}
