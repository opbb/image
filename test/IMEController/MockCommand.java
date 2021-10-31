package IMEController;

import java.util.Scanner;

import IMEModel.ImageModel;

public class MockCommand implements ICommand {

  StringBuilder log;

  public MockCommand(StringBuilder log) {

  }

  @Override
  public void excecute(ImageModel model, Scanner sc) {
    log.append("model: " + model.toString() + " scanner: " + sc.next());
  }
}
