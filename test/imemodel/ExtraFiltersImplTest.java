package imemodel;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ExtraFiltersImplTest {

  private ExtraFilters fil1;
  private ExtraFilters fil2;

  private ImageModel model2;
  private Image image2;

  @Before
  public void setUp() {

    int[][][] list1 = new int[][][]{{{25, 25, 25}, {50, 75, 100}}};
    Image image1 = new ImageImpl(list1);
    String str1 = "Image1";
    ImageModel model1 = new ImageModelImpl();
    model1.loadImage(str1, image1);

    int[][][] list2 = new int[][][]{{{25, 25, 25}, {50, 75, 100}, {205, 210, 160}, {169, 68, 6}}};
    image2 = new ImageImpl(list2);
    model2 = new ImageModelImpl();

    ImageModel mod1 = new ImageModelImpl();
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


  @Test
  public void testSepia() {
    fil1.toSepia("Cool");
    assertEquals("[[[197, 176, 137]], [[212, 188, 147]]]",
            Arrays.deepToString(fil1.getImageValues("Cool")));

    model2.loadImage("Image2", image2);
    fil2.toSepia("Image2");
    assertEquals("[[[34, 30, 23], [96, 86, 67], [255, 242, 189], [120, 107, 83]]]",
            Arrays.deepToString(fil2.getImageValues("Image2")));

    fil2.loadImage("Blur", new ImageImpl());
    fil2.toSepia("Blur");
    assertEquals("[[[197, 176, 137]], [[212, 188, 147]]]",
            Arrays.deepToString(fil2.getImageValues("Blur")));

    ImageModel mod1 = new ImageModelImpl();
    ExtraFilters ex1 = new ExtraFiltersImpl(mod1);

    ex1.loadImage("Stay", new ImageImpl(new int[][][]{{{3, 3, 3}}}));
    assertArrayEquals(new int[][][]{{{3, 3, 3}}}, ex1.getImageValues("Stay"));
    ex1.toSepia("Stay");
    assertEquals("[[[4, 4, 3]]]", Arrays.deepToString(ex1
            .getImageValues("Stay")));

    ex1.loadImage("Koala", new ImageImpl(new int[][][]{{{1, 2, 3}}, {{1, 2, 3}}, {{1, 2, 3}}, {{1, 2, 3}}}));
    ex1.toSepia("Koala");
    assertEquals("[[[2, 2, 2]], [[2, 2, 2]], [[2, 2, 2]], [[2, 2, 2]]]", Arrays.
            deepToString(ex1.getImageValues("Koala")));

    ex1.loadImage("Text", new ImageImpl(new int[][][]{{{}}}));

    ex1.loadImage("safe", new ImageImpl(new int[][][]{{{2, 45, 12}}, {{23, 24, 90}}}));
    ex1.toSepia("safe");
    assertEquals("[[[38, 34, 26]], [[45, 40, 31]]]",
            Arrays.deepToString(ex1.getImageValues("safe")));
  }

  @Test
  public void testLuma() {

    fil1.loadImage("eaer", new ImageImpl(new int[][][]{{{1, 2, 3}}}));
    assertTrue(fil1.hasImage("eaer"));

    fil1.greyscaleByLuma("eaer");
    assertTrue(fil1.hasImage("eaer"));
    assertArrayEquals(new int[][][]{{{2, 2, 2}}}, fil1.getImageValues("eaer"));
    fil1.loadImage("safe", new ImageImpl(new int[][][]{{{2, 45, 12}}, {{23, 24, 90}}}));
    fil1.greyscaleByLuma("safe");
    assertArrayEquals(new int[][][]{{{33, 33, 33}}, {{29, 29, 29}}},
            fil1.getImageValues("safe"));

    fil2.loadImage("Blur", new ImageImpl());
    fil2.greyscaleByLuma("Blur");
    assertEquals("[[[183, 183, 183]], [[162, 162, 162]]]",
            Arrays.deepToString(fil2.getImageValues("Blur")));

    ImageModel mod1 = new ImageModelImpl();
    ExtraFilters ex1 = new ExtraFiltersImpl(mod1);

    ex1.loadImage("Stay", new ImageImpl(new int[][][]{{{3, 3, 3}}}));
    assertArrayEquals(new int[][][]{{{3, 3, 3}}}, ex1.getImageValues("Stay"));
    ex1.greyscaleByLuma("Stay");
    assertEquals("[[[3, 3, 3]]]", Arrays.deepToString(ex1
            .getImageValues("Stay")));

    ex1.loadImage("Koala", new ImageImpl(new int[][][]{{{1, 2, 3}}, {{1, 2, 3}}, {{1, 2, 3}}, {{1, 2, 3}}}));
    ex1.greyscaleByLuma("Koala");
    assertEquals("[[[2, 2, 2]], [[2, 2, 2]], [[2, 2, 2]], [[2, 2, 2]]]", Arrays.
            deepToString(ex1.getImageValues("Koala")));

    ex1.loadImage("Text", new ImageImpl(new int[][][]{{{}}}));

    ex1.loadImage("safe", new ImageImpl(new int[][][]{{{2, 45, 12}}, {{23, 24, 90}}}));
    ex1.greyscaleByLuma("safe");
    assertEquals("[[[33, 33, 33]], [[29, 29, 29]]]",
            Arrays.deepToString(ex1.getImageValues("safe")));
  }


  /**
   * This class represents a mock of the ExtraFilters class.
   */
  public static class MockExtraFilters implements ExtraFilters {

    //Stringbuilder to check that inputs are passed correctly to the methods.
    protected StringBuilder log;

    /**
     * Constructor for testing.
     *
     * @param log Log for checking inputs are passed correctly.
     */
    public MockExtraFilters(StringBuilder log) {
      this.log = log;
    }

    @Override
    public void blur(String name) {
      log.append("blur").append(name);
    }

    @Override
    public void sharpen(String name) {

      log.append("sharpen").append(name);
    }

    @Override
    public void toSepia(String name) {
      log.append("sepia").append(name);
    }

    @Override
    public int[][][] getImageValues(String name) {
      log.append("name");
      return new int[0][][];
    }

    @Override
    public void loadImage(String name, Image image) {
      log.append("load").append(name);
    }

    @Override
    public void duplicateImage(String name, String newName) {
      log.append("dup").append(name);
    }

    @Override
    public Image getImage(String name) {
      log.append("get").append(name);
      return new ImageImpl(new int[][][]{{{0}}});
    }

    @Override
    public boolean hasImage(String name) {
      return true;
    }

    @Override
    public void getByComponent(String name, String comp) {
      log.append(comp).append(name);
    }

    @Override
    public void brighten(String name, double increase) {

      log.append("brighten").append(name).append(increase);
    }

    @Override
    public void closeImage(String name) {

      log.append("close").append(name);
    }

    @Override
    public void flipVertical(String name) {

      log.append("vert").append(name);
    }

    @Override
    public void flipHorizontal(String name) {
      log.append("hori").append(name);
    }

    @Override
    public void greyscaleByLuma(String name) {
      log.append("luma").append(name);
    }

    @Override
    public Set<String> getKeys() {
      log.append("getKeys");
      return null;
    }

    public StringBuilder getLog() {
      return log;
    }
  }



  @Test
  public void testMockBlur() {
    MockExtraFilters mock = new MockExtraFilters(new StringBuilder());
    mock.blur("blur");
    String actual = mock.getLog().toString();
    assertEquals("blurblur", actual);
  }

  @Test
  public void testMockSharpen() {
    MockExtraFilters mock = new MockExtraFilters(new StringBuilder());
    mock.sharpen("sharpen");
    String actual = mock.getLog().toString();
    assertEquals("sharpensharpen", actual);
  }

  @Test
  public void testMockSepia() {
    MockExtraFilters mock = new MockExtraFilters(new StringBuilder());
    mock.toSepia("sepia");
    String actual = mock.getLog().toString();
    assertEquals("sepiasepia", actual);
  }

  @Test
  public void testMockLuma() {
    MockExtraFilters mock = new MockExtraFilters(new StringBuilder());
    mock.greyscaleByLuma(" l");
    String actual = mock.getLog().toString();
    assertEquals("luma l", actual);
  }

  @Test
  public void testMockComponent() {
    MockExtraFilters mock = new MockExtraFilters(new StringBuilder());
    mock.getByComponent("comp", "red");
    String actual = mock.getLog().toString();
    assertEquals("redcomp", actual);
  }

  @Test
  public void testMockBrighten() {

    MockExtraFilters mock = new MockExtraFilters(new StringBuilder());
    mock.brighten("2", 2);
    String actual = mock.getLog().toString();
    assertEquals("brighten22.0", actual);
  }

  @Test
  public void testMockVert() {
    MockExtraFilters mock = new MockExtraFilters(new StringBuilder());
    mock.flipVertical("lol");
    String actual = mock.getLog().toString();
    assertEquals("vertlol", actual);
  }

  @Test
  public void testMockHori() {
    MockExtraFilters mock = new MockExtraFilters(new StringBuilder());
    mock.flipHorizontal("lol");
    String actual = mock.getLog().toString();
    assertEquals("horilol", actual);
  }

  @Test
  public void testMockClose() {
    MockExtraFilters mock = new MockExtraFilters(new StringBuilder());
    mock.closeImage("lol");
    String actual = mock.getLog().toString();
    assertEquals("closelol", actual);
  }

  @Test
  public void testGetKeys() {
    MockExtraFilters mock = new MockExtraFilters(new StringBuilder());
    mock.getKeys();
    String actual = mock.getLog().toString();
    assertEquals("getKeys", actual);
  }




  @Test
  public void testMockImageValues() {
    MockExtraFilters mock = new MockExtraFilters(new StringBuilder());
    mock.getImageValues("name");
    String actual = mock.getLog().toString();
    assertEquals("name", actual);
    assertArrayEquals(new int[0][][], mock.getImageValues("name"));
  }

  @Test
  public void testMockLoadImage() {
    MockExtraFilters mock = new MockExtraFilters(new StringBuilder());
    mock.loadImage("name", new ImageImpl());
    String actual = mock.getLog().toString();
    assertEquals("loadname", actual);

  }

  @Test
  public void testMockDupImage() {
    MockExtraFilters mock = new MockExtraFilters(new StringBuilder());
    mock.duplicateImage("name", "new");
    String actual = mock.getLog().toString();
    assertEquals("dupname", actual);

  }

  @Test
  public void testGetImage() {
    MockExtraFilters mock = new MockExtraFilters(new StringBuilder());
    mock.getImage("name");
    String actual = mock.getLog().toString();
    assertEquals("getname", actual);


  }


  @Test
  public void testHasImage() {
    MockExtraFilters mock = new MockExtraFilters(new StringBuilder());
    mock.hasImage("name");
    String actual = mock.getLog().toString();
    assertEquals("", actual);
    assertTrue(mock.hasImage("example"));


  }

}