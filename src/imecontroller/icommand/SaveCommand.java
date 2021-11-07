package imecontroller.icommand;

import java.io.IOException;
<<<<<<< Updated upstream
import java.net.URLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Files;
=======
import java.util.Map;
>>>>>>> Stashed changes
import java.util.Scanner;

import imemodel.Formats;
import imemodel.Image;
import imemodel.ImageModel;
import imemodel.ImageUtil;
import imeview.IMEView;

public class SaveCommand extends AbstractCommand {

  @Override
  public void execute(ImageModel model, IMEView view, Scanner sc, Map<String, ICommand> commands) throws IllegalStateException {
    String fromImage = getStringInput(sc);
    String fileName = getStringInput(sc);


    Image image = model.getImage(fromImage);

    try {
      if (image != null && (fileName.substring(fileName.lastIndexOf(".") + 1)).equals("ppm")) {

        ImageUtil.writePPM(image, fileName);
      }
      else if (!(fileName.substring(fileName.lastIndexOf(".") + 1)).equals("ppm")) {
        Formats.writeImageFile(image, fileName);
      }
      else {
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


