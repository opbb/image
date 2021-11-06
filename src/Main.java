import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

import javax.imageio.ImageIO;

import imecontroller.icommand.BlueValueCommand;
import imecontroller.icommand.BlurCommand;
import imecontroller.icommand.BrightenCommand;
import imecontroller.icommand.CloseCommand;
import imecontroller.icommand.LoadPNGCommand;
import imecontroller.icommand.LumaValueCommand;
import imecontroller.icommand.GreenValueCommand;
import imecontroller.icommand.HoriFlipCommand;
import imecontroller.icommand.ICommand;
import imecontroller.icommand.IntensityValueCommand;
import imecontroller.icommand.LoadCommand;
import imecontroller.icommand.RedValueCommand;
import imecontroller.icommand.SaveBMPCommand;
import imecontroller.icommand.SaveCommand;
import imecontroller.icommand.SavePNGCommand;
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
            new SaveBMPCommand(),
            new LoadPNGCommand(),
            new SavePNGCommand(),
            new CloseCommand()));
          //  new SaveCommand()));
    ImageModel model = new ImageModelImpl();
    IMEView view = new IMEViewImpl(model);
    IMEController controller = new IMEControllerImpl(commands, model, view);

    String filename = "hep.png";
    System.out.println(filename.substring(filename.lastIndexOf(".") + 1));
      controller.run();

  }
}
