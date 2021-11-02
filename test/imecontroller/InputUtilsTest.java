package imecontroller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

import imeview.IMEView;
import imeview.IMEViewMock;

/**
 * A test class for InputUtils.
 */
public class InputUtilsTest {

  Scanner sc;

  StringBuilder log;
  IMEView mockView;

  @Before
  public void setUp() {
    log = new StringBuilder();
    mockView = new IMEViewMock(log);
  }

  @Test
  public void testGetDoubleInput() {
    sc = new Scanner(new StringReader("0 1\n2\n\n3.0 12345 .445 10.2134"));

    Assert.assertEquals(0.0, InputUtils.getDoubleInput(mockView, sc), 0);
    Assert.assertEquals(1.0, InputUtils.getDoubleInput(mockView, sc), 0);
    Assert.assertEquals(2.0, InputUtils.getDoubleInput(mockView, sc), 0);
    Assert.assertEquals(3.0, InputUtils.getDoubleInput(mockView, sc), 0);
    Assert.assertEquals(12345.0, InputUtils.getDoubleInput(mockView, sc), 0);
    Assert.assertEquals(0.445, InputUtils.getDoubleInput(mockView, sc), 0);
    Assert.assertEquals(10.2134, InputUtils.getDoubleInput(mockView, sc), 0);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetDoubleInputException0() {
    sc = new Scanner(new StringReader(""));
    InputUtils.getDoubleInput(mockView, sc);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetDoubleInputException1() {
    sc = new Scanner(new StringReader("0.0"));
    InputUtils.getDoubleInput(mockView, sc);
    InputUtils.getDoubleInput(mockView, sc);
  }

  @Test
  public void testGetDoubleInputException2() {
    sc = new Scanner(new StringReader("bazinga!"));

    Assert.assertThrows("Ran out of inputs.", NoSuchElementException.class,
            () -> {
              InputUtils.getDoubleInput(mockView, sc);
            });
    Assert.assertEquals("renderMessage) message: Invalid input, expected a double.\n\n",
            log.toString());
  }

  @Test
  public void testGetDoubleInputException3() {
    sc = new Scanner(new StringReader("0.0 bazinga!"));

    Assert.assertEquals(0.0, InputUtils.getDoubleInput(mockView, sc), 0);
    Assert.assertThrows("Ran out of inputs.", NoSuchElementException.class,
            () -> {
              InputUtils.getDoubleInput(mockView, sc);
            });
    Assert.assertEquals("renderMessage) message: Invalid input, expected a double.\n\n",
            log.toString());
  }

  @Test
  public void testGetStringInput() {
    sc = new Scanner(new StringReader("input0 input1\ninput2\n\ninput3 12345"));

    Assert.assertEquals("input0", InputUtils.getStringInput(sc));
    Assert.assertEquals("input1", InputUtils.getStringInput(sc));
    Assert.assertEquals("input2", InputUtils.getStringInput(sc));
    Assert.assertEquals("input3", InputUtils.getStringInput(sc));
    Assert.assertEquals("12345", InputUtils.getStringInput(sc));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetStringInputException0() {
    sc = new Scanner(new StringReader(""));
    InputUtils.getStringInput(sc);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetStringInputException1() {
    sc = new Scanner(new StringReader("input0"));
    InputUtils.getStringInput(sc);
    InputUtils.getStringInput(sc);
  }
}
