import java.util.Arrays;
import java.util.Map;

import imecontroller.icommand.BlueValueCommand;
import imecontroller.icommand.BrightenCommand;
import imecontroller.icommand.CloseCommand;
import imecontroller.icommand.LumaValueCommand;
import imecontroller.icommand.GreenValueCommand;
import imecontroller.icommand.HoriFlipCommand;
import imecontroller.icommand.ICommand;
import imecontroller.icommand.IntensityValueCommand;
import imecontroller.icommand.LoadCommand;
import imecontroller.icommand.RedValueCommand;
import imecontroller.icommand.SaveCommand;
import imecontroller.icommand.ValueCommand;
import imecontroller.icommand.VertFlipCommand;
import imecontroller.IMEController;
import imecontroller.IMEControllerImpl;
import imemodel.ImageModel;
import imemodel.ImageModelImpl;
import imeview.IMEView;
import imeview.IMEViewImpl;

public class Main {

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
            new IntensityValueCommand(),
            new CloseCommand(),
            new SaveCommand()));
    ImageModel model = new ImageModelImpl();
    IMEView view = new IMEViewImpl(model);
    IMEController controller = new IMEControllerImpl(commands, model, view);

    controller.run();
  }
}
