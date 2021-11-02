package imeview;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import imemodel.ImageImpl;
import imemodel.ImageModel;
import imemodel.ImageModelImpl;

/**
 * This tester class holds the tests for the IMEViewImpl class, in which all of its public facing
 * methods and constructors are tested.
 */
public class IMEViewImplTest {

  private IMEView view1;
  private IMEView view2;

  private ImageModel mod1;
  private ImageModel mod2;
  private Appendable str;

  @Before
  public void setUp() {
    mod1 = new ImageModelImpl(new ImageImpl(new int[][][]{{{24, 2, 217}}}));
    mod2 = new ImageModelImpl(new ImageImpl(new int[][][]{{{0}}}, 0, 0));
    str = new StringBuilder();
    view1 = new IMEViewImpl(mod1);
    view2 = new IMEViewImpl(mod2, str);

  }

  @Test (expected = IllegalArgumentException.class)
  public void testConst1() {
    new IMEViewImpl(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConst2() {
    new IMEViewImpl(null, null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConst3() {
    new IMEViewImpl(mod1, null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConst4() {
    new IMEViewImpl(null, str);
  }


  @Test
  public void testRenderMsg() {
    view2.renderMessage("Hello World!");
    String expected = "Hello World!";
    assertEquals(expected, str.toString());

    view2.renderMessage(" Light!");
    String expectedout = "Hello World! Light!";
    assertEquals(expectedout, str.toString());

    Appendable ap1 = new StringBuilder();
    IMEView view1 = new IMEViewImpl(mod1, ap1);
    view1.renderMessage("The dog made this application!");
    expected = "The dog made this application!";
    assertEquals(expected, ap1.toString());

  }

  @Test
  public void testToString() {
    assertEquals("|===============\n" +
            "| Loaded images:\n" +
            "|===============", view1.toString());

    mod1.loadImage("Str1", new ImageImpl(new int[][][]{{{24, 89, 12}}}));
    assertEquals("|===============\n" +
            "| Loaded images:\n" +
            "| Str1\n" +
            "|===============", view1.toString());
    mod1.loadImage("Stardust", new ImageImpl(new int[][][]{{{24, 89, 12}}}));
    assertEquals("|===============\n" +
            "| Loaded images:\n" +
            "| Stardust\n" +
            "| Str1\n" +
            "|===============", view1.toString());
    mod1.loadImage("Str1", new ImageImpl(new int[][][]{{{0}}}));
    assertEquals("|===============\n" +
            "| Loaded images:\n" +
            "| Stardust\n" +
            "| Str1\n" +
            "|===============", view1.toString());
    mod1.closeImage("Str1");
    assertEquals("|===============\n" +
            "| Loaded images:\n" +
            "| Stardust\n" +
            "|===============", view1.toString());
    mod1.closeImage("Stardust");
    assertEquals("|===============\n" +
            "| Loaded images:\n" +
            "|===============", view1.toString());

  }
}

