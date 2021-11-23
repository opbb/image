import java.util.Arrays;
import java.util.Map;


import javax.swing.*;

import imecontroller.IMEFileController;
import imecontroller.IMEGUIController;
import imecontroller.IMEGUIControllerImpl;
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
import imemodel.HistogramImpl;
import imemodel.ImageImpl;
import imemodel.ImageModel;
import imemodel.ImageModelImpl;
import imeview.IMEGUIView;
import imeview.IMEGUIViewImpl;
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
            new InputFromFileCommand()));
    ImageModel model = new ImageModelImpl();
    IMEView view = new IMEViewImpl(model);
    //new IMEControllerImpl(commands, model, view).run();

    //added the feature of reading a .txt file filled with commands and disregards comments.

    /*
    IMEController controller = null;
    if (args.length == 0) {
      //controller = new IMEGUIController(commands, model, view);
    } else {
      commands.add(new SaveCommand())
      commands.add(new LoadCommand())
      if (args[0].equals("-text")) {
        controller = new IMEControllerImpl(commands, model, view);
      } else if (args[0].equals("-file") && args.length >= 2) {
        controller = new IMEFileController(commands, model, view, args[1]);
      }
    }

    if (controller == null) {
      throw new IllegalArgumentException("Must give no args, or give \"-text\" as an arg, " +
              "or give \"file\" and a file path as command line args.");
    }

    controller.run();
    */

    IMEGUIViewImpl.setDefaultLookAndFeelDecorated(false);
    IMEGUIViewImpl frame = new IMEGUIViewImpl(model, commands);



    model.loadImage("ella", new ImageImpl("res/ella.png"));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
