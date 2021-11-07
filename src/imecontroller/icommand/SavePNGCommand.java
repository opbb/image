//<<<<<<< HEAD
////package imecontroller.icommand;
////
////import java.io.IOException;
////import java.net.URLConnection;
////import java.nio.file.FileSystems;
////import java.nio.file.Files;
////import java.util.Scanner;
////
////import imemodel.Formats;
////import imemodel.Image;
////import imemodel.ImageModel;
////import imemodel.ImageUtil;
////import imeview.IMEView;
////
////public class SavePNGCommand extends AbstractCommand {
////
////  @Override
////  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
////    String fromImage = getStringInput(sc);
////    String fileName = getStringInput(sc);
////
////
////    Image image = model.getImage(fromImage);
////
////    try {
////      if (image != null && (fileName.substring(fileName.lastIndexOf(".") + 1)).equals("ppm")) {
////
////        ImageUtil.writePPM(image, fileName);
////      }
////      else if (!(fileName.substring(fileName.lastIndexOf(".") + 1)).equals("ppm")) {
////        Formats.writeImageFile(image, fileName);
////      }
////      else {
////        view.renderMessage("The image " + fromImage + " is null.\n\n");
////      }
////    } catch (IOException e) {
////      view.renderMessage("The image cannot be saved to " + fileName + ".\n\n");
////    }
////  }
////
////  @Override
////  public String helpMessage() {
////    return "save [image to save] [file name to save as]";
////  }
////
////  @Override
////  public String commandText() {
////    return "save";
////  }
////}
////
////
//=======
//package imecontroller.icommand;
//
//import java.io.IOException;
//import java.net.URLConnection;
//import java.nio.file.FileSystems;
//import java.nio.file.Files;
//import java.util.Scanner;
//
//import imemodel.Formats;
//import imemodel.Image;
//import imemodel.ImageModel;
//import imemodel.ImageUtil;
//import imeview.IMEView;
//
//public class SavePNGCommand extends AbstractCommand {
//
//  @Override
//  public void execute(ImageModel model, IMEView view, Scanner sc) throws IllegalStateException {
//    String fromImage = getStringInput(sc);
//    String fileName = getStringInput(sc);
//
//    String extension = fileName.substring(0, fileName.lastIndexOf("."));
//    Image image = model.getImage(fromImage);
//
//    try {
//      boolean isPPM = fileName.substring(fileName.lastIndexOf(".") + 1) == "ppm";
//      if (image != null && isPPM) {
//
//        ImageUtil.writePPM(image, fileName);
//      }
//      else if (!isPPM) {
//        Formats.writeImageFile(image, fileName);
//      }
//      else {
//        view.renderMessage("The image " + fromImage + " is null.\n\n");
//      }
//    } catch (IOException e) {
//      view.renderMessage("The image cannot be saved to " + fileName + ".\n\n");
//    }
//  }
//
//  @Override
//  public String helpMessage() {
//    return "save-png [image to save] [file name to save as]";
//  }
//
//  @Override
//  public String commandText() {
//    return "save-png";
//  }
//}
//
//
//>>>>>>> 8ce38cc35a386e9427c63c38da53ac9663c3f047
