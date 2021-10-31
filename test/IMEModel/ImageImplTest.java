package IMEModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ImageImplTest {

  private Image image1;
  private ImageModel model1;

  private int[][][] list1;

  @Before
  public void setUp() {

    list1 = new int[][][]{{{50, 50, 14}, {3, 2, 3}}};
    image1 = new ImageImpl(list1);
    model1 = new ImageModelImpl();

  }

  @Test
  public void testGetPixels() {
    assertArrayEquals(list1, image1.getPixels());
  }

  @Test
  public void testGetHeight() {
    assertEquals(1, image1.getHeight());
  }

  @Test
  public void testGetWidth() {
    assertEquals(2, image1.getWidth());
  }


  @Test
  public void testGetModelPixels() {
    //assertArrayEquals(new int[1][1][3], model1.getImageValues());
  }

//  @Test
//  public void testGetModel1Pixels() {
//    assertArrayEquals(new int[1][1][3], new
//            ImageModelImpl("/Users/thomasgrbic/Downloads/code (10)/koala-luma-greyscale.ppm").getImageValues());
//  }





}