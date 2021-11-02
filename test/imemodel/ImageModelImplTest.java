package imemodel;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ImageModelImplTest {

  private ImageModel model1;
  private Image image1;
  private int[][][] list1;
  private String str1;

  private ImageModel model2;
  private Image image2;
  private int[][][] list2;

  @Before
  public void setUp() {

    list1 = new int[][][]{{{25, 25, 25}, {50, 75, 100}}};
    image1 = new ImageImpl(list1);
    str1 = "Image1";
    model1 = new ImageModelImpl();
    model1.loadImage(str1, image1);

    list2 = new int[][][]{{{25, 25, 25}, {50, 75, 100}, {205, 210, 160}, {169, 68, 6}}};
    image2 = new ImageImpl(list2);
    model2 = new ImageModelImpl();
  }

  @Test
  public void testGetValues() {
    assertArrayEquals(list1, model1.getImageValues("Image1"));
    model1.loadImage("ImageOfWi", new ImageImpl(new int[][][]{{{1}}}));
    assertArrayEquals(new int[][][]{{{1}}}, model1.getImageValues("ImageOfWi"));

    model2.loadImage("Image2", image2);
    assertArrayEquals(list2, model2.getImageValues("Image2"));

    ImageModel mod1 = new ImageModelImpl();
    mod1.loadImage("Stay", new ImageImpl(new int[][][]{{{3, 3, 3}}}));
    assertArrayEquals(new int[][][]{{{3, 3, 3}}}, mod1.getImageValues("Stay"));
  }

  @Test
  public void testLoadImage() {
    model1.loadImage("Trevor", new ImageImpl(new int[][][]{{{2, 2,2}}, {{1, 12, 178}}}));
    assertEquals(Arrays.deepToString(new int[][][]{{{2, 2,2}}, {{1, 12, 178}}}),
            Arrays.deepToString(model1.getImageValues("Trevor")));
    model1.loadImage("silverware", new ImageImpl(new int[][][]{{{1}}}));
    assertArrayEquals(new int[][][]{{{1}}}, model1.getImage("silverware").getPixels());

    Image img5 = new ImageImpl(new int[][][]{{{2 , 129, 189}}});
    model2.loadImage("Sparking", img5);
    assertEquals(Arrays.deepToString(img5.getPixels()), Arrays.deepToString(model2
            .getImageValues("Sparking")));

  }

  @Test
  public void testDuplicate() {
    model1.loadImage("Trevor", new ImageImpl(new int[][][]{{{2, 2,2}}, {{1, 12, 178}}}));
    model1.duplicateImage("Trevor", "Bixxy");
    assertEquals(Arrays.deepToString(new int[][][]{{{2, 2,2}}, {{1, 12, 178}}}),
            Arrays.deepToString(model1.getImageValues("Trevor")));
    assertEquals(Arrays.deepToString(new int[][][]{{{2, 2,2}}, {{1, 12, 178}}}),
            Arrays.deepToString(model1.getImageValues("Bixxy")));

  }

  @Test
  public void testGetImage() {

    model1.loadImage("eaer", new ImageImpl(new int[][][]{{{1, 2, 3}}}));
    assertArrayEquals(new int[][][]{{{1, 2, 3}}}, model1.getImage("eaer").getPixels());

    model2.loadImage("Image2", image2);
    assertArrayEquals(new int[][][]{{{25, 25, 25}, {50, 75, 100}, {205, 210, 160}, {169, 68, 6}}},
            model2.getImage("Image2").getPixels());


  }

  @Test
  public void testHasImage() {

    model1.loadImage("eaer", new ImageImpl(new int[][][]{{{1, 2, 3}}}));
    assertTrue(model1.hasImage("eaer"));

    model2.loadImage("Image2", image2);
    assertTrue(model2.hasImage("Image2"));
    assertFalse(model2.hasImage("eaer"));
    assertFalse(model2.hasImage("Image1"));
    assertFalse(model2.hasImage("Trevor"));
    model2.loadImage("SingingBird", new ImageImpl(new int[][][]{{{}}}));
    assertTrue(model2.hasImage("SingingBird"));
  }

  @Test
  public void testGreyScaleLuma() {

    model1.loadImage("eaer", new ImageImpl(new int[][][]{{{1, 2, 3}}}));
    assertTrue(model1.hasImage("eaer"));

    model1.greyscaleByLuma("eaer");
    assertTrue(model1.hasImage("eaer"));
    assertArrayEquals(new int[][][]{{{2, 2, 2}}}, model1.getImageValues("eaer"));
    model2.loadImage("safe", new ImageImpl(new int[][][]{{{2, 45, 12}}, {{23, 24, 90}}}));
    model2.greyscaleByLuma("safe");
    assertArrayEquals(new int[][][]{{{33, 33, 33}}, {{29, 29, 29}}},
            model2.getImageValues("safe"));

  }

  @Test
  public void testGetByComponent() {

    model1.loadImage("eaer", new ImageImpl(new int[][][]{{{1, 2, 3}}}));

    model1.getByComponent("eaer", "red");
    assertTrue(model1.hasImage("eaer"));
    assertArrayEquals(new int[][][]{{{1, 1, 1}}}, model1.getImageValues("eaer"));

    model2.loadImage("safe", new ImageImpl(new int[][][]{{{2, 45, 12}}, {{23, 24, 90}}}));
    model2.getByComponent("safe", "blue");
    assertArrayEquals(new int[][][]{{{12, 12, 12}}, {{90, 90, 90}}},
            model2.getImageValues("safe"));


    model1.loadImage("eaer", new ImageImpl(new int[][][]{{{1, 2, 3}}}));

    model1.getByComponent("eaer", "green");
    assertTrue(model1.hasImage("eaer"));
    assertArrayEquals(new int[][][]{{{2, 2, 2}}}, model1.getImageValues("eaer"));

    model2.loadImage("safe", new ImageImpl(new int[][][]{{{2, 45, 12}}, {{23, 24, 90}}}));
    model2.getByComponent("safe", "value");
    assertArrayEquals(new int[][][]{{{45, 45, 45}}, {{90, 90, 90}}},
            model2.getImageValues("safe"));

    model1.loadImage("eaer", new ImageImpl(new int[][][]{{{1, 2, 3}}}));
    assertTrue(model1.hasImage("eaer"));

    model1.getByComponent("eaer", "value");

    assertArrayEquals(new int[][][]{{{3, 3, 3}}}, model1.getImageValues("eaer"));
    model2.loadImage("safe", new ImageImpl(new int[][][]{{{2, 45, 12}}, {{23, 24, 90}}}));
    model2.getByComponent("safe", "intensity");
    assertArrayEquals(new int[][][]{{{19, 19, 19}}, {{45, 45, 45}}},
            model2.getImageValues("safe"));

  }

  @Test
  public void testBrightenAndDarken() {

    model1.loadImage("eaer", new ImageImpl(new int[][][]{{{1, 2, 3}}}));
    assertTrue(model1.hasImage("eaer"));
    model1.brighten("eaer", 100);

    assertArrayEquals(new int[][][]{{{101, 102, 103}}}, model1.getImageValues("eaer"));

    model2.loadImage("safe", new ImageImpl(new int[][][]{{{2, 45, 12}}, {{23, 24, 90}}}));
    model2.brighten("safe", 101);
    assertArrayEquals(new int[][][]{{{103, 146, 113}}, {{124, 125, 191}}},
            model2.getImageValues("safe"));

    model1.loadImage("eaer", new ImageImpl(new int[][][]{{{1, 2, 3}}}));
    assertTrue(model1.hasImage("eaer"));
    model1.brighten("eaer", -100);

    assertArrayEquals(new int[][][]{{{0, 0, 0}}}, model1.getImageValues("eaer"));

    model2.loadImage("safe", new ImageImpl(new int[][][]{{{2, 45, 12}}, {{23, 24, 90}}}));
    model2.brighten("safe", -89);
    assertArrayEquals(new int[][][]{{{0, 0, 0}}, {{0, 0, 1}}},
            model2.getImageValues("safe"));


    model1.loadImage("echopark", new ImageImpl(new int[][][]{{{255, 255, 255}}}));
    assertTrue(model1.hasImage("echopark"));
    model1.brighten("echopark", 1);

    assertArrayEquals(new int[][][]{{{255, 255, 255}}}, model1.getImageValues("echopark"));

    model2.loadImage("backTo", new ImageImpl(new int[][][]{{{0, 100, 59}}, {{3, 2, 255}}}));
    model2.brighten("backTo", -1000);
    assertArrayEquals(new int[][][]{{{0, 0, 0}}, {{0, 0, 0}}},
            model2.getImageValues("backTo"));
  }

  @Test
  public void testVerticalFlip() {

    model1.loadImage("eaer", new ImageImpl(new int[][][]{{{1, 2, 3}}}));
    assertTrue(model1.hasImage("eaer"));
    model1.flipVertical("eaer");

    assertArrayEquals(new int[][][]{{{1, 2, 3}}}, model1.getImageValues("eaer"));

    model2.loadImage("safe", new ImageImpl(new int[][][]{{{2, 45, 12}}, {{23, 24, 90}}}));
    model2.flipVertical("safe");
    assertArrayEquals(new int[][][]{{{23, 24, 90}}, {{2, 45, 12}}},
            model2.getImageValues("safe"));

    model2.loadImage("Over", image2);
    model2.flipVertical("Over");
    assertArrayEquals(new int[][][]{{{25, 25, 25}, {50, 75, 100}, {205, 210, 160}, {169, 68, 6}}},
            model2.getImageValues("Over"));

    //testing with new image.
    model2.loadImage("Horses", new ImageImpl(new int[][][]{{{77, 189, 208}},
            {{56, 109, 215}}, {{34, 90, 249 }}, {{56, 89, 191}}}));
    model2.flipVertical("Horses");
    assertArrayEquals(new int[][][]{{{56, 89, 191}},
                    {{34, 90, 249}}, {{56, 109, 215 }}, {{77, 189, 208}}},
            model2.getImageValues("Horses"));
    //flip the same image back to its original.
    model2.flipVertical("Horses");
    assertArrayEquals(new int[][][]{{{77, 189, 208}},
                    {{56, 109, 215}}, {{34, 90, 249 }}, {{56, 89, 191}}},
            model2.getImageValues("Horses"));



  }

  @Test
  public void testHorizontalFlip() {

    model1.loadImage("eaer", new ImageImpl(new int[][][]{{{1, 2, 3}}}));
    assertTrue(model1.hasImage("eaer"));
    model1.flipHorizontal("eaer");

    assertArrayEquals(new int[][][]{{{1, 2, 3}}}, model1.getImageValues("eaer"));

    model2.loadImage("safe", new ImageImpl(new int[][][]{{{2, 45, 12}}, {{23, 24, 90}}}));
    model2.flipHorizontal("safe");
    assertArrayEquals(new int[][][]{{{2, 45, 12}}, {{23, 24, 90}}},
            model2.getImageValues("safe"));

    model2.loadImage("Over", image2);
    model2.flipHorizontal("Over");
    assertArrayEquals(new int[][][]{{{169, 68, 6}, {205, 210, 160}, {50, 75, 100}, {25, 25, 25}}},
            model2.getImageValues("Over"));

    //testing with new image.
    model2.loadImage("Horses", new ImageImpl(new int[][][]{{{77, 189, 208}},
            {{56, 109, 215}}, {{34, 90, 249 }}, {{56, 89, 191}}}));
    model2.flipHorizontal("Horses");
    assertArrayEquals(new int[][][]{{{77, 189, 208}},
                    {{56, 109, 215}}, {{34, 90, 249 }}, {{56, 89, 191}}},
            model2.getImageValues("Horses"));
    //flip the same image back to its original.
    model2.flipHorizontal("Horses");
    assertArrayEquals(new int[][][]{{{77, 189, 208}},
                    {{56, 109, 215}}, {{34, 90, 249 }}, {{56, 89, 191}}},
            model2.getImageValues("Horses"));



  }

  @Test
  public void testClose() {
    model1.closeImage("Image1");
    assertFalse(model1.hasImage("Image1"));
    model1.loadImage("img2", image2);
    model1.loadImage("Much", new ImageImpl(new int[][][]{{{}}}));
    assertTrue(model1.hasImage("Much"));
    assertTrue(model1.hasImage("img2"));
    model1.closeImage("img2");
    model1.closeImage("Much");
    assertFalse(model1.hasImage("img2"));
    assertFalse(model1.hasImage("Much"));

  }

  @Test
  public void testToString() {
    model2.loadImage("Img2", image2);
    assertEquals("Img2\n[[[25, 25, 25], [50, 75, 100], [205, 210, 160], " +
            "[169, 68, 6]]]\n\n", model2.toString());
    assertEquals("Image1\n" +
            "[[[25, 25, 25], [50, 75, 100]]]\n\n", model1.toString());

    model2.loadImage("Over", image2);

    assertEquals("Over\n" +
                    "[[[25, 25, 25], [50, 75, 100], [205, 210, 160], [169, 68, 6]]]\n" +
                    "\n" +
                    "Img2\n" +
                    "[[[25, 25, 25], [50, 75, 100], [205, 210, 160], [169, 68, 6]]]\n" +
                    "\n",
            model2.toString());


    model2.loadImage("Horses", new ImageImpl(new int[][][]{{{77, 189, 208}},
            {{56, 109, 215}}, {{34, 90, 249 }}, {{56, 89, 191}}}));
    assertEquals("Over\n" +
            "[[[25, 25, 25], [50, 75, 100], [205, 210, 160], [169, 68, 6]]]\n" +
            "\n" +
            "Horses\n" +
            "[[[77, 189, 208]], [[56, 109, 215]], [[34, 90, 249]], [[56, 89, 191]]]\n" +
            "\n" +
            "Img2\n" +
            "[[[25, 25, 25], [50, 75, 100], [205, 210, 160], [169, 68, 6]]]\n" +
            "\n", model2.toString());

  }

  @Test
  public void testGetKeys() {
    assertEquals("[Image1]", model1.getKeys().toString());

    model2.loadImage("Image1", image1);
    model2.loadImage("Img2", image2);

    assertEquals("[Image1, Img2]", model2.getKeys().toString());

    model2.loadImage("Over", image2);
    assertEquals("[Over, Image1, Img2]", model2.getKeys().toString());
    model2.closeImage("Over");
    assertEquals("[Image1, Img2]", model2.getKeys().toString());

    model2.loadImage("Horses", new ImageImpl(new int[][][]{{{77, 189, 208}},
            {{56, 109, 215}}, {{34, 90, 249 }}, {{56, 89, 191}}}));
    assertEquals("[Horses, Image1, Img2]", model2.getKeys().toString());


  }

  @Test (expected = IllegalArgumentException.class)
  public void testConst1() {
    new ImageModelImpl(null);
  }


  @Test
  public void testConst2() {
    //this test will pass if the grader/user has the images/folder with the contained image.
//    ImageModel mod1 = new ImageModelImpl("walk", "images/when-you-walking.ppm");
//    assertTrue(mod1.hasImage("walk"));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConst3() {
    new ImageModelImpl("", "");
  }

  //testing default constructor
  @Test
  public void testConst4() {
    model1 = new ImageModelImpl();
    assertFalse(model1.hasImage("Image1"));
  }


}