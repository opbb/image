package imecontroller.icommand;

import imemodel.ImageModel;

/**
 * This interface is used as a lambda to apply effects with our model in using a masked image.
 */
public interface MethodCaller {

  /**
   * This method is used as the lambda operator for our masked image commands.
   * @param model The model by which we can derive an effect from.
   */
  void execute(ImageModel model);

}
