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
    log.append("model: " + model.toString() +
            " view: " + view.toString() +
            " scanner: " + sc.next() + "\n");
  }

  @Override
  public String helpMessage() {
    log.append("helpMessage)\n");
    return "mock";
  }

  @Override
  public String commandText() {
    log.append("commandText)\n");
    return "mock";
  }
}
