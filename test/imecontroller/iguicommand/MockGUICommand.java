package imecontroller.iguicommand;

import imemodel.ImageModel;
import imeview.IMEGUIView;

/**
 * Mock command class used for IGUICommands and Controller testing.
 */
public class MockGUICommand implements IGUICommand {

  StringBuilder log;

  /**
   * Command mock constructor.
   *
   * @param log Stringbuilder object acting as the required Appendable.
   */
  public MockGUICommand(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void execute(ImageModel model, IMEGUIView view, String imageName) {
    log.append("execute) model: " + model.toString() +
            "imageName: " + imageName + "\n");
  }

  @Override
  public String commandText() {
    log.append("commandText)\n");
    return "mock";
  }
}
