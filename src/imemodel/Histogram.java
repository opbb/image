package imemodel;

public interface Histogram extends ImageModel {

  int[] getRedData(String name);

  int[] getGreenData(String name);

  int[] getBlueData(String name);

  int[] getIntensityData(String name);


}
