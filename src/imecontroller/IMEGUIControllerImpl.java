package imecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import imecontroller.icommand.ICommand;
import imecontroller.iguicommand.IGUICommand;
import imemodel.Image;
import imemodel.ImageImpl;
import imemodel.ImageModel;
import imeview.IMEGUIView;
import imeview.IMEGUIViewImpl;

public class IMEGUIControllerImpl implements IMEGUIController, ActionListener,
        ItemListener, ListSelectionListener {

  private final Map<String, IGUICommand> commands;
  private final ImageModel model;
  private final IMEGUIView view;
  private Image currentImg;


  /**
   * A basic constructor for the IMEGUIController. Takes input from the GUI.
   *
   * @param commands the commands that this controller can use
   * @param model    the model that this controller will control
   * @param view     the view that this controller will use to display things
   */
  public IMEGUIControllerImpl(Map<String, IGUICommand> commands,
                              ImageModel model, IMEGUIView view) {
    if (commands == null || model == null || view == null) {
      throw new IllegalArgumentException("Inputs must not be null.");
    }
    this.commands = commands;
    this.model = model;
    this.view = view;
    this.view.setController(this);
  }

  @Override
  public void run() {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch(e.getActionCommand()) {

      case("LoadImage"):

      case ("Load file"):
        view.setUpImageAndHistogram();
//        JFileChooser fchooser = new JFileChooser("");
//        FileNameExtensionFilter filter = new FileNameExtensionFilter(
//                "JPG & PPM & PNG & BMP Images", "ppm", "png", "bmp", "jpg");
//        fchooser.setFileFilter(filter);
//        int retvalue = fchooser.showOpenDialog(view.getMainComponent());
//        if (retvalue == JFileChooser.APPROVE_OPTION) {
//          File f = fchooser.getSelectedFile();
//          String newName = f.getAbsolutePath();
//          model.loadImage(newName, new ImageImpl(newName));
//        }
          break;
      default:
        boolean executedCommand = false; // Boolean flag so that we know if we executed or not.
        for (String key : commands.keySet()) {
          if (key.equals(e.getActionCommand())) {
            commands.get(key).execute(model, view, commands);
            executedCommand = true; // Record that we have executed.
            break; // Breaks loop so that we don't waste energy checking the remaining commands.
          }
        }


        // Block below check for if the given command was invalid (i.e. no command was executed).
        if (!executedCommand) {
          throw new IllegalStateException("Couldn't recognize the actionCommand \"" +
                  e.getActionCommand() + "\"");
        }
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    switch(e.getStateChange()) {

    }
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    switch(e.getFirstIndex()) {

    }
  }
}
