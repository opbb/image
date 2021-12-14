package imemodel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    return map.get(name).getPixels();
//    int[][][] values = new int[image.getHeight()][image.getWidth()][3];
//    for (int i = 0; i < image.getHeight(); i++) {
//      for (int j = 0; j < image.getWidth(); j++) {
//        values[i][j] = image.getPixels()[i][j];
//      }
//    }
//    return values;
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
    loadImage(newName, new ImageImpl(getImageValues(name)));
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
    map.replace(name, Application.applyMultipliedTransformation(map.get(name), greyscale));
  }

  @Override
  public void getByComponent(String name, String comp) {
    map.replace(name, Application.applySetEffect(map.get(name), comp));
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
    int[] temp;

    for (int i = 0; i < height / 2; i++) {
      for (int j = 0; j < width; j++) {
        temp = map.get(name).getPixels()[i][j];
        pixels[i][j] = pixels[height - i - 1][j];
        pixels[height - i - 1][j] = temp;
      }
    }

    map.get(name).setPixels(pixels);

    map.replace(name, map.get(name));

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


  @Override
  public void resize(int newHeight, int newWidth, String imageName) {
    int[][][] rs = new int[newHeight][newWidth][3];
    double xRatio = (double) map.get(imageName).getWidth() / newWidth;
    double yRatio = (double) map.get(imageName).getHeight() / newHeight;
    double newX;
    double newY;
    int pixelR;
    int pixelG;
    int pixelB;
    for (int i = 0; i < newHeight; i++) {
      for (int j = 0; j < newWidth; j++) {
        newX = (j * xRatio);
        newY = (i * yRatio);

        pixelR = fixColors(imageName, newY, newX, 0);
        pixelG = fixColors(imageName, newY, newX, 1);
        pixelB = fixColors(imageName, newY, newX, 2);
        rs[i][j][0] = pixelR;
        rs[i][j][1] = pixelG;
        rs[i][j][2] = pixelB;

      }

    }
    map.get(imageName).setPixels(rs);
    map.get(imageName).setHeight(newHeight);
    map.get(imageName).setWidth(newWidth);
    map.replace(imageName, map.get(imageName));
  }

  /**
   * This method fixes the colors of the pixels of a resized image using algebra given within the
   * assignment.
   *
   * @param name    the name of the image to fix the pixels of.
   * @param x       the x coordinate of a pixel.
   * @param y       the y coordiante of a pixel.
   * @param channel the channel of the pixel, either red, green, or blue.
   * @return An Integer that has been verified and correctly represented within the newly downsized
   * image.
   */
  private int fixColors(String name, double x, double y, int channel) {

    Image image = map.get(name);

    if (Math.floor(x) == Math.ceil(x) || Math.floor(y) == Math.ceil(y)) {
      return image.getPixels()[(int) x][(int) y][channel];
    } else {

      int a = image.getPixels()[(int) Math.floor(x)][(int) Math.floor(y)][channel];
      int b = image.getPixels()[(int) Math.ceil(x)][(int) Math.floor(y)][channel];
      int c = image.getPixels()[(int) Math.floor(x)][(int) Math.ceil(y)][channel];
      int d = image.getPixels()[(int) Math.ceil(x)][(int) Math.ceil(y)][channel];

      double m = (b * (x - Math.floor(x))) + (a * (Math.ceil(x) - x));
      double n = (d * (x - Math.floor(x))) + (c * (Math.ceil(x) - x));

      double p = (n * (y - Math.floor(y))) + (m * (Math.ceil(y) - y));

      return (int) p;
    }
  }

  @Override
  public void maskedImagify(String name, String maskedImage) {
    Image main = map.get(name);
    Image masked = map.get(maskedImage);


    List<Integer> col = getCols(maskedImage, masked);
    List<Integer> row = getRows(maskedImage, masked);

    int[][][] mk = new int[col.size()][row.size()][3];
    for (int k = 0; k < col.size(); k++) {
      for (int t = 0; t < row.size(); t++) {
        mk[k][t] = main.getPixels()[col.get(k)][row.get(t)];

      }
    }

    if (hasImage("masked-copy")) {
      map.replace("masked-copy", new ImageImpl(mk));
    } else {
      map.put("masked-copy", new ImageImpl(mk));
    }
  }

  /**
   * This method calculates the rows of a masked image's black pixels.
   *
   * @param maskedName the main image to get a buffered image for.
   * @param masked     the masked copy to read through
   * @return a list of x coordinates for the masked image' black pixels.
   */
  private List<Integer> getRows(String maskedName, Image masked) {

    List<Integer> row = new ArrayList<Integer>();

    BufferedImage bf = Formats.makeBF(maskedName, masked.getPixels());
    for (int i = 0; i < masked.getHeight(); i++) {
      for (int j = 0; j < masked.getWidth(); j++) {
        int pixel = bf.getRGB(j, i);
        if ((pixel & 0x00FFFFFF) == 0) {
          row.add(j);
        }
      }
    }
    return row;
  }

  /**
   * This method calculates the columns of a masked image's black pixels.
   *
   * @param maskedName the main image to get a buffered image for.
   * @param masked     the masked copy to read through
   * @return a list of y coordinates for the masked image' black pixels.
   */
  private List<Integer> getCols(String maskedName, Image masked) {

    List<Integer> col = new ArrayList<Integer>();

    BufferedImage bf = Formats.makeBF(maskedName, masked.getPixels());
    for (int i = 0; i < masked.getHeight(); i++) {
      for (int j = 0; j < masked.getWidth(); j++) {
        int pixel = bf.getRGB(j, i);
        if ((pixel & 0x00FFFFFF) == 0) {
          col.add(i);

        }
      }
    }
    return col;
  }


  @Override
  public void applyMaskedChanges(String name, String maskedImage) {
    Image main = map.get(name);
    Image masked = map.get(maskedImage);
    Image maskedCopy = map.get("masked-copy");

    List<Integer> col = getCols(maskedImage, masked);
    List<Integer> row = getRows(maskedImage, masked);
    int colNum = col.size();
    int rowNum = row.size();
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        main.getPixels()[col.get(i)][row.get(j)] = maskedCopy.getPixels()[i][j];
      }
    }

    map.replace(name, main);
  }
}

