package imecontroller.icommand;

import java.io.IOException;
import java.util.Scanner;

import imemodel.Formats;
import imemodel.Image;
import imemodel.ImageModel;
import imeview.IMEView;

public class SaveJPEGCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String fileName = getStringInput(sc);
    Image image = model.getImage(fromImage);

    try {
      if (image != null) {
        Formats.writeJPEG(image, fileName);
      } else {
        view.renderMessage("The image " + fromImage + " is null.\n\n");
      }
    } catch (IOException e) {
      view.renderMessage("The image cannot be saved to " + fileName + ".\n\n");
    }
  }

  @Override
  public String helpMessage() {
    return "save-jpeg [image to save] [file name to save as]";
  }

  @Override
  public String commandText() {
    return "save-jpeg";
  }
}


