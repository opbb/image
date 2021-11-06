package imemodel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class represents the model in the M,V,C format, and implements the ImageModel interface.
 * It is responsible for several actions, some simple such as returning the rgb values of all
 * the pixels and loading the image. Along with brightening or darkening, along with flipping the
 * image.
 */
public class ImageModelImpl implements ImageModel {


  // A map containing a String as its keys and Image objects as its values. This allows for ease
  // of use in loading multiple images and setting their keys to be accessible by different names.
  private final Map<String, Image> map;


  /**
   * Default constructor for test cases, that takes in no parameters, but initializes the hashmap.
   */
  public ImageModelImpl() {
    this.map = new HashMap<String, Image>();
  }


  /**
   * This constructor takes in a filename, as to what the key within the map will be addressed by,
   * along with a filepath to create the image object set by this filepath, acting as the value
   * within the map.
   *
   * @param filename the file name as to be addressed by within the hashmap.
   * @param filepath the file path used to make the image object.
   * @throws IllegalArgumentException if the filename or filepath are empty or null.
   */
  public ImageModelImpl(String filename, String filepath) throws IllegalArgumentException {
    if (filename == null || filename.equals("") || filepath.equals("") || filepath == null) {
      throw new IllegalArgumentException("The given filename or filepath do not exist or must not" +
              "be null!");
    }
    this.map = new HashMap<String, Image>();
    loadImage(filename, new ImageImpl(filepath));
  }

  /**
   * A 3rd constructor used for testing, takes in an Image object, but not a String as to be loaded
   * by the tester.
   *
   * @param image an image object.
   * @throws IllegalArgumentException if the image object is null.
   */
  public ImageModelImpl(Image image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("The given image cannot be null!");
    }
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


  @Override
  public void loadImage(String name, Image image) {
    if (name == null || image == null) {
      throw new IllegalArgumentException("Name or image cannot be null!");
    }
    map.put(name, image);
  }

  @Override
  public void duplicateImage(String name, String newName) {
    loadImage(newName, map.get(name));
  }

  @Override
  public Image getImage(String name) {
    return map.get(name);
  }

  @Override
  public boolean hasImage(String name) {
    for (String key : map.keySet()) {
      if (key.equals(name)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void greyscaleByLuma(String name) {
    double[][] greyscale = {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}, {0.2126,
            0.7152, 0.0722}};
    map.replace(name, Application1.applyMultipliedEffect(map.get(name), greyscale));
  }

  @Override
  public void getByComponent(String name, String comp) {
    map.replace(name, Application1.applySetEffect(map.get(name), comp));
  }


  @Override
  public void brighten(String name, double increase) {
    map.replace(name, Application1.applyAddedEffect(map.get(name), increase));
  }


  @Override
  public void flipVertical(String name) {

    int[][][] pixels = map.get(name).getPixels();
    int width = map.get(name).getWidth();
    int height = map.get(name).getHeight();
    int[] temp;

    for (int i = 0; i < height / 2; i++) {
      for (int j = 0; j < width; j++) {
        temp = map.get(name).getPixels()[i][j];
        pixels[i][j] = pixels[height - i - 1][j];
        pixels[height - i - 1][j] = temp;
      }
    }

    map.get(name).setPixels(pixels);
  }

  @Override
  public void closeImage(String name) {
    map.remove(name);
  }


  @Override
  public void flipHorizontal(String name) {

    int[][][] pixels = map.get(name).getPixels();
    int width = map.get(name).getWidth();
    int height = map.get(name).getHeight();
    int[] temp;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width / 2; j++) {
        temp = map.get(name).getPixels()[i][j];
        pixels[i][j] = pixels[i][width - j - 1];
        pixels[i][width - j - 1] = temp;
      }
    }
    map.get(name).setPixels(pixels);
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    for (Map.Entry<String, Image> entry : map.entrySet()) {
      str.append(entry.getKey()).append("\n").append(Arrays.deepToString(entry.getValue()
              .getPixels())).append("\n\n");
    }
    return str.toString();
  }

  @Override
  public Set<String> getKeys() {
    return map.keySet();
  }
}

