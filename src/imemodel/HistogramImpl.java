package imemodel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.imageio.ImageIO;

public class HistogramImpl implements Histogram {

  private final ImageModel delegate;

  public HistogramImpl(ImageModel model) {
    this.delegate = model;
  }

  @Override
  public int[][] getHistogramData(String name) {


    int[][][] image = delegate.getImageValues(name);
    int[][] result = new int[256][4];
    int red = 0;
    int blue = 0;
    int green = 0;
    int intensity = 0;


    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {

        blue = image[i][j][2];
        green = image[i][j][1];
        red = image[i][j][0];
        intensity = ((blue + green + red) / 3);
        result[red][0]++;
        result[green][1]++;
        result[blue][2]++;
        result[intensity][3]++;
      }


    }
    return result;
  }
//  }Image currImage = new ImageImpl(imageValues);
//  BufferedImage img;
//  int width = currImage.getWidth();
//  int height = currImage.getHeight();
//  int[][][] pixels = currImage.getPixels();
//  img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//    if (name.substring(name.lastIndexOf(".") + 1).equals("ppm")) {
//
//
//
//    for (int k = 0; k < height; k++) {
//      for (int p = 0; p < width; p++) {
//
//        int col = new Color(pixels[k][p][0], pixels[k][p][1], pixels[k][p][2]).getRGB();
//        img.setRGB(p, k, col);
//
//      }
//    }
//    return img;
//  }
//    else if (name.substring(name.lastIndexOf(".") + 1).equals("bmp")) {
//    for (int i = 0; i < height; i++) {
//      for (int j = 0; j < width; j++) {
//        img.setRGB(j, i, (currImage.getPixels()[i][j][0] << 16) |
//                (currImage.getPixels()[i][j][1] << 8) | currImage.getPixels()[i][j][2]);
//      }
//    }
//    return img;
//
//
//  }
//    else {
//    for (int k = 0; k < height; k++) {
//      for (int p = 0; p < width; p++) {
//
//        int col = new Color(pixels[k][p][0], pixels[k][p][1], pixels[k][p][2]).getRGB();
//        img.setRGB(p, k, col);
//
//      }
//    }
//    return img;
//  }
//}



  @Override
  public int[][][] getImageValues(String name) {
    return delegate.getImageValues(name);
  }

  @Override
  public void loadImage(String name, Image image) {

    delegate.loadImage(name, image);
  }

  @Override
  public void duplicateImage(String name, String newName) {

    delegate.duplicateImage(name, newName);
  }

  @Override
  public Image getImage(String name) {
    return delegate.getImage(name);
  }

  @Override
  public boolean hasImage(String name) {
    return delegate.hasImage(name);
  }

  @Override
  public void getByComponent(String name, String comp) {

    delegate.getByComponent(name, comp);
  }

  @Override
  public void brighten(String name, double increase) {

    delegate.brighten(name, increase);
  }

  @Override
  public void closeImage(String name) {

    delegate.closeImage(name);
  }

  @Override
  public void flipVertical(String name) {
    delegate.flipVertical(name);

  }

  @Override
  public void flipHorizontal(String name) {

    delegate.flipHorizontal(name);
  }

  @Override
  public void greyscaleByLuma(String name) {

    delegate.greyscaleByLuma(name);
  }

  @Override
  public Set<String> getKeys() {
    return delegate.getKeys();
  }
}
