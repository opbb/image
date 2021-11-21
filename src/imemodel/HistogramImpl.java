package imemodel;

import java.util.Set;

public class HistogramImpl implements Histogram {

  private final ImageModel delegate;

  public HistogramImpl(ImageModel model) {
    this.delegate = model;
  }

  @Override
  public int[] getRedData(String name) {

      int[] data = new int[256];
      int[][][] pixels = delegate.getImageValues(name);
      int width = pixels[0].length;
      int height = pixels.length;


      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          for (int k = 0; k < 256; k++) {
            if (pixels[i][j][0] == k) {
              data[k]++;
            }
          }
        }
      }
      return data;
    }


  @Override
  public int[] getGreenData(String name) {
    int[] data = new int[256];
    int[][][] pixels = delegate.getImageValues(name);
    int width = pixels[0].length;
    int height = pixels.length;


    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 256; k++) {
          if (pixels[i][j][1] == k) {
            data[k]++;
          }
        }
      }
    }
    return data;
  }


  @Override
  public int[] getBlueData(String name) {
    int[] data = new int[256];
    int[][][] pixels = delegate.getImageValues(name);
    int width = pixels[0].length;
    int height = pixels.length;


    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 256; k++) {
          if (pixels[i][j][2] == k) {
            data[k]++;
          }
        }
      }
    }
    return data;
  }

  @Override
  public int[] getIntensityData(String name) {
    int[] data = new int[256];
    int[][][] pixels = delegate.getImageValues(name);
    int width = pixels[0].length;
    int height = pixels.length;


    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 256; k++) {
          if (getIntensity(pixels[i][j]) == k) {
            data[k]++;
          }
        }
      }
    }
    return data;
  }


  private int getIntensity(int[] arr) {

    return (int) ((arr[0] + arr[1] + arr[2]) / 3);

  }

  @Override
  public int[][][] getImageValues(String name) {
    return new int[0][][];
  }

  @Override
  public void loadImage(String name, Image image) {

  }

  @Override
  public void duplicateImage(String name, String newName) {

  }

  @Override
  public Image getImage(String name) {
    return null;
  }

  @Override
  public boolean hasImage(String name) {
    return false;
  }

  @Override
  public void getByComponent(String name, String comp) {

  }

  @Override
  public void brighten(String name, double increase) {

  }

  @Override
  public void closeImage(String name) {

  }

  @Override
  public void flipVertical(String name) {

  }

  @Override
  public void flipHorizontal(String name) {

  }

  @Override
  public void greyscaleByLuma(String name) {

  }

  @Override
  public Set<String> getKeys() {
    return null;
  }
}
