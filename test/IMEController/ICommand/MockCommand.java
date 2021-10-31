package IMEController.ICommand;

import java.util.Scanner;

import IMEController.ICommand.ICommand;
import IMEModel.ImageModel;
import IMEView.IMEView;

public class MockCommand implements ICommand {

  StringBuilder log;

  public MockCommand(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) {
    log.append("model: " + model.toString() + " scanner: " + sc.next());
  }

  @Override
  public String helpMessage() {
    return null;
  }

  @Override
  public String commandText() {
    return null;
  }
}
