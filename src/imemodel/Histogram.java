package imemodel;

import java.awt.image.BufferedImage;

public interface Histogram extends ImageModel {

  int[][] getHistogramData(int[][][] pixels);




}
