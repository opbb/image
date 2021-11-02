package imecontroller.icommand;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import imemodel.ImageModel;
import imemodel.ImageModelMockFalse;
import imeview.IMEView;
import imeview.IMEViewMock;
import imemodel.ImageModelMockTrue;

/**
 * ICommands tester class in which all commands are tested with varying given inputs and checks for
 * correct commands and managing failures correctly.
 */
public class ICommandsTest {

  StringBuilder log;
  ImageModel mockModelTrue;
  ImageModel mockModelFalse;
  IMEView mockView;
  Scanner sc;

  @Before
  public void setUp() {
    log = new StringBuilder();
    mockModelTrue = new ImageModelMockTrue(log);
    mockModelFalse = new ImageModelMockFalse(log);
    mockView = new IMEViewMock(log);
  }

  @Test
  public void testBlueValueCommandDiffArgs() {
    ICommand command = new BlueValueCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "duplicateImage) name: input0 newName: input1\n" +
            "getByComponent) name: input1 comp: blue\n", log.toString());
  }

  @Test
  public void testBlueValueCommandSameArgs() {
    ICommand command = new BlueValueCommand();
    sc = new Scanner(new StringReader("input0 input0"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "getByComponent) name: input0 comp: blue\n", log.toString());
  }

  @Test
  public void testBlueValueCommandFail() {
    ICommand command = new BlueValueCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelFalse, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
                    "renderMessage) message: " +
            "The given image name input0 does not exist.\n\n\n", log.toString());
  }

  @Test
  public void testBlueValueCommandHelpers() {
    ICommand command = new BlueValueCommand();
    Assert.assertEquals("blue-value [image to get blue-value of] [new image name]",
            command.helpMessage());
    Assert.assertEquals("blue-value", command.commandText());
  }

  @Test(expected = IllegalStateException.class)
  public void testBlueValueCommandException0() {
    ICommand command = new BlueValueCommand();
    sc = new Scanner(new StringReader(""));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test(expected = IllegalStateException.class)
  public void testBlueValueCommandException1() {
    ICommand command = new BlueValueCommand();
    sc = new Scanner(new StringReader("input0"));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test
  public void testRedValueCommandDiffArgs() {
    ICommand command = new RedValueCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "duplicateImage) name: input0 newName: input1\n" +
            "getByComponent) name: input1 comp: red\n", log.toString());
  }

  @Test
  public void testRedValueCommandSameArgs() {
    ICommand command = new RedValueCommand();
    sc = new Scanner(new StringReader("input0 input0"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "getByComponent) name: input0 comp: red\n", log.toString());
  }

  @Test
  public void testRedValueCommandFail() {
    ICommand command = new RedValueCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelFalse, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "renderMessage) message: The given image name input0 does not exist.\n\n\n",
            log.toString());
  }

  @Test
  public void testRedValueCommandHelpers() {
    ICommand command = new RedValueCommand();
    Assert.assertEquals("red-value [image to get red-value of] [new image name]",
            command.helpMessage());
    Assert.assertEquals("red-value", command.commandText());
  }

  @Test(expected = IllegalStateException.class)
  public void testRedValueCommandException0() {
    ICommand command = new RedValueCommand();
    sc = new Scanner(new StringReader(""));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test(expected = IllegalStateException.class)
  public void testRedValueCommandException1() {
    ICommand command = new RedValueCommand();
    sc = new Scanner(new StringReader("input0"));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test
  public void testGreenValueCommandDiffArgs() {
    ICommand command = new GreenValueCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "duplicateImage) name: input0 newName: input1\n" +
            "getByComponent) name: input1 comp: green\n", log.toString());
  }

  @Test
  public void testGreenValueCommandSameArgs() {
    ICommand command = new GreenValueCommand();
    sc = new Scanner(new StringReader("input0 input0"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "getByComponent) name: input0 comp: green\n", log.toString());
  }

  @Test
  public void testGreenValueCommandFail() {
    ICommand command = new GreenValueCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelFalse, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
                    "renderMessage) message: The given image name input0 does not exist.\n\n\n",
            log.toString());
  }

  @Test
  public void testGreenValueCommandHelpers() {
    ICommand command = new GreenValueCommand();
    Assert.assertEquals("green-value [image to get green-value of] [new image name]",
            command.helpMessage());
    Assert.assertEquals("green-value", command.commandText());
  }

  @Test(expected = IllegalStateException.class)
  public void testGreenValueCommandException0() {
    ICommand command = new GreenValueCommand();
    sc = new Scanner(new StringReader(""));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test(expected = IllegalStateException.class)
  public void testGreenValueCommandException1() {
    ICommand command = new GreenValueCommand();
    sc = new Scanner(new StringReader("input0"));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test
  public void testIntensityValueCommandDiffArgs() {
    ICommand command = new IntensityValueCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "duplicateImage) name: input0 newName: input1\n" +
            "getByComponent) name: input1 comp: intensity\n", log.toString());
  }

  @Test
  public void testIntensityValueCommandSameArgs() {
    ICommand command = new IntensityValueCommand();
    sc = new Scanner(new StringReader("input0 input0"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "getByComponent) name: input0 comp: intensity\n", log.toString());
  }

  @Test
  public void testIntensityValueCommandFail() {
    ICommand command = new IntensityValueCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelFalse, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
                    "renderMessage) message: The given image name input0 does not exist.\n\n\n",
            log.toString());
  }

  @Test
  public void testIntensityValueCommandHelpers() {
    ICommand command = new IntensityValueCommand();
    Assert.assertEquals("intensity-value [image to get intensity-value of] [new image name]",
            command.helpMessage());
    Assert.assertEquals("intensity-value", command.commandText());
  }

  @Test(expected = IllegalStateException.class)
  public void testIntensityValueCommandException0() {
    ICommand command = new IntensityValueCommand();
    sc = new Scanner(new StringReader(""));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test(expected = IllegalStateException.class)
  public void testIntensityValueCommandException1() {
    ICommand command = new IntensityValueCommand();
    sc = new Scanner(new StringReader("input0"));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test
  public void testLumaValueCommandDiffArgs() {
    ICommand command = new LumaValueCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "duplicateImage) name: input0 newName: input1\n" +
            "greyscaleByLuma) name: input1\n", log.toString());
  }

  @Test
  public void testLumaValueCommandSameArgs() {
    ICommand command = new LumaValueCommand();
    sc = new Scanner(new StringReader("input0 input0"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "greyscaleByLuma) name: input0\n", log.toString());
  }

  @Test
  public void testLumaValueCommandFail() {
    ICommand command = new LumaValueCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelFalse, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
                    "renderMessage) message: The given image name input0 does not exist.\n\n\n",
            log.toString());
  }

  @Test
  public void testLumaValueCommandHelpers() {
    ICommand command = new LumaValueCommand();
    Assert.assertEquals("luma-value [image to get luma-value of] [new image name]",
            command.helpMessage());
    Assert.assertEquals("luma-value", command.commandText());
  }

  @Test(expected = IllegalStateException.class)
  public void testLumaValueCommandException0() {
    ICommand command = new LumaValueCommand();
    sc = new Scanner(new StringReader(""));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test(expected = IllegalStateException.class)
  public void testLumaValueCommandException1() {
    ICommand command = new LumaValueCommand();
    sc = new Scanner(new StringReader("input0"));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test
  public void testValueCommandDiffArgs() {
    ICommand command = new ValueCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "duplicateImage) name: input0 newName: input1\n" +
            "getByComponent) name: input1 comp: value\n", log.toString());
  }

  @Test
  public void testValueCommandSameArgs() {
    ICommand command = new ValueCommand();
    sc = new Scanner(new StringReader("input0 input0"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "getByComponent) name: input0 comp: value\n", log.toString());
  }

  @Test
  public void testValueCommandFail() {
    ICommand command = new ValueCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelFalse, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
                    "renderMessage) message: The given image name input0 does not exist.\n\n\n",
            log.toString());
  }

  @Test
  public void testValueCommandHelpers() {
    ICommand command = new ValueCommand();
    Assert.assertEquals("value [image to get value of] [new image name]",
            command.helpMessage());
    Assert.assertEquals("value", command.commandText());
  }

  @Test(expected = IllegalStateException.class)
  public void testValueCommandException0() {
    ICommand command = new ValueCommand();
    sc = new Scanner(new StringReader(""));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test(expected = IllegalStateException.class)
  public void testValueCommandException1() {
    ICommand command = new ValueCommand();
    sc = new Scanner(new StringReader("input0"));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test
  public void testCloseCommand() {
    ICommand command = new CloseCommand();
    sc = new Scanner(new StringReader("input0"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
                    "closeImage) name: input0\n"
            , log.toString());
  }

  @Test
  public void testCloseCommandFail() {
    ICommand command = new CloseCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelFalse, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
                    "renderMessage) message: The given image name input0 does not exist.\n\n\n",
            log.toString());
  }

  @Test
  public void testCloseCommandHelpers() {
    ICommand command = new CloseCommand();
    Assert.assertEquals("close [image to close]",
            command.helpMessage());
    Assert.assertEquals("close", command.commandText());
  }

  @Test(expected = IllegalStateException.class)
  public void testCloseCommandException0() {
    ICommand command = new CloseCommand();
    sc = new Scanner(new StringReader(""));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test
  public void testBrightenCommandDiffArgs() {
    ICommand command = new BrightenCommand();
    sc = new Scanner(new StringReader("15 input0 input1"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "duplicateImage) name: input0 newName: input1\n" +
            "brighten) name: input1 increase: 15.0\n", log.toString());
  }

  @Test
  public void testBrightenCommandSameArgs() {
    ICommand command = new BrightenCommand();
    sc = new Scanner(new StringReader("15 input0 input0"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "brighten) name: input0 increase: 15.0\n", log.toString());
  }

  @Test
  public void testBrightenCommandFail0() {
    ICommand command = new BrightenCommand();
    sc = new Scanner(new StringReader("15 input0 input1"));
    command.execute(mockModelFalse, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
                    "renderMessage) message: The given image name input0 does not exist.\n\n\n",
            log.toString());
  }

  @Test
  public void testBrightenCommandFail1() {
    ICommand command = new BrightenCommand();
    sc = new Scanner(new StringReader("bazinga! input0 input1"));
    command.execute(mockModelFalse, mockView, sc);
    Assert.assertEquals("renderMessage) message: Invalid input, expected a double.\n\n" +
                    "renderMessage) message: The command should be structured as shown below:\n" +
                    "brighten [amount to brighten] [image to brighten] [new image name]\n\n\n",
            log.toString());
  }

  @Test
  public void testBrightenCommandHelpers() {
    ICommand command = new BrightenCommand();
    Assert.assertEquals("brighten [amount to brighten] " +
                    "[image to brighten] [new image name]",
            command.helpMessage());
    Assert.assertEquals("brighten", command.commandText());
  }

  @Test(expected = IllegalStateException.class)
  public void testBrightenCommandException0() {
    ICommand command = new BrightenCommand();
    sc = new Scanner(new StringReader(""));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test(expected = IllegalStateException.class)
  public void testBrightenCommandException1() {
    ICommand command = new BrightenCommand();
    sc = new Scanner(new StringReader("15 input0"));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test
  public void testHoriFlipCommandDiffArgs() {
    ICommand command = new HoriFlipCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "duplicateImage) name: input0 newName: input1\n" +
            "flipHorizontal) name: input1\n", log.toString());
  }

  @Test
  public void testHoriFlipCommandSameArgs() {
    ICommand command = new HoriFlipCommand();
    sc = new Scanner(new StringReader("input0 input0"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "flipHorizontal) name: input0\n", log.toString());
  }

  @Test
  public void testHoriFlipCommandFail() {
    ICommand command = new HoriFlipCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelFalse, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
                    "renderMessage) message: The given image name input0 does not exist.\n\n\n",
            log.toString());
  }

  @Test
  public void testHoriFlipCommandHelpers() {
    ICommand command = new HoriFlipCommand();
    Assert.assertEquals("horizontal-flip [image to flip] [new image name]",
            command.helpMessage());
    Assert.assertEquals("horizontal-flip", command.commandText());
  }

  @Test(expected = IllegalStateException.class)
  public void testHoriFlipCommandException0() {
    ICommand command = new HoriFlipCommand();
    sc = new Scanner(new StringReader(""));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test(expected = IllegalStateException.class)
  public void testHoriFlipCommandException1() {
    ICommand command = new HoriFlipCommand();
    sc = new Scanner(new StringReader("input0"));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test
  public void testVertFlipCommandDiffArgs() {
    ICommand command = new VertFlipCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "duplicateImage) name: input0 newName: input1\n" +
            "flipVertical) name: input1\n", log.toString());
  }

  @Test
  public void testVertFlipCommandSameArgs() {
    ICommand command = new VertFlipCommand();
    sc = new Scanner(new StringReader("input0 input0"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
            "flipVertical) name: input0\n", log.toString());
  }

  @Test
  public void testVertFlipCommandFail() {
    ICommand command = new HoriFlipCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelFalse, mockView, sc);
    Assert.assertEquals("hasImage) name: input0\n" +
                    "renderMessage) message: The given image name input0 does not exist.\n\n\n",
            log.toString());
  }

  @Test
  public void testVertFlipCommandHelpers() {
    ICommand command = new VertFlipCommand();
    Assert.assertEquals("vertical-flip [image to flip] [new image name]",
            command.helpMessage());
    Assert.assertEquals("vertical-flip", command.commandText());
  }

  @Test(expected = IllegalStateException.class)
  public void testVertFlipCommandException0() {
    ICommand command = new VertFlipCommand();
    sc = new Scanner(new StringReader(""));
    command.execute(mockModelTrue, mockView, sc);
  }
  
  @Test(expected = IllegalStateException.class)
  public void testVertFlipCommandException1() {
    ICommand command = new VertFlipCommand();
    sc = new Scanner(new StringReader("input0"));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test
  public void testLoadCommand() {
    ICommand command = new LoadCommand();
    sc = new Scanner(new StringReader("res/test-input.ppm test"));
    command.execute(mockModelTrue, mockView, sc);
    Assert.assertEquals("loadImage) name: test image: [[[204, 204, 204], " +
                    "[188, 188, 188], [68, 68, 68]], [[201, 201, 201], [23, 23, 23], " +
            "[207, 207, 207]], [[235, 235, 235], [165, 165, 165], [255, 255, 255]]]\n",
            log.toString());
  }

  @Test
  public void testLoadCommandFail() {
    ICommand command = new LoadCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelFalse, mockView, sc);
    Assert.assertEquals(
            "renderMessage) message: The given file name input0 does not exist.\n\n\n",
            log.toString());
  }

  @Test
  public void testLoadCommandHelpers() {
    ICommand command = new LoadCommand();
    Assert.assertEquals("load [file name] [new image name]",
            command.helpMessage());
    Assert.assertEquals("load", command.commandText());
  }

  @Test(expected = IllegalStateException.class)
  public void testLoadCommandException0() {
    ICommand command = new LoadCommand();
    sc = new Scanner(new StringReader(""));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test(expected = IllegalStateException.class)
  public void testLoadCommandException1() {
    ICommand command = new LoadCommand();
    sc = new Scanner(new StringReader("input0"));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test
  public void testSaveCommandFail() {
    ICommand command = new SaveCommand();
    sc = new Scanner(new StringReader("input0 input1"));
    command.execute(mockModelFalse, mockView, sc);
    Assert.assertEquals("getImage) name: input0\n" +
            "renderMessage) message: The image input0 is null.\n\n\n",
            log.toString());
  }

  @Test
  public void testSaveCommandHelpers() {
    ICommand command = new SaveCommand();
    Assert.assertEquals("save [image to save] [file name to save as]",
            command.helpMessage());
    Assert.assertEquals("save", command.commandText());
  }

  @Test(expected = IllegalStateException.class)
  public void testSaveCommandException0() {
    ICommand command = new SaveCommand();
    sc = new Scanner(new StringReader(""));
    command.execute(mockModelTrue, mockView, sc);
  }

  @Test(expected = IllegalStateException.class)
  public void testSaveCommandException1() {
    ICommand command = new SaveCommand();
    sc = new Scanner(new StringReader("input0"));
    command.execute(mockModelTrue, mockView, sc);
  }
}