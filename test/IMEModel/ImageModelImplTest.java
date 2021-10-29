package IMEModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class ImageModelImplTest {

  private ImageModel model1;
  private Image image1;
  private int[][][] list1;

  private ImageModel model2;
  private Image image2;
  private int[][][] list2;

  @Before
  public void setUp() {

    list1 = new int[][][]{{{25, 25, 25}, {50, 75, 100}}};
    image1 = new ImageImpl(list1);
    model1 = new ImageModelImpl(image1);

    list2 = new int[][][]{{{25, 25, 25}, {50, 75, 100}, {205, 210, 160}, {169, 68, 6}}};
    image2 = new ImageImpl(list2);
    model2 = new ImageModelImpl(image2);
  }

  @Test
  public void testValueGreyscale() {
    Image image2 = new ApplicationImpl().applySetEffect(image1, "value");
    model1 = new ImageModelImpl(image2);

    assertEquals("[[[25, 25, 25], [100, 100, 100]]]", Arrays.deepToString(model1
            .getImageValues()));
  }

  @Test
  public void testGetImageValues() {
    assertEquals("[[[25, 25, 25], [50, 75, 100]]]", Arrays.deepToString(model1.
            getImageValues()));



    assertEquals("[[[25, 25, 25], [50, 75, 100], [205, 210, 160], [169, 68, 6]]]", Arrays.deepToString(model2.
            getImageValues()));

    int[][][] newList = new int[][][]{{{0, 0, 0}}};
    Image img = new ImageImpl(newList);
    model2 = new ImageModelImpl(img);
    assertEquals("[[[0, 0, 0]]]", Arrays.deepToString(model2.
            getImageValues()));

  }


  @Test
  public void testGetGrayscaleLuma() {
    model1.greyscaleByLuma();
    assertEquals("[[[25, 25, 25], [71, 71, 71]]]", Arrays.deepToString(model1.
            getImageValues()));


    model2.greyscaleByLuma();

    assertEquals("[[[25, 25, 25], [71, 71, 71], [205, 205, 205], [85, 85, 85]]]", Arrays.deepToString(model2.
            getImageValues()));

    int[][][] newList = new int[][][]{{{0, 0, 0}}};
    Image img = new ImageImpl(newList);
    model2 = new ImageModelImpl(img);
    model2.greyscaleByLuma();
    assertEquals("[[[0, 0, 0]]]", Arrays.deepToString(model2.
            getImageValues()));

  }

  @Test
  public void testFlipV() {
    model1.flipVertical();
    assertEquals("[[[25, 25, 25], [71, 71, 71]]]", Arrays.deepToString(model1.
            getImageValues()));


//    model2.greyscaleByLuma();
//
//    assertEquals("[[[25, 25, 25], [71, 71, 71], [205, 205, 205], [85, 85, 85]]]", Arrays.deepToString(model2.
//            getImageValues()));
//
//    int[][][] newList = new int[][][]{{{0, 0, 0}}};
//    Image img = new ImageImpl(newList);
//    model2 = new ImageModelImpl(img);
//    model2.greyscaleByLuma();
//    assertEquals("[[[0, 0, 0]]]", Arrays.deepToString(model2.
//            getImageValues()));

  }





}