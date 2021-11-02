package imecontroller;

import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import imemodel.ImageModel;
import imemodel.ImageModelMockTrue;
import imeview.IMEView;
import imeview.IMEViewMock;

class IMEControllerImplTest {

  StringBuilder log;
  ImageModel mockModelTrue;
  IMEView mockView;
  Scanner sc;

  @Before
  public void setUp() {
    log = new StringBuilder();
    mockModelTrue = new ImageModelMockTrue(log);
    mockView = new IMEViewMock(log);
  }

  @Test
  public void testRun0() {
    StringBuilder expectedOutput = new StringBuilder();

  }
}