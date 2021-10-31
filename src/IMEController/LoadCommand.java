package IMEController;

import java.util.Scanner;
import IMEModel.ImageModel;
import IMEView.IMEView;

public class LoadCommand extends AbstractCommand {
  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) {
    String path = getStringInput(sc);
    String fileName = getStringInput(sc);

    
  }

  @Override
  public String helpMessage() {
    return null;
  }
}
