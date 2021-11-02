package IMEController;

import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import IMEModel.ImageModel;
import IMEModel.ImageModelMockFalse;
import IMEModel.ImageModelMockTrue;
import IMEView.IMEView;
import IMEView.IMEViewMock;

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