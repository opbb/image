package imecontroller;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Map;
import java.util.Scanner;

import imecontroller.icommand.ICommand;
import imemodel.ImageModel;
import imeview.IMEView;

/**
 * A basic implementation of IMEController. Loops until told to quit, parsing user input and
 * carrying out script commands.
 */
public class IMEFileController implements IMEController {

  private final Map<String, ICommand> commands;
  private final ImageModel model;
  private final IMEView view;
  private final String[] lines;

  /**
   * A basic constructor for the IMEFileController. Takes input from the text file at _filePath_.
   *
   * @param commands the commands that this controller can use
   * @param model    the model that this controller will control
   * @param view     the view that this controller will use to display things
   */
  public IMEFileController(Map<String, ICommand> commands, ImageModel model, IMEView view,
                           String filePath) {
    this.commands = commands;
    this.model = model;
    this.view = view;
    try {
      this.lines = processInputToLines(new InputStreamReader(new FileInputStream(filePath)));
    } catch (IOException e) {
      throw new IllegalArgumentException("Couldn't find a file at" + filePath + ".");
    }
  }

  /**
   * A testing constructor for the IMEFileController. Takes input from the given readable.
   *
   * @param commands the commands that this controller can use
   * @param model    the model that this controller will control
   * @param view     the view that this controller will use to display things
   */
  public IMEFileController(Map<String, ICommand> commands, ImageModel model,
                           IMEView view, Readable readable) {
    this.commands = commands;
    this.model = model;
    this.view = view;
    this.lines = processInputToLines(readable);
  }

  @Override
  public void run() {
    for (int ln = 1; ln < (lines.length + 1); ln++) {
      if (lines[ln].equals("")) {
        continue;
      } // If line is empty, skip it.
      Scanner sc = new Scanner(new StringReader(lines.toString())); // A scanner for just this line.

      // Input is converted to lowercase for easier parsing.
      String input = InputUtils.getStringInput(sc).toLowerCase();

      boolean executedCommand = false; // Boolean flag so that we know if we executed or not.
      for (Map.Entry<String, ICommand> entry : commands.entrySet()) {
        if (entry.getKey().equals(input)) {
          entry.getValue().execute(model, view, sc);
          executedCommand = true; // Record that we have executed.
          break; // Breaks loop so that we don't waste energy checking the remaining commands.
        }
      }

      // Block below check for if the given command was invalid (i.e. no command was executed).
      if (!executedCommand) {
        view.renderMessage("Couldn't recognize the command, \"" + input + "\", " +
                "in line " + ln + ".\n");
      }
    }
  }

  /**
   * Processes a given readable into an array of lines, with comments set to empty strings.
   *
   * @param inputFile the readable to be processed
   * @return an array of all lines not starting with "#".
   */
  private String[] processInputToLines(Readable inputFile) {
    String inputString = inputFile.toString();
    String[] inputLines = inputString.split("\n");
    for (int i = 0; i < inputLines.length; i++) {
      if (!inputLines[i].equals("") && inputLines[i].charAt(0) == '#') {
        inputLines[i] = "";
      }
    }
    return inputLines;
  }
}
