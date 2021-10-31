import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import IMEController.ICommand.BrightenCommand;
import IMEController.ICommand.GrayscaleCommand;
import IMEController.ICommand.HoriFlipCommand;
import IMEController.ICommand.ICommand;
import IMEController.ICommand.LoadCommand;
import IMEController.ICommand.SaveCommand;
import IMEController.ICommand.VertFlipCommand;
import IMEController.IMEController;
import IMEController.IMEControllerImpl;
import IMEModel.ImageModel;
import IMEModel.ImageModelImpl;
import IMEView.IMEView;
import IMEView.IMEViewImpl;

public class Main {

  public static void main(String[] args) {
    Map<String, ICommand> commands = ICommand.generateMapFromList(Arrays.asList(
            new BrightenCommand(),
            new GrayscaleCommand(),
            new HoriFlipCommand(),
            new LoadCommand(),
            new VertFlipCommand(),
            new SaveCommand()));
    ImageModel model = new ImageModelImpl();
    IMEView view = new IMEViewImpl();
    IMEController controller = new IMEControllerImpl(commands, model, view);

    controller.run();
  }
}
