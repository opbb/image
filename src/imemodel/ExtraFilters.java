package imemodel;

import java.util.Map;

public interface ExtraFilters extends ImageModel {

  void blur(String name);

  void sharpen(String name);

  void toSepia(String name);


}
