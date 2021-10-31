package IMEController;

import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

import IMEController.ICommand.ICommand;
import IMEModel.ImageModel;
import IMEView.IMEView;

/**
 * A basic implementation of IMEController. Loops until told to quit, parsing user input and
 * carrying out script commands.
 */
public class IMEControllerImpl implements IMEController{

  private final Map<String, ICommand> commands;
  private final ImageModel model;
  private final IMEView view;
  private final Scanner sc;

  /**
   * A basic constructor for the IMEControllerImpl. Takes input from System.in.
   * @param commands the commands that this controller can use
   * @param model the model that this controller will control
   * @param view the view that this controller will use to display things
   */
  public IMEControllerImpl(Map<String, ICommand> commands, ImageModel model, IMEView view) {
    this.commands = commands;
    this.model = model;
    this.view = view;
    this.sc = new Scanner(new InputStreamReader(System.in));
  }

  /**
   * A testing constructor for the IMEControllerImpl. Takes input from the given readable.
   * @param commands the commands that this controller can use
   * @param model the model that this controller will control
   * @param view the view that this controller will use to display things
   */
  public IMEControllerImpl(Map<String, ICommand> commands, ImageModel model,
                           IMEView view, Readable readable) {
    this.commands = commands;
    this.model = model;
    this.view = view;
    this.sc = new Scanner(readable);
  }

  @Override
  public void run() {

    while(true) {
      view.renderMessage("Input new command: ");

      String input = sc.next().toLowerCase(); // Input is converted to lowercase for easier parsing.
      // Block below check for the quit command.
      if(input.equals("q") || input.equals("quit")) { // "q" or "quit" quits the program.
        break; // Breaks loop to end program.
      }

      // Block below checks for the help command
      else if(input.equals("help") || input.equals("\"help\"")) { // "help" or ""help"" for help.
        view.renderMessage("\nList of commands:\n\n"); // Simple spaced header.
        for (ICommand command : commands.values()) {
          view.renderMessage(command.helpMessage() + "\n\n"); // Prints help msg for all commands.
        }
      }

      // Block below check for all other commands.
      else {
        boolean executedCommand = false; // Boolean flag so that we know if we executed or not.
        for (Map.Entry<String,ICommand> entry : commands.entrySet()) {
          if (entry.getKey().equals(input)) {
            entry.getValue().execute(model, view, sc);
            executedCommand = true; // Record that we have executed.
            break; // Breaks loop so that we don't waste energy checking the remaining commands.
          }
        }

        // Block below check for if the given command was invalid (i.e. no command was executed).
        if (!executedCommand) {
          view.renderMessage("Couldn't recognize the inputted command, " +
                  "input \"help\" for a list of available commands.\n\n");
        }
      }
    }
  }
}