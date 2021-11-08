package imemodel;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ExtraFiltersImplTest {

  private ExtraFilters fil1;
  private ExtraFilters fil2;
  private ImageModel mod1;

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
    String str1 = "Image1";
    model1 = new ImageModelImpl();
    model1.loadImage(str1, image1);

    list2 = new int[][][]{{{25, 25, 25}, {50, 75, 100}, {205, 210, 160}, {169, 68, 6}}};
    image2 = new ImageImpl(list2);
    model2 = new ImageModelImpl();

    mod1 = new ImageModelImpl();
    mod1.loadImage("Cool", new ImageImpl());
    fil1 = new ExtraFiltersImpl(mod1);

    fil2 = new ExtraFiltersImpl(model2);

  }

  @Test
  public void testBlur() {
    fil1.blur("Cool");
    assertEquals(Arrays.deepToString(new int[][][]{{{12, 87, 25}}, {{23, 77, 50}}}),
            Arrays.deepToString(fil1.getImageValues("Cool")));

    model2.loadImage("Image2", image2);
    fil2.blur("Image2");
    assertEquals(Arrays.deepToString(new int[][][]{{{12, 15, 19}, {42, 48, 48}, {78, 71, 54},
            {68, 43, 22}}}), Arrays.deepToString(fil2.getImageValues("Image2")));

    fil2.loadImage("Blur", new ImageImpl());
    fil2.blur("Blur");
    assertEquals(Arrays.deepToString(new int[][][]{{{12, 87, 25}}, {{23, 77, 50}}}),
            Arrays.deepToString(fil2.getImageValues("Blur")));

    ImageModel mod1 = new ImageModelImpl();
    ExtraFilters ex1 = new ExtraFiltersImpl(mod1);

    ex1.loadImage("Stay", new ImageImpl(new int[][][]{{{3, 3, 3}}}));
    assertArrayEquals(new int[][][]{{{3, 3, 3}}}, ex1.getImageValues("Stay"));
    ex1.blur("Stay");
    assertEquals(Arrays.deepToString(new int[][][]{{{1, 1, 1}}}), Arrays.deepToString(ex1
            .getImageValues("Stay")));

    ex1.loadImage("Koala", new ImageImpl(new int[][][]{{{1, 2, 3}}, {{1, 2, 3}}, {{1, 2, 3}}, {{1, 2, 3}}}));
    ex1.blur("Koala");
    assertEquals("[[[0, 1, 1]], [[0, 1, 1]], [[0, 1, 1]], [[0, 1, 1]]]", Arrays.
            deepToString(ex1.getImageValues("Koala")));

    ex1.loadImage("Text", new ImageImpl(new int[][][]{{{}}}));

    ex1.loadImage("safe", new ImageImpl(new int[][][]{{{2, 45, 12}}, {{23, 24, 90}}}));
    ex1.blur("safe");
    assertArrayEquals(new int[][][]{{{4, 14, 14}}, {{6, 12, 25}}},
            ex1.getImageValues("safe"));
  }

  @Test
  public void testSharpen() {
    fil1.sharpen("Cool");
    assertEquals("[[[25, 255, 50]], [[91, 244, 200]]]",
            Arrays.deepToString(fil1.getImageValues("Cool")));

    model2.loadImage("Image2", image2);
    fil2.sharpen("Image2");
    assertEquals("[[[12, 18, 30], [86, 126, 145], [255, 243, 184], [214, 112, 34]]]",
            Arrays.deepToString(fil2.getImageValues("Image2")));

    fil2.loadImage("Blur", new ImageImpl());
    fil2.sharpen("Blur");
    assertEquals("[[[25, 255, 50]], [[91, 244, 200]]]",
            Arrays.deepToString(fil2.getImageValues("Blur")));

    ImageModel mod1 = new ImageModelImpl();
    ExtraFilters ex1 = new ExtraFiltersImpl(mod1);

    ex1.loadImage("Stay", new ImageImpl(new int[][][]{{{3, 3, 3}}}));
    assertArrayEquals(new int[][][]{{{3, 3, 3}}}, ex1.getImageValues("Stay"));
    ex1.sharpen("Stay");
    assertEquals("[[[3, 3, 3]]]", Arrays.deepToString(ex1
            .getImageValues("Stay")));

    ex1.loadImage("Koala", new ImageImpl(new int[][][]{{{1, 2, 3}}, {{1, 2, 3}}, {{1, 2, 3}}, {{1, 2, 3}}}));
    ex1.sharpen("Koala");
    assertEquals("[[[1, 3, 4]], [[1, 4, 5]], [[1, 4, 5]], [[1, 3, 4]]]", Arrays.
            deepToString(ex1.getImageValues("Koala")));

    ex1.loadImage("Text", new ImageImpl(new int[][][]{{{}}}));

    ex1.loadImage("safe", new ImageImpl(new int[][][]{{{2, 45, 12}}, {{23, 24, 90}}}));
    ex1.sharpen("safe");
    assertEquals("[[[8, 51, 35]], [[24, 35, 93]]]",
            Arrays.deepToString(ex1.getImageValues("safe")));
  }

}