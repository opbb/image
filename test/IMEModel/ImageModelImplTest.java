package IMEModel;

import org.junit.Assert;
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

    Image img2 = new ImageImpl(new int[][][]{{{2 , 129, 189}}});
    model2.loadImage("Sparking", img2);
    assertEquals(Arrays.deepToString(img2.getPixels()), Arrays.deepToString(model1
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




}