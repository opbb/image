package imecontroller.icommand;

import java.util.Scanner;

import imemodel.ImageModel;
import imeview.IMEView;

/**
 * Mock command class used for ICommands and Controller testing.
 */
public class MockCommand implements ICommand {

  StringBuilder log;

  /**
   * Command mock constructor.
   *
   * @param log Stringbuilder object acting as the required Appendable.
   */
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
