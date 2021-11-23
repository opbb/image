package imeview;

import imecontroller.IMEGUIController;

public interface IMEGUIView extends IMEView {

  /**
   * Sets the given controller as the action listener for all input from this GUI.
   * @param controller the controller that will serve as the action listener
   */
  void setController(IMEGUIController controller);



  void buildImagePanel(String filename);

  void buildHistPanel(String filename);

  void setUpImageAndHistogram();
}
