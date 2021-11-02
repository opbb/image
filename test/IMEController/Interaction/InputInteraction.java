package IMEController.Interaction;

/**
 * Represents a user providing the program with  an input.
 */
class InputInteraction implements Interaction {
  String input;

  /**
   * Basic constructor.
   * @param input is the simulated input for the given interaction
   */
  public InputInteraction(String input) {
    this.input = input;
  }

  @Override
  public void apply(StringBuilder in, StringBuilder out) {
    in.append(input);
  }
}


