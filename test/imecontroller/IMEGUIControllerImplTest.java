package imecontroller;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import javax.swing.event.ListSelectionEvent;

import imecontroller.iguicommand.IGUICommand;
import imecontroller.iguicommand.MockGUICommand;
import imemodel.ImageImpl;
import imemodel.ImageModel;
import imemodel.ImageModelMockFalse;
import imemodel.ImageModelMockTrue;
import imeview.IMEGUIViewMock;


/**
 * A test class for IMEGUIControllerImpl.
 */
public class IMEGUIControllerImplTest {

  StringBuilder log;
  Map<String, IGUICommand> commands;
  ImageModel mockModelTrue;
  ImageModel mockModelFalse;
  IMEGUIViewMock mockViewLoad;
  IMEGUIViewMock mockViewSave;


  @Before
  public void setUp() {
    log = new StringBuilder();


    commands = IGUICommand.generateMapFromList(Arrays.asList(
            new MockGUICommand(log)));
    mockModelTrue = new ImageModelMockTrue(log);
    mockModelFalse = new ImageModelMockFalse(log);
    mockViewLoad = new IMEGUIViewMock(log, "res/test-input.ppm");
    mockViewSave = new IMEGUIViewMock(log, "res/mock-save.ppm");

    log.setLength(0); // Clears anything that was logged during setUp().


  }




  @Test
  public void testRun() {
    // Run isn't actually implemented because this controller is reactive rather than proactive.
    Assert.assertTrue(true);
  }

  @Test
  public void testActionPerformedLoad() {
    IMEGUIController controller = new IMEGUIControllerImpl(commands, mockModelFalse, mockViewLoad);
    log.setLength(0);
    mockViewLoad.fireActionEvent("Load file");
    Assert.assertEquals("getFilePath)\n" +
            "hasImage) name: res/test-input.ppm\n" +
            "loadImage) name: res/test-input.ppm image: " +
            "[[[204, 204, 204], [188, 188, 188], [68, 68, 68]], " +
            "[[201, 201, 201], [23, 23, 23], [207, 207, 207]], " +
            "[[235, 235, 235], [165, 165, 165], [255, 255, 255]]]\n" +
            "setUpImageAndHistogram) newName: res/test-input.ppm\n" +
            "updateOpenedFiles)\n", log.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testActionPerformedLoadException0() {
    IMEGUIViewMock mockViewFail = new IMEGUIViewMock(log, "no-file");
    IMEGUIController controller = new IMEGUIControllerImpl(commands, mockModelFalse, mockViewFail);
    mockViewFail.fireActionEvent("Load file");
  }

  @Test
  public void testActionPerformedSave() {
    IMEGUIController controller = new IMEGUIControllerImpl(commands, mockModelTrue, mockViewSave);
    log.setLength(0);
    mockViewSave.fireActionEvent("Save file");
    Assert.assertEquals("getFilePath)\n" +
            "getImage) name: null\n", log.toString());
    // The code below tests that there is an image at the given path.
    Assert.assertTrue(new ImageImpl("res/mock-save.ppm") != null);
  }

  // Tests what happens when you try to save a null image.
  @Test
  public void testActionPerformedSaveFail() {
    IMEGUIController controller = new IMEGUIControllerImpl(commands, mockModelFalse, mockViewSave);
    log.setLength(0);
    mockViewSave.fireActionEvent("Save file");
    Assert.assertEquals("getFilePath)\n" +
            "getImage) name: null\n" +
            "renderMessage) message: The image res/mock-save.ppm is null.\n\n\n", log.toString());
  }

  @Test
  public void testActionPerformedCommand() {
    IMEGUIController controller = new IMEGUIControllerImpl(commands, mockModelFalse, mockViewLoad);
    mockViewLoad.fireActionEvent("Load file");
    log.setLength(0);
    mockViewLoad.fireActionEvent("mock");
    Assert.assertEquals("execute) model: toString) This is a mock model.\n" +
            "imageName: res/test-input.ppm\n" +
            "setUpLoadedImageAndHistogram) newName: res/test-input.ppm\n", log.toString());
  }

  // Tests that an error message is rendered when you try to execute a command on no image.
  @Test
  public void testActionPerformedCommandFail() {
    IMEGUIController controller = new IMEGUIControllerImpl(commands, mockModelFalse, mockViewLoad);
    log.setLength(0);
    mockViewLoad.fireActionEvent("mock");
    Assert.assertEquals("renderMessage) message: There is no image loaded.\n",
            log.toString());
  }

  // Tests that an exception is thrown when an invalid command is entered.
  @Test(expected = IllegalStateException.class)
  public void testActionPerformedCommandException() {
    IMEGUIController controller = new IMEGUIControllerImpl(commands, mockModelFalse, mockViewLoad);
    mockViewLoad.fireActionEvent("Load file");
    log.setLength(0);
    mockViewLoad.fireActionEvent("fake-command");
  }

  @Test
  public void testValueChanged0() {
    ListSelectionEvent e = new ListSelectionEvent(mockViewLoad, 0,
            0,false);
    IMEGUIController controller = new IMEGUIControllerImpl(commands, mockModelTrue, mockViewLoad);
    controller.valueChanged(e);
    Assert.assertEquals("setController)\n" +
            "getKeys)\n" +
            "setUpLoadedImageAndHistogram) newName: key0\n", log.toString());
  }

  @Test
  public void testValueChanged1() {
    ListSelectionEvent e = new ListSelectionEvent(mockViewLoad, 0,
            1,false);
    IMEGUIController controller = new IMEGUIControllerImpl(commands, mockModelTrue, mockViewLoad);
    controller.valueChanged(e);
    Assert.assertEquals("setController)\n" +
            "getKeys)\n" +
            "setUpLoadedImageAndHistogram) newName: key1\n", log.toString());
  }

  @Test
  public void testValueChanged2() {
    ListSelectionEvent e = new ListSelectionEvent(mockViewLoad, 1,
            0,false);
    IMEGUIController controller = new IMEGUIControllerImpl(commands, mockModelTrue, mockViewLoad);
    controller.valueChanged(e);
    Assert.assertEquals("setController)\n" +
            "getKeys)\n" +
            "setUpLoadedImageAndHistogram) newName: key0\n", log.toString());
  }
}

