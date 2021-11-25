package imeview;



import imecontroller.IMEGUIController;

/**
 * This interface extends the original IMEView interface in abiding to SOLID principles, and
 * contains all required public facing methods so that the controller can properly instruct
 * the GUI view of what to do.
 */
public interface IMEGUIView extends IMEView {

  /**
   * Sets the given controller as the action listener for all input from this GUI.
   * @param controller the controller that will serve as the action listener
   */
  void setController(IMEGUIController controller);


  /**
   * This method is able to read in the filename of the chosen file and created a BufferedImage
   * equivalent and transposes the image within the GUI.
   * @param filename The image file by which to display in the GUI.
   */
  void buildImagePanel(String filename);


  /**
   * This method allows for the display of the loaded image into the GUI along with its histogram
   * data. Alternatively it handles the case of multiple images loaded as well.
   * @param newName the name of the file to be set up
   */
  void setUpImageAndHistogram(String newName);



  /**
   * Gets a double as input from the user.
   *
   * @param message the message asking for input
   * @param def the default value if the user clicks cancel
   * @return an inputted double
   */
  double getDoubleInput(String message, double def);

  /**
   * Creates a popup of a file browser/chooser and allows the user to choose a given file, of
   * only type ppm, png, jpeg, or bmp. This file name is then used for the controller to know what
   * image has been loaded and what is currently being worked on.
   * @return A string of the filename choosen by the user.
   */
  String getFilePath();

  /**
   * This method acts in sync with the original setUpImageAndHistogram method, in that it is
   * responsible for displaying the changes of an image after a given filter has been applied.
   * @param newName The name of the image that has been changed or altered.
   */
  void setUpLoadedImageAndHistogram(String newName);

  /**
   * Updates this view's list of opened files based off of the files the model has open.
   */
  void updateOpenedFiles();
}
