package imecontroller;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
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
    if (commands == null || model == null || view == null || filePath == null) {
      throw new IllegalArgumentException("Inputs must not be null.");
    }
    this.commands = commands;
    this.model = model;
    this.view = view;
    try {
      this.lines = processInputToLines(new Scanner(new InputStreamReader(
              new FileInputStream(filePath))));
    } catch (IOException e) {
      throw new IllegalArgumentException("Couldn't find a file at " + filePath + ".");
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
    if (commands == null || model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Inputs must not be null.");
    }
    this.commands = commands;
    this.model = model;
    this.view = view;
    this.lines = processInputToLines(new Scanner(readable));
  }

  @Override
  public void run() {
    for (int ln = 0; ln < lines.length; ln++) {
      if (lines[ln].equals("")) {
        continue;
      } // If line is empty, skip it.
      Scanner sc = new Scanner(new StringReader(lines[ln])); // A scanner for just this line.

      // Input is converted to lowercase for easier parsing.
      String input = InputUtils.getStringInput(sc).toLowerCase();

      boolean executedCommand = false; // Boolean flag so that we know if we executed or not.
      for (Map.Entry<String, ICommand> entry : commands.entrySet()) {
        if (entry.getKey().equals(input)) {
          entry.getValue().execute(model, view, sc, commands);
          executedCommand = true; // Record that we have executed.
          view.renderMessage("Ran " + entry.getKey() + " command.\n");
          break; // Breaks loop so that we don't waste energy checking the remaining commands.
        }
      }

      // Block below check for if the given command was invalid (i.e. no command was executed).
      if (!executedCommand) {
        view.renderMessage("Couldn't recognize the command, \"" + input + "\", " +
                "in line " + (ln + 1) + ".\n");
      }
    }
  }

  /**
   * Processes a given scanner into an array of lines, with comments set to empty strings.
   *
   * @param sc the scanner containing the file to be processed
   * @return an array of all lines not starting with "#".
   */
  private String[] processInputToLines(Scanner sc) {
    ArrayList<String> lines = new ArrayList<>();
    while (sc.hasNextLine()) {
      String[] rawLine = sc.nextLine().split(" ");
      String line = "";
      for (int i = 0; i < rawLine.length; i++) { // Removes excess spaces.
        if (!rawLine[i].equals(" ") && !rawLine[i].equals("")) {
          line += rawLine[i];
          if (i < rawLine.length) {line += " ";}
        }
      }
      if (!line.equals("") && line.charAt(0) == '#') {
        line = "";
      }
      lines.add(line);
    }
    return lines.toArray(new String[0]);
  }
}
