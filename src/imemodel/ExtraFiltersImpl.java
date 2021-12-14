package imemodel;


import java.util.Set;

/**
 * This class resembles the use of code reuse, specifically composition, in supplying the original
 * ImageModelImpl class with new image filters.
 */
public class ExtraFiltersImpl implements ExtraFilters {

  //The delegate object of an ImageModel by which to perform operations on.
  private final ImageModel delegate;

  /**
   * Main constructor which requires an ImageModel object, assigning it as this class' delegate
   * object.
   *
   * @param model the ImageModel object by which to perform the required operations.
   */
  public ExtraFiltersImpl(ImageModel model) {
    this.delegate = model;
  }

  /**
   * Secondary constructor used for possible future needs.
   *
   * @param name     the name of the image as represented by the ImageModel's map.
   * @param filepath the filepath of an image.
   */
  public ExtraFiltersImpl(String name, String filepath) {
    this.delegate = new ImageModelImpl();
    delegate.loadImage(name, new ImageImpl(filepath));
  }


  @Override
  public void blur(String name) {
    double[][] blur = {{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625,
            0.125, 0.0625}};
    delegate.loadImage(name, Application.applyMultipliedEffect(getImage(name), blur));

  }


  @Override
  public void sharpen(String name) {
    double[][] sharpen = {{-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125}, {-0.125, 0.25, 1, 0.25, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125}, {-0.125, -0.125, -0.125, -0.125, -0.125}};
    delegate.loadImage(name, Application.applyMultipliedEffect(getImage(name), sharpen));
  }

  @Override
  public void toSepia(String name) {

    double[][] sepia = {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272,
            0.534, 0.131}};
    delegate.loadImage(name, Application.applyMultipliedTransformation(getImage(name), sepia));
  }


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

  //Did not have to change luma greyscale as its matrix multiplication was already supported in
  //last assignment.
  @Override
  public void greyscaleByLuma(String name) {

    delegate.greyscaleByLuma(name);
  }

  @Override
  public Set<String> getKeys() {
    return delegate.getKeys();
  }

  @Override
  public void resize(int newHeight, int newWidth, String imageName) {
    delegate.resize(newHeight, newWidth, imageName);
  }

  @Override
  public void maskedImagify(String name, String maskedImage) {

    delegate.maskedImagify(name, maskedImage);
  }

  @Override
  public void applyMaskedChanges(String name, String maskedImage) {

    applyMaskedChanges(name, maskedImage);
  }


}
