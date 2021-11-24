package imeview;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import imecontroller.IMEGUIController;

public class IMEGUIViewMock implements IMEGUIView {

  ActionListener listener;

  public IMEGUIViewMock(ActionListener listener) {
    this.listener = listener;
  }

  public void fireActionEvent(String command) {
    JButton testButton = new JButton();
    testButton.setActionCommand(command);
    testButton.addActionListener(listener);
    testButton.doClick();
  }

  @Override
  public void setController(IMEGUIController controller) {

  }

  @Override
  public void buildImagePanel(String filename) {

  }

  @Override
  public void buildHistPanel(String filename) {

  }

  @Override
  public void setUpImageAndHistogram(String newName) {

  }

  @Override
  public JComponent getMainComponent() {
    return null;
  }

  @Override
  public double getDoubleInput(String message, double def) {
    return 0;
  }

  @Override
  public String getFilePath() {
    return null;
  }

  @Override
  public void setUpLoadedImageAndHistogram(String newName) {

  }

  @Override
  public void updateOpenedFiles() {

  }

  @Override
  public void setToMap(String name, BufferedImage img) {

  }

  @Override
  public void renderMessage(String message) {

  }
}
