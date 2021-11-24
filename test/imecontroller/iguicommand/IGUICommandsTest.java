package imecontroller.iguicommand;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


import imecontroller.IMEGUIControllerImpl;
import imemodel.FilterMock;
import imemodel.ImageModel;
import imemodel.ImageModelMockTrue;
import imeview.IMEGUIView;
import imeview.IMEGUIViewMock;
public class IGUICommandsTest {

  StringBuilder log;
  ImageModel mockModel;
  IMEGUIView mockView;
  Map<String, IGUICommand> commands;
  ImageModel filterMock;
  IMEGUIControllerImpl dummyController;

  @Before
  public void setUp() {
    log = new StringBuilder();
    mockModel = new ImageModelMockTrue(log);
    mockView = new IMEGUIViewMock(log);
    commands = new HashMap<>();
    //commands.put("horizontal-flip", new HoriFlipGUICommand());
    filterMock = new FilterMock(log);
    dummyController = new IMEGUIControllerImpl(commands, mockModel, mockView);
    log.setLength(0); // Clears anything logged during setup.
  }

  //===============================================================================================

  @Test
  public void testRedValueGUICommand() {
    IGUICommand command = new RedValueGUICommand();
    command.execute(mockModel, mockView, "input0");
    Assert.assertEquals("getByComponent) name: input0 comp: red\n", log.toString());
  }

  @Test
  public void testRedValueGUICommandHelper() {
    IGUICommand command = new RedValueGUICommand();
    Assert.assertEquals("red-value", command.commandText());
  }

  //===============================================================================================

  @Test
  public void testGreenValueGUICommand() {
    IGUICommand command = new GreenValueGUICommand();
    command.execute(mockModel, mockView, "input0");
    Assert.assertEquals("getByComponent) name: input0 comp: green\n", log.toString());
  }

  @Test
  public void testGreenValueGUICommandHelper() {
    IGUICommand command = new GreenValueGUICommand();
    Assert.assertEquals("green-value", command.commandText());
  }

  //===============================================================================================

  @Test
  public void testBlueValueGUICommand() {
    IGUICommand command = new BlueValueGUICommand();
    command.execute(mockModel, mockView, "input0");
    Assert.assertEquals("getByComponent) name: input0 comp: blue\n", log.toString());
  }

  @Test
  public void testBlueValueGUICommandHelper() {
    IGUICommand command = new BlueValueGUICommand();
    Assert.assertEquals("blue-value", command.commandText());
  }

  //===============================================================================================

  @Test
  public void testLumaValueGUICommand() {
    IGUICommand command = new LumaValueGUICommand();
    command.execute(mockModel, mockView, "input0");
    Assert.assertEquals("greyscaleByLuma) name: input0\n", log.toString());
  }

  @Test
  public void testLumaValueGUICommandHelper() {
    IGUICommand command = new LumaValueGUICommand();
    Assert.assertEquals("luma-value", command.commandText());
  }

  //===============================================================================================

  @Test
  public void testIntensityValueGUICommand() {
    IGUICommand command = new IntensityValueGUICommand();
    command.execute(mockModel, mockView, "input0");
    Assert.assertEquals("getByComponent) name: input0 comp: intensity\n", log.toString());
  }

  @Test
  public void testIntensityValueGUICommandHelper() {
    IGUICommand command = new IntensityValueGUICommand();
    Assert.assertEquals("intensity-value", command.commandText());
  }

  //===============================================================================================

  @Test
  public void testValueGUICommand() {
    IGUICommand command = new ValueGUICommand();
    command.execute(mockModel, mockView, "input0");
    Assert.assertEquals("getByComponent) name: input0 comp: value\n", log.toString());
  }

  @Test
  public void testValueGUICommandHelper() {
    IGUICommand command = new ValueGUICommand();
    Assert.assertEquals("value", command.commandText());
  }

  //===============================================================================================

  @Test
  public void testBrightenGUICommand() {
    IGUICommand command = new BrightenGUICommand();
    command.execute(mockModel, mockView, "input0");
    Assert.assertEquals("getDoubleInput) message: Input amount to brighten " +
            "(A number from -255 to 255): def: 0.0\n" +
            "brighten) name: input0 increase: 0.0\n", log.toString());
  }

  @Test
  public void testBrightenGUICommandHelper() {
    IGUICommand command = new BrightenGUICommand();
    Assert.assertEquals("brighten", command.commandText());
  }

  //===============================================================================================

  @Test
  public void testHoriFlipGUICommand() {
    IGUICommand command = new HoriFlipGUICommand();
    command.execute(mockModel, mockView, "input0");
    Assert.assertEquals("flipHorizontal) name: input0\n", log.toString());
  }

  @Test
  public void testHoriFlipGUICommandHelper() {
    IGUICommand command = new HoriFlipGUICommand();
    Assert.assertEquals("horizontal-flip", command.commandText());
  }

  //===============================================================================================

  @Test
  public void testVertFlipGUICommand() {
    IGUICommand command = new VertFlipGUICommand();
    command.execute(mockModel, mockView, "input0");
    Assert.assertEquals("flipVertical) name: input0\n", log.toString());
  }

  @Test
  public void testVertFlipGUICommandHelper() {
    IGUICommand command = new VertFlipGUICommand();
    Assert.assertEquals("vertical-flip", command.commandText());
  }

  //===============================================================================================

  @Test
  public void testBlurGUICommand() {
    IGUICommand command = new BlurGUICommand();
    command.execute(mockModel, mockView, "input0");
    Assert.assertEquals("getImage) name: input0\n" +
            "loadImage) name: input0 image: [[[0, 1, 1]]]\n", log.toString());
  }

  @Test
  public void testBlurGUICommandHelper() {
    IGUICommand command = new BlurGUICommand();
    Assert.assertEquals("blur", command.commandText());
  }

  //===============================================================================================

  @Test
  public void testSepiaGUICommand() {
    IGUICommand command = new SepiaGUICommand();
    command.execute(mockModel, mockView, "input0");
    Assert.assertEquals("getImage) name: input0\n" +
            "loadImage) name: input0 image: [[[2, 2, 2]]]\n", log.toString());
  }

  @Test
  public void testSepiaGUICommandHelper() {
    IGUICommand command = new SepiaGUICommand();
    Assert.assertEquals("sepia", command.commandText());
  }

  //===============================================================================================

  @Test
  public void testSharpenGUICommand() {
    IGUICommand command = new SharpenGUICommand();
    command.execute(mockModel, mockView, "input0");
    Assert.assertEquals("getImage) name: input0\n" +
            "loadImage) name: input0 image: [[[1, 2, 3]]]\n", log.toString());
  }

  @Test
  public void testSharpenGUICommandHelper() {
    IGUICommand command = new SharpenGUICommand();
    Assert.assertEquals("sharpen", command.commandText());
  }
}
