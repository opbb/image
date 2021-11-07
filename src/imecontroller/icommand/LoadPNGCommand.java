package imecontroller.icommand;

import java.util.Map;
import java.util.Scanner;

import imemodel.Image;
import imemodel.ImageImpl;
import imemodel.ImageModel;
import imeview.IMEView;

public class LoadPNGCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc, Map<String, ICommand> commands)
          throws IllegalStateException {
    String fileName = getStringInput(sc);
    String toImage = getStringInput(sc);

      Image img = new ImageImpl(fileName, "png");
      model.loadImage(toImage, img);
  }

  @Override
  public String helpMessage() {
    return "load-png [file name] [new image name]";
  }

  @Override
  public String commandText() {
    return "load-png";
  }
}
