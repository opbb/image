import java.util.Arrays;
import java.util.Map;


import javax.swing.*;

import imecontroller.IMEFileController;
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

import imecontroller.iguicommand.BlueValueGUICommand;
import imecontroller.iguicommand.BlurGUICommand;
import imecontroller.iguicommand.BrightenGUICommand;
import imecontroller.iguicommand.GreenValueGUICommand;
import imecontroller.iguicommand.HoriFlipGUICommand;
import imecontroller.iguicommand.IGUICommand;
import imecontroller.iguicommand.IntensityValueGUICommand;
import imecontroller.iguicommand.LumaValueGUICommand;
import imecontroller.iguicommand.RedValueGUICommand;
import imecontroller.iguicommand.SepiaGUICommand;
import imecontroller.iguicommand.SharpenGUICommand;
import imecontroller.iguicommand.ValueGUICommand;
import imecontroller.iguicommand.VertFlipGUICommand;

import imemodel.ImageModel;
import imemodel.ImageModelImpl;
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
    ImageModel model = new ImageModelImpl();

    //added the feature of reading a .txt file filled with commands and disregards comments.


    IMEController controller = null;
    if (args.length == 0) {
      Map<String, IGUICommand> commands = IGUICommand.generateMapFromList(Arrays.asList(
              new BrightenGUICommand(),
              new LumaValueGUICommand(),
              new HoriFlipGUICommand(),
              new VertFlipGUICommand(),
              new RedValueGUICommand(),
              new GreenValueGUICommand(),
              new BlueValueGUICommand(),
              new ValueGUICommand(),
              new BlurGUICommand(),
              new IntensityValueGUICommand(),
              new SharpenGUICommand(),
              new SepiaGUICommand()));
      IMEGUIViewImpl view = new IMEGUIViewImpl(model, commands);
      view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      view.setVisible(true);
      IMEGUIViewImpl.setDefaultLookAndFeelDecorated(false);
      view.setResizable(true);
      try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (UnsupportedLookAndFeelException e) {
        e.printStackTrace();
      }
      controller = new IMEGUIControllerImpl(commands, model, view);
    } else {
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
              new InputFromFileCommand()));
      IMEView view = new IMEViewImpl(model);
      //new IMEControllerImpl(commands, model, view).run();

      //added the feature of reading a .txt file filled with commands and disregards comments.


        //controller = new IMEGUIController(commands, model, view);
//    } else {
//      commands.add(new SaveCommand()),
//      commands.add(new LoadCommand()),
//
//            new InputFromFileCommand(),
//            new SaveCommand(),
//            new LoadCommand()));
//       view = new IMEViewImpl(model);

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



  }
  }

