package imeview;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import imecontroller.IMEGUIController;
import imecontroller.icommand.ICommand;
import imemodel.Formats;
import imemodel.Histogram;
import imemodel.HistogramImpl;
import imemodel.ImageImpl;
import imemodel.ImageModel;

public class IMEGUIViewImpl extends JFrame implements IMEGUIView, ActionListener {

  private JPanel mainPanel;
  private ArrayList<JButton> buttons; // A list of buttons so we can give them all action listeners.
  private final Map<String, ICommand> commands; // Commands to have buttons for.
  private final ImageModel model;
  private JPanel imagePanel;
  private JPanel histPanel;
  private JFileChooser fchooser;
  private String imageName = "/Users/thomasgrbic/Desktop/ood-work/group/ime/res/ella.png";
  private JPanel rightPanel;
  private boolean opened;

  private JLabel imageLabel;


  JButton loadButton;
  JButton saveButton;

  private static final Stroke GRAPH_STROKE = new BasicStroke(2f);

  public IMEGUIViewImpl(ImageModel model, Map<String, ICommand> commands) {
    super();
    setTitle("Image Processor");
    setSize(800, 800);
    this.model = model;


    imageLabel = new JLabel();
    ImageIcon icon = new ImageIcon(imageName);
    Image img = icon.getImage();
    Image resi = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);


    imageLabel.setIcon(new ImageIcon(resi));

    imagePanel = new JPanel();
    imagePanel.add(imageLabel);

    histPanel = new DrawHist(imageName);



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

    rightPanel = new JPanel();
    setUpVertPanel(rightPanel, mainPanel);


    // All user interaction happens on the left side of the screen.
    // The I/O panel contains a save and a load button.
    JPanel inOutPanel = new JPanel();
    setUpVertPanel(inOutPanel, leftPanel);
    inOutPanel.setBorder(BorderFactory.createTitledBorder("In/Out"));
    loadButton = new JButton("Load");
    loadButton.setActionCommand("Load file");
    loadButton.addActionListener(this);
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
        String displayName = command.substring(0, 1).toUpperCase(); // First letter uppercase.
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


//    buildImagePanel("res/ella.png");
    setUpVertPanel(imagePanel, rightPanel);
    setUpVertPanel(histPanel, rightPanel);


//    imagePanel = new JPanel();
//    histPanel = new JPanel();

//    JButton showImage = new JButton("Show Image");
//    JButton showHistogram = new JButton("Show Histogram");
//    showImage.addActionListener(this);
//    showHistogram.addActionListener(this);
//    imagePanel.add(showImage);
//    histPanel.add(showHistogram);
//    setUpVertPanel(imagePanel, rightPanel);
//    setUpVertPanel(histPanel, rightPanel);




    //buildImagePanel(imageName);


    //setUpVertPanel(imagePanel, rightPanel);

    //   buildHistPanel("/Users/thomasgrbic/Desktop/ood-work/group/ime/res/test-input.png");
    //  setUpVertPanel(histPanel, rightPanel);

