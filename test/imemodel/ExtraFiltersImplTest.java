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

  }

  @Test
  public void testBlur() {
    fil1.blur("Cool");
    assertEquals(Arrays.deepToString(new int[][][]{{{12, 87, 25}}, {{23, 77, 50}}}),
            Arrays.deepToString(fil1.getImageValues("Cool")));

    model2.loadImage("Image2", image2);
    
    assertArrayEquals(list2, model2.getImageValues("Image2"));
  }

}