package imecontroller.iguicommand;

import java.io.IOException;
import java.util.Map;

import imecontroller.icommand.ICommand;
import imemodel.Formats;
import imemodel.Image;
import imemodel.ImageModel;
import imemodel.ImageUtil;

/**
 * This class represents the save feature of our program, in which it saves the image to its
 * given destination, in using the respective methods to do so, depending the on the MIME type of
 * the filename.
 */
public class SaveGUICommand extends AbstractGUICommand {

  @Override
  public void execute(ImageModel model, IMEGUIView view, Map<String, ICommand> commands)
          throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String fileName = getStringInput(sc);


    Image image = model.getImage(fromImage);

    try {
      if (image != null) {
        if (fileName.substring(fileName.lastIndexOf(".") + 1).equals("ppm")) {
          ImageUtil.writePPM(image, fileName);
        } else {
          Formats.writeImageFile(image, fileName);
        }
      } else {
        view.renderMessage("The image " + fromImage + " is null.\n\n");
      }
    } catch (IOException e) {
      view.renderMessage("The image cannot be saved to " + fileName + ".\n\n");
    }
  }

  @Override
  public String helpMessage() {
    return "save [image to save] [file name to save as]";
  }

  @Override
  public String commandText() {
    return "save";
  }
}


