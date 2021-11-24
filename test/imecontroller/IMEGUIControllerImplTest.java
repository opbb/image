package imecontroller;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import imecontroller.iguicommand.IGUICommand;
import imemodel.ImageModel;
import imemodel.ImageModelImpl;
import imeview.IMEGUIView;
import imeview.IMEGUIViewMock;

public class IMEGUIControllerImplTest {

  private IMEGUIView view;
  private ImageModel model;
  private IMEGUIController cont;
  private StringBuilder log;
  private Map<String, IGUICommand> map;

  @Before
  public void setUp() {
    log = new StringBuilder();
    view = new IMEGUIViewMock(log);
    model = new ImageModelImpl();
    cont = new IMEGUIControllerImpl(map, model, view);
    view.setController(cont);

  }


  @Test
  public void testLoad() {

    IMEGUIViewMock mock = new IMEGUIViewMock(log);
    mock.fireActionEvent("Load file");
//    cont.actionPerformed();
  }

}