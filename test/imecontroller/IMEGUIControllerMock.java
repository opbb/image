package imecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import javax.swing.event.ListSelectionEvent;

/**
 * A mock controller for testing that the view fires events correctly. Logs events.
 */
public class IMEGUIControllerMock implements IMEGUIController {

  StringBuilder log;

  /**
   * A basic constructor.
   * @param log a string builder that we use to log any events this controller recieves
   */
  public IMEGUIControllerMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void run() {
    log.append("run)\n");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    log.append("actionPerformed) actionCommand: " + e.getActionCommand() + "\n");
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    log.append("actionPerformed) firstIndex: " + e.getFirstIndex() +
            " lastIndex: " + e.getLastIndex() + "\n");
  }
}
