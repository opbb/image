package imeview;

import java.awt.event.ActionListener;

import javax.swing.*;

import imecontroller.IMEGUIController;

public class IMEGUIViewMock implements IMEGUIView {

  ActionListener listener;
  StringBuilder log;

  public IMEGUIViewMock(StringBuilder log) {
    this.log = log;
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
    return null;
  }

  // Bazinga

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
