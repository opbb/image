package imeview;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;

import imecontroller.IMEGUIController;
import imecontroller.icommand.ICommand;
import imemodel.ImageModel;

public class IMEGUIViewImpl extends JFrame implements IMEGUIView {

  private JPanel mainPanel;
  private ArrayList<JButton> buttons; // A list of buttons so we can give them all action listeners.
  private final Map<String, ICommand> commands; // Commands to have buttons for.

  JButton loadButton;
  JButton saveButton;

  public IMEGUIViewImpl(ImageModel model, Map<String, ICommand> commands) {
    super();
    setTitle("Image Processor");
    setSize(400, 400);

    buttons = new ArrayList<JButton>();
    this.commands = commands;

    mainPanel = new JPanel();
    // The main panel will be separated into a left and right half by sub-panels.
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
    //scroll bars around this main panel.
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);


    // These two panels separate the interactive UI on the left,
    // and the image/histogram on the right.
    JPanel leftPanel = new JPanel();
    setUpVertPanel(leftPanel, mainPanel);

    JPanel rightPanel = new JPanel();
    setUpVertPanel(rightPanel, mainPanel);


    // All user interaction happens on the left side of the screen.
    // The I/O panel contains a save and a load button.
    JPanel inOutPanel = new JPanel();
    setUpVertPanel(inOutPanel, leftPanel);
    inOutPanel.setBorder(BorderFactory.createTitledBorder("In/Out"));
    loadButton = new JButton("Load");
    loadButton.setActionCommand("Load file");
    buttons.add(loadButton);
    inOutPanel.add(loadButton);
    saveButton = new JButton("Save");
    saveButton.setActionCommand("Save file");
    buttons.add(saveButton);
    inOutPanel.add(saveButton);

    JPanel commandsPanel = new JPanel();
    setUpVertPanel(commandsPanel, leftPanel);
    commandsPanel.setBorder(BorderFactory.createTitledBorder("Image Modifiers"));

    for (String command : commands.keySet()) {
      if (command.length() > 0) {
        String displayName = command.substring(0,1).toUpperCase(); // First letter uppercase.
        if (command.length() > 1) {
          displayName = displayName.concat(command.substring(1).toLowerCase()); // Remaining letters lowercase.
        }
        JButton button = new JButton(displayName);
        button.setActionCommand(command);
        buttons.add(button);
        commandsPanel.add(button);
      }
    }

    JPanel filesPanel = new JPanel();
    setUpVertPanel(filesPanel, leftPanel);
    filesPanel.setBorder(BorderFactory.createTitledBorder("Open Images"));
    filesPanel.add(new JButton("files"));


    // The image and histogram are displayed on the right.
    JPanel imagePanel = new JPanel();
    setUpVertPanel(imagePanel, rightPanel);
    imagePanel.add(new JButton("Image"));

    JPanel histogramPanel = new JPanel();
    setUpVertPanel(histogramPanel, rightPanel);
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    histogramPanel.add(new JButton("histogram"));
  }

  @Override
  public void renderMessage(String message) {


  }

  /**
   * Sets up a JPanel with a Page axis box layout.
   * @param panel the panel to be set up
   * @param parentPanel the parent of this panel
   */
  private void setUpVertPanel(JPanel panel, JPanel parentPanel) {
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    parentPanel.add(panel);
  }

  @Override
  public void setController(IMEGUIController controller) {
    for (JButton button : buttons) {
      button.addActionListener(controller);
    }
  }
}
