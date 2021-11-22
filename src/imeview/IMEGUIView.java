package imeview;

import javax.swing.*;

import imecontroller.IMEGUIController;

public interface IMEGUIView extends IMEView {

  /**
   * Sets the given controller as the action listener for all input from this GUI.
   * @param controller the controller that will serve as the action listener
   */
  void setController(IMEGUIController controller);



  void buildImagePanel(String filename);

  void buildHistPanel(String filename);

  /**
   * Gets the main JComponent in this view.
   * @return the main JComponent
   */
  JComponent getMainComponent();

  /**
   * Gets a double as input from the user.
   * @return an inputted double
   */
  double getDoubleInput(String message);

}
