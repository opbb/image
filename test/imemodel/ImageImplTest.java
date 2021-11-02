
package imemodel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class ImageImplTest {

  private Image image1;
  private Image img2;
  private Image img3;


  private int[][][] list1;
  private int[][][] list2;
  private int[][][] list3;

  @Before
  public void setUp() {

    list1 = new int[][][]{{{50, 50, 14}, {3, 2, 3}}};
    image1 = new ImageImpl(list1);

    list2 = new int[][][]{{{2, 2, 2}, {102, 28, 219}}, {{56, 78, 148}, {47, 189, 214}}};
    img2 = new ImageImpl(list2);

    list3 = new int[][][]{{{3, 78, 198}}, {{45, 90, 180}}, {{190, 245, 202}}};
    img3 = new ImageImpl(list3, 500, 750);


  }

  @Test
  public void testGetPixels() {
    assertArrayEquals(list1, image1.getPixels());
    assertArrayEquals(list2, img2.getPixels());
    assertArrayEquals(list3, img3.getPixels());
    list3 = new int[][][]{{{1, 2, 3}}};
    Image img4 = new ImageImpl(list3);
    assertArrayEquals(list3, img4.getPixels());
  }

  @Test
  public void testGetHeight() {
    assertEquals(1, image1.getHeight());
    assertEquals(2, img2.getHeight());
    assertEquals(500, img3.getHeight());
    assertEquals(7000, new ImageImpl(list2, 7000, 1).getHeight());
  }

  @Test
  public void testGetWidth() {
    assertEquals(2, image1.getWidth());
    assertEquals(2, img2.getWidth());
    assertEquals(750, img3.getWidth());
    assertEquals(13800, new ImageImpl(list3, 1230, 13800).getWidth());
  }

  @Test
  public void testSetPixels() {
    assertArrayEquals(list1, image1.getPixels());
    image1.setPixels(new int[][][]{{{0}}});
    assertArrayEquals(new int[][][]{{{0}}}, image1.getPixels());

    assertArrayEquals(list2, img2.getPixels());
    img2.setPixels(new int[][][]{{{0, 90, 90, 180, 0, 180, 360, 270}}});
    assertArrayEquals(new int[][][]{{{0, 90, 90, 180, 0, 180, 360, 270}}}, img2.getPixels());


  }

  @Test (expected = IllegalArgumentException.class)
  public void testConst1() {
    new ImageImpl(new int[][][]{{{0}}}, -120, 24);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConst2() {
    new ImageImpl(new int[][][]{{{0}}}, 12210, -128);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConst3() {
    new ImageImpl("");
  }

}