    // The image and histogram are displayed on the right.


//     buildImagePanel(imageName);
//    setUpVertPanel(imagePanel, rightPanel);
//    try {
//      BufferedImage bf = ImageIO.read(new File("res/ella.png"));
//
//
//      JLabel imageLabel = new JLabel();
//      ImageIcon icon = new ImageIcon(bf);
//      Image img = icon.getImage();
//      Image resi = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
//
//      //imageLabel.setPreferredSize(new Dimension(500, 500));
//      imageLabel.setIcon(new ImageIcon(resi));
//
//      JPanel imagePanel = new JPanel();
//
//      imagePanel.add(imageLabel);
//
//      setUpVertPanel(imagePanel, rightPanel);
//
//    } catch (IOException e) {
//      e.printStackTrace();
//    }


//    buildHistPanel(imageName);

//    setUpVertPanel(histPanel, rightPanel);
//    JPanel histogramPanel = new DrawHist("res/ella.png");
//    setUpVertPanel(histogramPanel, rightPanel);


  }

  //private class FileListener implements ActionListener {


  @Override
  public void actionPerformed(ActionEvent e) {
    fchooser = new JFileChooser("");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & PPM & PNG & BMP Images", "ppm", "png", "bmp", "jpg");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      String newName = f.getAbsolutePath();
      model.loadImage(newName, new ImageImpl(newName, ""));

      // buildHistPanel(imageName);

      buildImagePanel(newName);

      JPanel newHist = new DrawHist(newName);
      histPanel = newHist;

      setUpVertPanel(imagePanel, rightPanel);

      imageName = newName;
      setUpVertPanel(histPanel, rightPanel);




    }

  }



  @Override
  public void buildHistPanel(String filename) {
    model.loadImage(filename, new ImageImpl(filename, ""));
    histPanel = new DrawHist(filename);


  }

  @Override
  public JComponent getMainComponent() {
    return null;
  }

  @Override
  public void buildImagePanel(String filename) {

    // BufferedImage bf = ImageIO.read(new File(filename));
    imageLabel = new JLabel();
    imagePanel.revalidate();
    imagePanel.repaint();
    ImageIcon icon = new ImageIcon(filename);
    Image img = icon.getImage();
    Image resi = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);

    imageLabel.setIcon(new ImageIcon(resi));
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
  }


  /**
   * Sets up a JPanel with a Page axis box layout.
   *
   * @param panel       the panel to be set up
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


  private class DrawHist extends JPanel {

    private String name;
    private int width = 600;
    private int height = 100;

    public DrawHist(String filename) {
      name = filename;
      setMaximumSize(new Dimension(width, height));


    }


    @Override
    public Dimension getPreferredSize() {
      return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {

      model.loadImage(imageName, new ImageImpl(imageName, ""));

      Histogram h = new HistogramImpl(model);



      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


      int MAX_SCORE = 300;

      int BORDER_GAP = 5;

//      double xScale = ((double) getWidth() - (2 * BORDER_GAP) - BORDER_GAP) / (256 - 1);
//      double yScale = ((double) getHeight() - 2 * BORDER_GAP - BORDER_GAP) / (300 - 0);
      double xScale = 3;
      double yScale = .0001;
//      double xScale = ((double) 800 - 2 * BORDER_GAP) / (h.getRedData(name).length - 1);
//      double yScale = ((double) 800 - 2 * BORDER_GAP) / (MAX_SCORE - 1);
//      double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (h.getRedData(name).length - 1);
//      double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

      ArrayList<Point> graphPoints = new ArrayList<Point>();
      ArrayList<Point> bluePoints = new ArrayList<Point>();
      ArrayList<Point> greenPoints = new ArrayList<Point>();

      /*
      for (int i = 0; i < h.getRedData(name).length; i++) {
        int x1 = (int) (i * xScale);
        int r1 = (int) ((h.getRedData(name)[i][0]) * yScale);
        int g1 = (int) ((h.getRedData(name)[i][1]) * yScale);
        int b1 = (int) ((h.getRedData(name)[i][2]) * yScale);
        int int1 = (int) ((h.getRedData(name)[i][3]) * yScale);
        graphPoints.add(new Point(x1, r1));
        bluePoints.add(new Point(x1, b1));
        greenPoints.add(new Point(x1, g1));
//        bluePoints.add(new Point(x1, b1));

      }


       */

//      for (int i = 0; i < h.getRedData(name).length; i++) {
//        int x1 = (int) (i * xScale);
//        int y1 = (int) ((h.getRedData(name)[i]) * yScale);
//        graphPoints.add(new Point(x1, y1));
//      }
//
//      for (int i = 0; i < h.getBlueData(name).length; i++) {
//        int x1 = (int) (i * xScale);
//        int y1 = (int) ((h.getBlueData(name)[i]) * yScale);
//        bluePoints.add(new Point(x1, y1));
//      }
//
//
//      for (int i = 0; i < h.getGreenData(name).length; i++) {
//        int x1 = (int) (i * xScale);
//        int y1 = (int) ((h.getGreenData(name)[i]) * yScale);
//        greenPoints.add(new Point(x1, y1));
//      }


//      g2.drawLine(BORDER_GAP, 800 - BORDER_GAP, BORDER_GAP, BORDER_GAP);
//      g2.drawLine(BORDER_GAP, 800 - BORDER_GAP, 800 - BORDER_GAP, 800 - BORDER_GAP);
      g2.drawLine(0, getHeight(), 0, 0);
      g2.drawLine(0, getHeight(), getWidth(), getHeight());


      Stroke oldStroke = g2.getStroke();

      g2.setColor(Color.red);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < graphPoints.size() - 1; i++) {
        int x1 = graphPoints.get(i).x;
        int y1 = graphPoints.get(i).y;
        int x2 = graphPoints.get(i + 1).x;
        int y2 = graphPoints.get(i + 1).y;
        g2.drawLine(x1, y1, x2, y2);
      }
      g2.setColor(Color.blue);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < bluePoints.size() - 1; i++) {
        int x1 = bluePoints.get(i).x;
        int y1 = bluePoints.get(i).y;
        int x2 = bluePoints.get(i + 1).x;
        int y2 = bluePoints.get(i + 1).y;
        g2.drawLine(x1, y1, x2, y2);
      }
      g2.setColor(Color.green);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < greenPoints.size() - 1; i++) {
        int x1 = greenPoints.get(i).x;
        int y1 = greenPoints.get(i).y;
        int x2 = greenPoints.get(i + 1).x;
        int y2 = greenPoints.get(i + 1).y;
        g2.drawLine(x1, y1, x2, y2);
      }


    }


  }

  @Override
  public double getDoubleInput(String message, double def) {
    String rawInput = JOptionPane.showInputDialog(this, message);
    if (rawInput == null) {
      return def;
    }
    try {
      return Integer.valueOf(rawInput);
    } catch (NumberFormatException e) {
      renderMessage("The given input was invalid, please input a number.");
      return getDoubleInput(message, def);
    }
  }

  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }
}



