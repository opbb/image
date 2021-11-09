package imecontroller;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import imecontroller.icommand.ICommand;
import imemodel.ImageModel;
import imeview.IMEView;

/**
 * A basic implementation of IMEController. Loops until told to quit, parsing user input and
 * carrying out script commands.
 */
public class IMEControllerImpl implements IMEController {

  private final Map<String, ICommand> commands;
  private final ImageModel model;
  private final IMEView view;
  private final Scanner sc;

  /**
   * A basic constructor for the IMEControllerImpl. Takes input from System.in.
   *
   * @param commands the commands that this controller can use
   * @param model    the model that this controller will control
   * @param view     the view that this controller will use to display things
   */
  public IMEControllerImpl(Map<String, ICommand> commands, ImageModel model, IMEView view) {
    if (commands == null || model == null || view == null) {
      throw new IllegalArgumentException("Inputs must not be null.");
    }
    this.commands = commands;
    this.model = model;
    this.view = view;
    this.sc = new Scanner(new InputStreamReader(System.in));
  }

  /**
   * A testing constructor for the IMEControllerImpl. Takes input from the given readable.
   *
   * @param commands the commands that this controller can use
   * @param model    the model that this controller will control
   * @param view     the view that this controller will use to display things
   */
  public IMEControllerImpl(Map<String, ICommand> commands, ImageModel model,
                           IMEView view, Readable readable) {
    if (commands == null || model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Inputs must not be null.");
    }
    this.commands = commands;
    this.model = model;
    this.view = view;
    this.sc = new Scanner(readable);
  }

  @Override
  public void run() {

    while (true) {
      view.renderMessage(view + "\n\nInput new command: ");

      // Input is converted to lowercase for easier parsing.
      String input = InputUtils.getStringInput(sc).toLowerCase();

      // Block below check for the quit command.
      if (input.equals("q") || input.equals("quit")) { // "q" or "quit" quits the program.
        view.renderMessage("You have quit the program.");
        break; // Breaks loop to end program.
      }

      // Block below checks for the help command
      else if (input.equals("help") || input.equals("\"help\"")) { // "help" or ""help"" for help.
        ArrayList<String> toSort = new ArrayList<String>(commands.keySet());
        java.util.Collections.sort(toSort);
        view.renderMessage("\nList of commands:\n\n"); // Simple spaced header.
        for (String key : toSort) {
          //Prints help msg for all commands.
          view.renderMessage(commands.get(key).helpMessage() + "\n");
        }
        view.renderMessage("\nq or quit to quit the program\n\n");
      }

      // Block below check for all other commands.
      else {
        boolean executedCommand = false; // Boolean flag so that we know if we executed or not.
        for (String key : commands.keySet()) {
          if (key.equals(input)) {
            commands.get(key).execute(model, view, sc, commands);
            executedCommand = true; // Record that we have executed.
            break; // Breaks loop so that we don't waste energy checking the remaining commands.
          }
        }

        // Block below check for if the given command was invalid (i.e. no command was executed).
        if (!executedCommand) {
          view.renderMessage("Couldn't recognize the inputted command \"" + input + "\", " +
                  "input \"help\" for a list of available commands.\n\n");
        }
      }
    }
  }

  /**
   * Returns the inputted String, or throws an exception if there are issues with the scanner.
   *
   * @param sc the scanner from which inputs are read
   * @return the inputted string
   * @throws IllegalStateException if there are no more inputs or the scanner is closed
   */
  protected String getStringInput(Scanner sc) throws IllegalStateException {
    try {
      return sc.next();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("Ran out of inputs.");
    }
  }
}
