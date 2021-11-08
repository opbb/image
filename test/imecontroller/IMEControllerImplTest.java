package imecontroller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

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
 * This class tests the IMEControllerImpl class, in which incorrect inputs, readable exceptions,
 * user error handling are tested. This tester class utilizes mocks of both a mock view and mock
 * models in checking that the controller is correctly processing what is has been given.
 */
public class IMEControllerImplTest {

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

  // Tests a very simple, successful usage of the program where the user loads a file,
  // creates a brightened and a flipped version of that file, then quits the program.
  @Test
  public void testRun0() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("load res/test-input.ppm test\n"),
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "| test\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("brighten 15 test test-bright\n"),
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "| test\n" +
                    "| test-bright\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("horizontal-flip test test-flip\n"),
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "| test\n" +
                    "| test-flip\n" +
                    "| test-bright\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("save test-flip res/test-output.ppm\n"),
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "| test\n" +
                    "| test-flip\n" +
                    "| test-bright\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("quit\n"),
            new PrintInteraction("You have quit the program."));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }

  // Tests a more realistic usage of the program where the user loads a file,
  // creates a brightened and a flipped version of that file with mis-inputs and using help,
  // then quits the program.
  @Test
  public void testRun1() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("load res/test-input.ppm test\n"),
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "| test\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("brighten test 15 test-bright\n"),
            new PrintInteraction("Invalid input, expected a double.\n" +
                    "The command should be structured as shown below:\n" +
                    "brighten [amount to brighten] [image to brighten] [new image name]\n" +
                    "\n" +
                    "|===============\n" +
                    "| Loaded images:\n" +
                    "| test\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("help\n"),
            new PrintInteraction("\n" +
                    "List of commands:\n" +
                    "\n" +
                    "red-value [image to get red-value of] [new image name]\n" +
                    "brighten [amount to brighten] [image to brighten] [new image name]\n" +
                    "intensity-value [image to get intensity-value of] [new image name]\n" +
                    "luma-value [image to get luma-value of] [new image name]\n" +
                    "load [file name] [new image name]\n" +
                    "vertical-flip [image to flip] [new image name]\n" +
                    "green-value [image to get green-value of] [new image name]\n" +
                    "save [image to save] [file name to save as]\n" +
                    "horizontal-flip [image to flip] [new image name]\n" +
                    "value [image to get value of] [new image name]\n" +
                    "close [image to close]\n" +
                    "blue-value [image to get blue-value of] [new image name]\n" +
                    "\n" +
                    "q or quit to quit the program\n" +
                    "\n" +
                    "|===============\n" +
                    "| Loaded images:\n" +
                    "| test\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("brighten 15 test test-bright\n"),
            new PrintInteraction("" +
                    "|===============\n" +
                    "| Loaded images:\n" +
                    "| test\n" +
                    "| test-bright\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("horizontal-flip test test-flip\n"),
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "| test\n" +
                    "| test-flip\n" +
                    "| test-bright\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("save test-flip res/test-output.ppm\n"),
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "| test\n" +
                    "| test-flip\n" +
                    "| test-bright\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("QUIT\n"),
            new PrintInteraction("You have quit the program."));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }

  // Tests quitting using "q"
  @Test
  public void testRunQuit0() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("q\n"),
            new PrintInteraction("You have quit the program."));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }

  // Tests quitting using "Q"
  @Test
  public void testRunQuit1() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("Q\n"),
            new PrintInteraction("You have quit the program."));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }

  // Tests quitting using "quit"
  @Test
  public void testRunQuit2() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("quit\n"),
            new PrintInteraction("You have quit the program."));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }

  // Tests quitting using "Quit"
  @Test
  public void testRunQuit3() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("Quit\n"),
            new PrintInteraction("You have quit the program."));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }

  // Tests quitting using "qUIt"
  @Test
  public void testRunQuit4() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("qUIt\n"),
            new PrintInteraction("You have quit the program."));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }

  // Tests getting help with help.
  @Test
  public void testRunHelp0() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("help\n"),
            new PrintInteraction("\n" +
                    "List of commands:\n" +
                    "\n" +
                    "red-value [image to get red-value of] [new image name]\n" +
                    "brighten [amount to brighten] [image to brighten] [new image name]\n" +
                    "intensity-value [image to get intensity-value of] [new image name]\n" +
                    "luma-value [image to get luma-value of] [new image name]\n" +
                    "load [file name] [new image name]\n" +
                    "vertical-flip [image to flip] [new image name]\n" +
                    "green-value [image to get green-value of] [new image name]\n" +
                    "save [image to save] [file name to save as]\n" +
                    "horizontal-flip [image to flip] [new image name]\n" +
                    "value [image to get value of] [new image name]\n" +
                    "close [image to close]\n" +
                    "blue-value [image to get blue-value of] [new image name]\n" +
                    "\n" +
                    "q or quit to quit the program\n" +
                    "\n" +
                    "|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("q\n"),
            new PrintInteraction("You have quit the program."));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }

  // Tests getting help with "help".
  @Test
  public void testRunHelp1() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("\"help\"\n"),
            new PrintInteraction("\n" +
                    "List of commands:\n" +
                    "\n" +
                    "red-value [image to get red-value of] [new image name]\n" +
                    "brighten [amount to brighten] [image to brighten] [new image name]\n" +
                    "intensity-value [image to get intensity-value of] [new image name]\n" +
                    "luma-value [image to get luma-value of] [new image name]\n" +
                    "load [file name] [new image name]\n" +
                    "vertical-flip [image to flip] [new image name]\n" +
                    "green-value [image to get green-value of] [new image name]\n" +
                    "save [image to save] [file name to save as]\n" +
                    "horizontal-flip [image to flip] [new image name]\n" +
                    "value [image to get value of] [new image name]\n" +
                    "close [image to close]\n" +
                    "blue-value [image to get blue-value of] [new image name]\n" +
                    "\n" +
                    "q or quit to quit the program\n" +
                    "\n" +
                    "|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("q\n"),
            new PrintInteraction("You have quit the program."));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }

  // Tests getting help with "HeLp".
  @Test
  public void testRunHelp2() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("\"HeLp\"\n"),
            new PrintInteraction("\n" +
                    "List of commands:\n" +
                    "\n" +
                    "red-value [image to get red-value of] [new image name]\n" +
                    "brighten [amount to brighten] [image to brighten] [new image name]\n" +
                    "intensity-value [image to get intensity-value of] [new image name]\n" +
                    "luma-value [image to get luma-value of] [new image name]\n" +
                    "load [file name] [new image name]\n" +
                    "vertical-flip [image to flip] [new image name]\n" +
                    "green-value [image to get green-value of] [new image name]\n" +
                    "save [image to save] [file name to save as]\n" +
                    "horizontal-flip [image to flip] [new image name]\n" +
                    "value [image to get value of] [new image name]\n" +
                    "close [image to close]\n" +
                    "blue-value [image to get blue-value of] [new image name]\n" +
                    "\n" +
                    "q or quit to quit the program\n" +
                    "\n" +
                    "|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("q\n"),
            new PrintInteraction("You have quit the program."));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }

  // Tests getting help with hElP.
  @Test
  public void testRunHelp3() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("hElP\n"),
            new PrintInteraction("\n" +
                    "List of commands:\n" +
                    "\n" +
                    "red-value [image to get red-value of] [new image name]\n" +
                    "brighten [amount to brighten] [image to brighten] [new image name]\n" +
                    "intensity-value [image to get intensity-value of] [new image name]\n" +
                    "luma-value [image to get luma-value of] [new image name]\n" +
                    "load [file name] [new image name]\n" +
                    "vertical-flip [image to flip] [new image name]\n" +
                    "green-value [image to get green-value of] [new image name]\n" +
                    "save [image to save] [file name to save as]\n" +
                    "horizontal-flip [image to flip] [new image name]\n" +
                    "value [image to get value of] [new image name]\n" +
                    "close [image to close]\n" +
                    "blue-value [image to get blue-value of] [new image name]\n" +
                    "\n" +
                    "q or quit to quit the program\n" +
                    "\n" +
                    "|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("q\n"),
            new PrintInteraction("You have quit the program."));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }

  @Test
  public void testRunUnknownCommand() {
    StringBuilder expectedOutput = new StringBuilder();
    testRunHelper(expectedOutput, commands, model, view,
            new PrintInteraction("|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("bazinga\n"),
            new PrintInteraction("Couldn't recognize the inputted command \"bazinga\", " +
                    "input \"help\" for a list of available commands.\n" +
                    "\n" +
                    "|===============\n" +
                    "| Loaded images:\n" +
                    "|===============\n" +
                    "\n" +
                    "Input new command: "),
            new InputInteraction("q\n"),
            new PrintInteraction("You have quit the program."));
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }


  @Test
  public void testRunException0() {
    StringBuilder expectedOutput = new StringBuilder();
    Assert.assertThrows("Ran out of inputs.", IllegalStateException.class, () -> {
      testRunHelper(expectedOutput, commands, model, view,
              new PrintInteraction("|===============\n" +
                      "| Loaded images:\n" +
                      "|===============\n" +
                      "\n" +
                      "Input new command: "));
    });
    Assert.assertEquals(expectedOutput.toString(), out.toString());
  }




  @Test
  public void testRunCommandInputs() {
    StringReader input = new StringReader("mock this-is-the-next-input q");
    new IMEControllerImpl(mockCommands, mockModelTrue, mockView, input).run();
    Assert.assertEquals("commandText)\n" +
            "toString) This is a mock view.\n" +
            "renderMessage) message: null\n" +
            "\n" +
            "Input new command: \n" +
            "commandText)\n" +
            "toString) This is a mock view.\n" +
            "model: toString) This is a mock model.\n" +
            " view: null scanner: this-is-the-next-input commands: mock-mock\n" +
            "toString) This is a mock view.\n" +
            "renderMessage) message: null\n" +
            "\n" +
            "Input new command: \n" +
            "renderMessage) message: You have quit the program.\n", log.toString());
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