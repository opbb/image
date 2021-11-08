import java.util.Arrays;
import java.util.Map;


import imecontroller.IMEFileController;
import imecontroller.icommand.BlueValueCommand;
import imecontroller.icommand.BlurCommand;
import imecontroller.icommand.BrightenCommand;
import imecontroller.icommand.CloseCommand;
import imecontroller.icommand.InputFromFileCommand;
import imecontroller.icommand.LumaValueCommand;
import imecontroller.icommand.GreenValueCommand;
import imecontroller.icommand.HoriFlipCommand;
import imecontroller.icommand.ICommand;
import imecontroller.icommand.IntensityValueCommand;
import imecontroller.icommand.LoadCommand;
import imecontroller.icommand.RedValueCommand;
import imecontroller.icommand.SaveCommand;

import imecontroller.icommand.SepiaCommand;
import imecontroller.icommand.SharpenCommand;
import imecontroller.icommand.ValueCommand;
import imecontroller.icommand.VertFlipCommand;
import imecontroller.IMEController;
import imecontroller.IMEControllerImpl;
import imemodel.ImageModel;
import imemodel.ImageModelImpl;
import imeview.IMEView;
import imeview.IMEViewImpl;

/**
 * Main class to which the commands, model, view, and controller are instantiated and the
 * application begins.
 */
public class Main {

  /**
   * Main class as described in the class javadoc, initializes a map of commands, the model,
   * view, and controller.
   *
   * @param args That of which commands are read in.
   */
  public static void main(String[] args) {
    Map<String, ICommand> commands = ICommand.generateMapFromList(Arrays.asList(
            new BrightenCommand(),
            new LumaValueCommand(),
            new HoriFlipCommand(),
            new LoadCommand(),
            new VertFlipCommand(),
            new RedValueCommand(),
            new GreenValueCommand(),
            new BlueValueCommand(),
            new ValueCommand(),
            new BlurCommand(),
            new IntensityValueCommand(),
            new CloseCommand(),
            new SharpenCommand(),
            new SepiaCommand(),
            new SaveCommand(),
            new LoadCommand(),
            new InputFromFileCommand()));
    ImageModel model = new ImageModelImpl();
    IMEView view = new IMEViewImpl(model);

    //added the feature of reading a .txt file filled with commands and disregards comments.

    IMEController controller;
    if (args.length == 0) {
      controller = new IMEControllerImpl(commands, model, view);
    } else {
      controller = new IMEFileController(commands, model, view, args[0]);
    }

    controller.run();

  }
}
