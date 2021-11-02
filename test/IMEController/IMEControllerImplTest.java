package IMEController;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import IMEController.ICommand.BlueValueCommand;
import IMEController.ICommand.BrightenCommand;
import IMEController.ICommand.CloseCommand;
import IMEController.ICommand.GreenValueCommand;
import IMEController.ICommand.HoriFlipCommand;
import IMEController.ICommand.ICommand;
import IMEController.ICommand.IntensityValueCommand;
import IMEController.ICommand.LoadCommand;
import IMEController.ICommand.LumaValueCommand;
import IMEController.ICommand.MockCommand;
import IMEController.ICommand.RedValueCommand;
import IMEController.ICommand.SaveCommand;
import IMEController.ICommand.ValueCommand;
import IMEController.ICommand.VertFlipCommand;
import IMEController.Interaction.InputInteraction;
import IMEController.Interaction.Interaction;
import IMEController.Interaction.PrintInteraction;
import IMEModel.ImageModel;
import IMEModel.ImageModelImpl;
import IMEModel.ImageModelMockTrue;
import IMEView.IMEView;
import IMEView.IMEViewImpl;
import IMEView.IMEViewMock;

public class IMEControllerImplTest {

  StringBuilder log;
  ImageModel mockModelTrue;
  IMEView mockView;
  Map<String, ICommand> mockCommands;

  Appendable out;
  ImageModel model;
  IMEView view;
  Map<String, ICommand> commands;

  Scanner sc;

  @Before
  public void setUp() {
    log = new StringBuilder();
    mockModelTrue = new ImageModelMockTrue(log);
    mockView = new IMEViewMock(log);
    mockCommands = ICommand.generateMapFromList(Arrays.asList(new MockCommand(log)));

    out = new StringBuilder();
    model = new ImageModelImpl();
    view = new IMEViewImpl(model, out);
    commands = ICommand.generateMapFromList(Arrays.asList(
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
  }

  @Test
  public void testRun0() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new InputInteraction("load test-input.ppm test"),
            new InputInteraction("brighten 15 test test-bright"),
            new InputInteraction("horizontal-flip test test-flip"),
            new InputInteraction("save test-flip res/test-output.ppm"),
            new InputInteraction("quit"));
    Assert.assertEquals(expectedOutput, out.toString());
  }

  private void testRunHelper(StringBuilder expectedOutput, Map<String, ICommand> commands,
                             ImageModel model, IMEView view, Interaction... interactions) {
    StringBuilder fakeUserInput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());

    IMEController controller = new IMEControllerImpl(commands, model, view, input);
    controller.run();
  }
}