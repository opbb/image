package IMEModel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the model in the M,V,C format, and implements the ImageModel interface.
 * It is responsible for several actions, some simple such as returning the rgb values of all
 * the pixels and loading the image. Along with brightening or darkening, along with flipping the
 * image.
 */
public class ImageModelImpl implements ImageModel {

  // Image object
//  private Image image;
  private Map<String, Image> map;

  //every method needs a string input

  /**
   * Defualt constructor for test cases.
   */
  public ImageModelImpl() {
    this.map = new HashMap<String, Image>();
  }

  /**
   * Constructor for manipulating a file that will be taken in as a String.
   *
   * @param filename the file path.
   */
  public ImageModelImpl(String filename, String filepath) {
    this.map = new HashMap<String, Image>();
    loadImage(filename, new ImageImpl(filepath));
  }

  public ImageModelImpl(Image image) {
    this.map = new HashMap<String, Image>();
  }

  @Override
  public int[][][] getImageValues(String name) {
    Image image = map.get(name);
    int[][][] values = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        values[i][j] = image.getPixels()[i][j];
      }
    }
    return values;
  }


  //check null
  public void loadImage(String name, Image image) {
    if (name == null || image == null) {
      throw new IllegalArgumentException("Name or image cannot be null!");
    }
    map.put(name, image);
  }

  public void duplicateImage(String name, String newName) {
    loadImage(newName, map.get(name));
  }

  public Image getImage(String name) {
    return map.get(name);
  }


  @Override
  public void greyscaleByLuma(String name) {
    double[][] greyscale = {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}, {0.2126,
            0.7152, 0.0722}};
    map.replace(name, Application.applyMultipliedEffect(map.get(name), greyscale));
  }


  @Override
  public void brighten(String name, double increase) {
    map.replace(name, Application.applyAddedEffect(map.get(name), increase));
  }


  @Override
  public void flipVertical(String name) {

    int[][][] pixels = map.get(name).getPixels();
    int width = map.get(name).getWidth();
    int height = map.get(name).getHeight();

    for (int i = 0; i < height / 2; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = pixels[height - i - 1][j];
      }
    }

    map.get(name).setPixels(pixels);
  }


  @Override
  public void flipHorizontal(String name) {
    int[][][] pixels = map.get(name).getPixels();
    int width = map.get(name).getWidth();
    int height = map.get(name).getHeight();


    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width / 2; j++) {
        pixels[i][j] = pixels[i][width - j - 1];
      }
    }

    map.get(name).setPixels(pixels);

    }

  @Override
  public String toString() {
    String str = "";
    for(Map.Entry<String, Image> entry : map.entrySet()) {
      str.concat(entry.getKey() + "\n"
              + Arrays.deepToString(entry.getValue().getPixels()) + "\n\n");
    }
    return str;
  }
}
//  @Override
//  public String toString() {
//    StringBuilder str = new StringBuilder();
//    str.append("P3\n");
//    str.append(image.getWidth()).append(" ").append(image.getHeight()).append("\n");
//    str.append("255").append("\n");
//
////      str.append("255\n");
//    for (int i = 0; i < image.getHeight(); i++) {
//      for (int j = 0; j < image.getWidth(); j++) {
//        int[] vals = getImageValues()[i][j];
//        str.append(vals[0]).append(" ").append(vals[1]).append(" ").append(vals[2]).append(" ");
//      }
//      str.append("\n");
//    }
//    return str.toString();
//  }
//}
