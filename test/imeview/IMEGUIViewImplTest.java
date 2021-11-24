package imeview;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import imecontroller.IMEGUIController;
import imecontroller.IMEGUIControllerMock;
import imecontroller.iguicommand.IGUICommand;
import imemodel.ImageModel;
import imemodel.ImageModelMockTrue;

/**
 * A test class for IMEGUIViewImpl.
 */
public class IMEGUIViewImplTest {

  StringBuilder log;
  ImageModel mockModel;
  IMEGUIController mockController;
  Map<String, IGUICommand> commands;

  @Before
  public void setUp() {

    log = new StringBuilder();
    mockModel = new ImageModelMockTrue(log);
    mockController = new IMEGUIControllerMock(log);
    commands = new HashMap<>();
    log.setLength(0); // Resets anything that might have been logged in setUp().
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIMEGUIViewImplConstructor0() {
    new IMEGUIViewImpl(null, commands);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIMEGUIViewImplConstructor1() {
    new IMEGUIViewImpl(mockModel, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIMEGUIViewImplConstructor2() {
    new IMEGUIViewImpl(null, null);
  }

  @Test
  public void testSetController() {
    IMEGUIView view = new IMEGUIViewImpl(mockModel, commands);
    view.setController(mockController);

    Assert.assertEquals("", log.toString());

  }
}
