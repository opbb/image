package imecontroller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import imecontroller.icommand.BlueValueCommand;
import imecontroller.icommand.BrightenCommand;
import imecontroller.icommand.CloseCommand;
import imecontroller.icommand.GreenValueCommand;
import imecontroller.icommand.HoriFlipCommand;
import imecontroller.icommand.ICommand;
import imecontroller.icommand.IntensityValueCommand;
import imecontroller.icommand.LoadCommand;
import imecontroller.icommand.LumaValueCommand;
import imecontroller.icommand.MockCommand;
import imecontroller.icommand.RedValueCommand;
import imecontroller.icommand.SaveCommand;
import imecontroller.icommand.ValueCommand;
import imecontroller.icommand.VertFlipCommand;
import imecontroller.interaction.InputInteraction;
import imecontroller.interaction.Interaction;
import imecontroller.interaction.PrintInteraction;
import imemodel.ImageModel;
import imemodel.ImageModelImpl;
import imemodel.ImageModelMockTrue;
import imeview.IMEView;
import imeview.IMEViewImpl;
import imeview.IMEViewMock;

/**
 * This class is the tester class for the IMEFileController class, in which all public facing
 * methods of this class are tested.
 */
public class IMEFileControllerTest {

  StringBuilder log;
  ImageModel mockModelTrue;
  IMEView mockView;
  Map<String, ICommand> mockCommands;

  Appendable out;
  ImageModel model;
  IMEView view;
  Map<String, ICommand> commands;

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

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException0() {
    new IMEFileController(null, model, view, "file");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException1() {
    new IMEFileController(new HashMap<String, ICommand>(), null, view, "file");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException2() {
    new IMEFileController(new HashMap<String, ICommand>(), model, null, "file");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3() {
    new IMEFileController(null, model, view, new StringReader(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException4() {
    new IMEFileController(new HashMap<String, ICommand>(), null,
            view, new StringReader(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException5() {
    new IMEFileController(new HashMap<String, ICommand>(), model,
            null, new StringReader(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException6() {
    new IMEFileController(new HashMap<String, ICommand>(), model, view, "dummy-path");
  }


  // Tests a very simple, successful usage of the program where the user loads a file,
  // creates a brightened and a flipped version of that file, then quits the program.
  @Test
  public void testRun0() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new InputInteraction("load res/test-input.ppm test\nbrighten 15 test test-bright\n" +
                    "horizontal-flip test test-flip\nsave test-flip res/test-output.ppm\n"),
            new PrintInteraction("Ran load command.\nRan brighten command.\n" +
                    "Ran horizontal-flip command.\nRan save command.\n"));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }

  // Tests a more troubled use of the program, where the user mis-inputs multiple different commands
  // and the program prints out the appropriate errors.
  @Test
  public void testRun1() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new InputInteraction("load res/test-input.ppm test\nbrighten test 15 test-bright\n" +
                    "brighten -15 test test-dark\nhori-flip test test-flip\n" +
                    "save test-flip res/test-output.ppm\n"),
            new PrintInteraction("Ran load command.\n" +
                    "Invalid input, expected a double.\n" +
                    "The command should be structured as shown below:\n" +
                    "brighten [amount to brighten] [image to brighten] [new image name]\n\n" +
                    "Ran brighten command.\nRan brighten command.\n" +
                    "Couldn't recognize the command, \"hori-flip\", in line 4.\n" +
                    "The image test-flip is null.\n\nRan save command.\n"));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }

  // Tests a usage of the program using a file with many extra spaces and comments.
  @Test
  public void testRun2() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new InputInteraction("load res/test-input.ppm test     \n" +
                    "brighten    15    test  test-bright\n" +
                    "#this is a comment\n" +
                    "         " +
                    "             horizontal-flip   test               test-flip\n" +
                    "# another comments al; jfdngkjsfjks\n" +
                    "save test-flip res/test-output.ppm\n"),
            new PrintInteraction("Ran load command.\n" +
                    "Ran brighten command.\n" +
                    "Ran horizontal-flip command.\n" +
                    "Ran save command.\n"));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }

  @Test
  public void testRunUnknownCommand() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new InputInteraction("unknown-command haaaa ushfds sabd hsbdj\n"),
            new PrintInteraction("Couldn't recognize the command, \"unknown-command\", " +
                    "in line 1.\n"));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }


  @Test
  public void testRunException0() {
    StringBuilder expectedOutput = new StringBuilder();
    Assert.assertThrows("Ran out of inputs.", IllegalStateException.class, () -> {
      testRunHelper(expectedOutput, commands, model, view,
              new InputInteraction("load res/test-input.ppm test\nbrighten 10"),
              new PrintInteraction("Ran load command.\n"));
    });
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }

  @Test
  public void testRunCommandInputs() {
    StringReader input = new StringReader("mock this-is-the-next-input");
    new IMEFileController(mockCommands, mockModelTrue, mockView, input).run();
    Assert.assertEquals("commandText)\n" +
            "commandText)\n" +
            "toString) This is a mock view.\n" +
            "model: toString) This is a mock model.\n" +
            " view: null scanner: this-is-the-next-input commands: mock-mock\n" +
            "renderMessage) message: Ran mock command.\n\n", log.toString());
  }


  private void testRunHelper(StringBuilder expectedOutput, Map<String, ICommand> commands,
                             ImageModel model, IMEView view, Interaction... interactions) {
    StringBuilder fakeUserInput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());

    IMEController controller = new IMEFileController(commands, model, view, input);
    controller.run();
  }
}
