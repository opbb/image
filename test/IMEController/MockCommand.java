package IMEController;

import java.util.Scanner;

import IMEModel.ImageModel;
import IMEView.IMEView;

public class MockCommand implements ICommand {

  StringBuilder log;

  public MockCommand(StringBuilder log) {

  }

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) {
    log.append("model: " + model.toString() + " scanner: " + sc.next());
  }
}
