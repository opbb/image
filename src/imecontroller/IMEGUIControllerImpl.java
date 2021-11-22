package imecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import imecontroller.icommand.ICommand;
import imemodel.Image;
import imemodel.ImageModel;
import imeview.IMEGUIView;

public class IMEGUIControllerImpl implements IMEGUIController, ActionListener,
        ItemListener, ListSelectionListener {

  private final Map<String, ICommand> commands;
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
  public IMEGUIControllerImpl(Map<String, ICommand> commands, ImageModel model, IMEGUIView view) {
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
