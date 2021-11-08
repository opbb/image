package imemodel;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * This class is the tester class for the Application inteface, in which its static methods are
 * being tested.
 */
public class Application1Test {

  private Image img1;

  @Before
  public void setUp() {
    img1 = new ImageImpl(new int[][][]{{{1, 2, 3}}});
    Image img2 = new ImageImpl(new int[][][]{{{12, 124, 209}}, {{16, 17, 124}}});
  }


  @Test
  public void testAppliedSetRGB() {
    img1.setPixels(new int[][][]{{{3, 1, 1}}});
    Image img2 = Application1.applySetEffect(img1, "red");
    assertEquals("[[[3, 3, 3]]]", Arrays.deepToString(img2.getPixels()));

    img2.setPixels(new int[][][]{{{251, 25, 78}}});
    img2 = Application1.applySetEffect(img2, "red");
    assertEquals("[[[251, 251, 251]]]", Arrays.deepToString(img2.getPixels()));

    img2.setPixels(new int[][][]{{{5, 8, 0}}});
    img2 = Application1.applySetEffect(img2, "blue");
    assertEquals("[[[0, 0, 0]]]", Arrays.deepToString(img2.getPixels()));

    img2.setPixels(new int[][][]{{{89, 12, 208}}});
    img2 = Application1.applySetEffect(img2, "blue");
    assertEquals("[[[208, 208, 208]]]", Arrays.deepToString(img2.getPixels()));

    img2.setPixels(new int[][][]{{{52, 128, 20}}});
    img2 = Application1.applySetEffect(img2, "green");
    assertEquals("[[[128, 128, 128]]]", Arrays.deepToString(img2.getPixels()));

    img2.setPixels(new int[][][]{{{67, 255, 234}}});
    img2 = Application1.applySetEffect(img2, "green");
    assertEquals("[[[255, 255, 255]]]", Arrays.deepToString(img2.getPixels()));

  }

  @Test
  public void testAppliedSetValue() {
    img1.setPixels(new int[][][]{{{245, 255, 212}}});
    Image img2 = Application1.applySetEffect(img1, "value");
    assertEquals("[[[255, 255, 255]]]", Arrays.deepToString(img2.getPixels()));

    img2.setPixels(new int[][][]{{{0, 0, 0}}});
    img2 = Application1.applySetEffect(img2, "value");
    assertEquals("[[[0, 0, 0]]]", Arrays.deepToString(img2.getPixels()));

    img2.setPixels(new int[][][]{{{254, 253, 0}}});
    img2 = Application1.applySetEffect(img2, "value");
    assertEquals("[[[254, 254, 254]]]", Arrays.deepToString(img2.getPixels()));

  }

  @Test
  public void testAppliedSetIntensity() {
    img1.setPixels(new int[][][]{{{245, 255, 212}}});
    Image img2 = Application1.applySetEffect(img1, "intensity");
    assertEquals("[[[237, 237, 237]]]", Arrays.deepToString(img2.getPixels()));

    img2.setPixels(new int[][][]{{{0, 0, 0}}});
    img2 = Application1.applySetEffect(img2, "intensity");
    assertEquals("[[[0, 0, 0]]]", Arrays.deepToString(img2.getPixels()));

    img2.setPixels(new int[][][]{{{17, 17, 17}}});
    img2 = Application1.applySetEffect(img2, "intensity");
    assertEquals("[[[17, 17, 17]]]", Arrays.deepToString(img2.getPixels()));

  }

  @Test
  public void testAppliedAddedEffect() {

    img1.setPixels(new int[][][]{{{245, 255, 212}}});
    Image img2 = Application1.applyAddedEffect(img1, 100);
    assertEquals("[[[255, 255, 255]]]", Arrays.deepToString(img2.getPixels()));

    img2.setPixels(new int[][][]{{{0, 0, 0}}});
    img2 = Application1.applyAddedEffect(img2, 123.7);
    assertEquals("[[[124, 124, 124]]]", Arrays.deepToString(img2.getPixels()));

    img2.setPixels(new int[][][]{{{17, 17, 17}}});
    img2 = Application1.applyAddedEffect(img2, 300.23);
    assertEquals("[[[255, 255, 255]]]", Arrays.deepToString(img2.getPixels()));

    img1.setPixels(new int[][][]{{{255, 255, 255}}});
    img2 = Application1.applyAddedEffect(img1, -55.0);
    assertEquals("[[[200, 200, 200]]]", Arrays.deepToString(img2.getPixels()));

    img2.setPixels(new int[][][]{{{147, 147, 0}}});
    img2 = Application1.applyAddedEffect(img2, 59);
    assertEquals("[[[206, 206, 59]]]", Arrays.deepToString(img2.getPixels()));


    img2 = Application1.applyAddedEffect(img2, -59);
    assertEquals("[[[147, 147, 0]]]", Arrays.deepToString(img2.getPixels()));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullMultiplied() {
    Image img = Application1.applyMultipliedEffect(null, new double[][]{{2.0}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAdded() {
    Image img = Application1.applyAddedEffect(null, 234.4332);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullSet() {
    Image img = Application1.applySetEffect(null, "WRong mesgea");
  }
}
