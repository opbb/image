package imeview;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import imecontroller.IMEGUIController;

/**
 * This is a mock GUIView of the IMEGUIView interface, in which we give the class stub methods to
 * add to a string builder (log)
 */
public class IMEGUIViewMock implements IMEGUIView {

  ActionListener listener;
  StringBuilder log;
  String mockFilePath;

  /**
   * Default Constructor for this mock class.
   * @param log A StringBuilder used to accumulate strings for testing.
   * @param mockFilePath A MockFilePath used for tracking files used.
   */
  public IMEGUIViewMock(StringBuilder log, String mockFilePath) {
    this.log = log;
    this.mockFilePath = mockFilePath;
  }

  /**
   * Constructor used only for needing a log.
   * @param log StringBuilder used to append strings of inputs together.
   */
  public IMEGUIViewMock(StringBuilder log) {
  }

  /**
   * Fires an action event with the given actionCommand.
   * @param actionCommand the action command to be fired
   */
  public void fireActionEvent(String actionCommand) {
    JButton button = new JButton();
    button.setActionCommand(actionCommand);
    button.addActionListener(listener);
    button.doClick();
  }

  @Override
  public void setController(IMEGUIController controller) {
    log.append("setController)\n");
    listener = controller;
  }

  @Override
  public void buildImagePanel(String filename) {
    log.append("buildImagePanel) filename:" + filename + "\n");
  }

  @Override
  public void setUpImageAndHistogram(String newName) {
    log.append("setUpImageAndHistogram) newName: " + newName + "\n");
  }

  @Override
  public double getDoubleInput(String message, double def) {
    log.append("getDoubleInput) message: " + message + " def: " + def + "\n");
    return def;
  }

  @Override
  public String getFilePath() {
    log.append("getFilePath)\n");
    return mockFilePath;
  }


  @Override
  public void setUpLoadedImageAndHistogram(String newName) {
    log.append("setUpLoadedImageAndHistogram) newName: " + newName + "\n");
  }

  @Override
  public void updateOpenedFiles() {
    log.append("updateOpenedFiles)\n");
  }

  @Override
  public void renderMessage(String message) {
    log.append("renderMessage) message: " + message + "\n");
  }
}
