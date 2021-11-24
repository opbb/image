package imemodel;


/**
 * This interface acts as an extended object of ImageModel, in which one public facing method is
 * added to our HistogramImpl class (getHistogramData). This was needed in abiding by SOLID
 * principles along with the requirement of a histogram within this assignment.
 */
public interface Histogram extends ImageModel {

  /**
   * Produces a 2d Integer array of the histogram data of the red, green, blue, and intensity values
   * of each pixel within an image.
   * @param pixels the 3d array representing the image's pixels.
   * @return a 2d integer array of histogram data of each pixels' RGB and intensity values.
   */
  int[][] getHistogramData(int[][][] pixels);




}
