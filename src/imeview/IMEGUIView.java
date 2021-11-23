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


  void setUpImageAndHistogram(String newName);

  /**
   * Gets the main JComponent in this view.
   * @return the main JComponent
   */
  JComponent getMainComponent();

  /**
   * Gets a double as input from the user.
   *
   * @param message the message asking for input
   * @param def the default value if the user clicks cancel
   * @return an inputted double
   */
  double getDoubleInput(String message, double def);

  String getFilePath();
}
