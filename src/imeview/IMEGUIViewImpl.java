package imeview;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import imecontroller.IMEGUIController;
import imecontroller.iguicommand.IGUICommand;
import imemodel.Formats;
import imemodel.Histogram;
import imemodel.HistogramImpl;
import imemodel.ImageImpl;
import imemodel.ImageModel;


/**
 * This class acts as the GUI view of our image processor. It contains a slew of java swing
 * components from buttons, labels, panels, and more. It delegates what the GUI to do through
 * its buttons and alterations listening to the controller, by which this class merely designs
 * the application/GUI side, knowing when to show a change in the image and histogram when ordered
 * to by the controller.
 */
public class IMEGUIViewImpl extends JFrame implements IMEGUIView {

  private ArrayList<JButton> buttons; // A list of buttons so we can give them all action listeners.
  private ArrayList<JList> lists; // A list of lists so we can give them all action listeners.
  private final ImageModel model;
  private JPanel imagePanel;
  private JPanel histPanel;
  private String imageName;
  private JPanel rightPanel;



  private JLabel imageLabel;
  private JScrollPane imageVerticalScroll;



  private int[][][] pixels;


  JButton loadButton;
  JButton saveButton;

  JList<String> listOfFiles;

  private static final Stroke GRAPH_STROKE = new BasicStroke(2f);

  /**
   * Main and only constructor, takes in a ImageModel object, a map of commands with keys as their
   * action command and the IGUICommand with their execute methods.
   * @param model Image model by which to retrieve needed data from.
   * @param commands A map of IGUICommands, needed so that the buttons can be drawn onto the GUI.
   */
  public IMEGUIViewImpl(ImageModel model, Map<String, IGUICommand> commands) {
    super();
    if (model == null || commands == null) {
      throw new IllegalArgumentException("The arguments must not be null.");
    }

    setTitle("Image Processor");
    setSize(800, 800);
    this.model = model;


    buttons = new ArrayList<JButton>();
    lists = new ArrayList<JList>();

    JPanel mainPanel = new JPanel();
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
          // Remaining letters lowercase.
          displayName = displayName.concat(command.substring(1).toLowerCase());
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
    listOfFiles = new JList<String>();
    listOfFiles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    lists.add(listOfFiles);
    filesPanel.add(listOfFiles);


    imageName = "";
    imageLabel = new JLabel();
    imagePanel = new JPanel();
    imagePanel.add(imageLabel);


    histPanel = new JPanel();

    setUpVertPanel(histPanel, rightPanel);
    setUpVertPanel(imagePanel, rightPanel);


  }

  @Override
  public void updateOpenedFiles() {
    listOfFiles.setListData(model.getKeys().toArray(new String[0]));
  }

  @Override
  public String getFilePath() {
    JFileChooser  fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & PPM & PNG & BMP Images", "ppm", "png", "bmp", "jpg");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      String newName = f.getAbsolutePath();
      return newName;
    } else {
      return "";
    }
  }


  @Override
  public void setUpLoadedImageAndHistogram(String newName) {

    pixels = model.getImageValues(newName);
    BufferedImage bf = Formats.makeBF(newName, pixels);
    removeHist();
    imagePanel.remove(imageVerticalScroll);
    imagePanel.revalidate();
    imagePanel.repaint();
    ImageIcon icon = new ImageIcon(bf);

    imageLabel.setIcon(icon);
    imageVerticalScroll = new JScrollPane(imageLabel);
    imageVerticalScroll.setMaximumSize(new Dimension(500, 500));

    this.imagePanel.add(imageVerticalScroll);
    histPanel = new DrawHist(pixels);

    imageName = newName;
    setUp(histPanel, rightPanel);
    SwingUtilities.updateComponentTreeUI(this);

  }


  @Override
  public void setUpImageAndHistogram(String newName) {

    if (imageName.equals("")) {

      buildImagePanel(newName);
      pixels = model.getImageValues(newName);
      histPanel = new DrawHist(pixels);

      setUp(imagePanel, rightPanel);

      imageName = newName;
      setUp(histPanel, rightPanel);

    } else {

      pixels = model.getImageValues(newName);
      BufferedImage bf = Formats.makeBF(newName, pixels);
      removeHist();
      imagePanel.remove(imageVerticalScroll);
      imagePanel.revalidate();
      imagePanel.repaint();
      ImageIcon icon = new ImageIcon(bf);

      imageLabel.setIcon(icon);
      imageVerticalScroll = new JScrollPane(imageLabel);
      imageVerticalScroll.setMaximumSize(new Dimension(500, 500));

      this.imagePanel.add(imageVerticalScroll);
      histPanel = new DrawHist(pixels);

      imageName = newName;
      setUp(histPanel, rightPanel);
      SwingUtilities.updateComponentTreeUI(this);

    }
  }


  /**
   * This method is used for removing the histogram displayed so that the updated histogram is
   * shown when a filter or change is made.
   */
  private void removeHist() {
    rightPanel.remove(histPanel);

  }


  @Override
  public void buildImagePanel(String filename) {

    //for ppm conversion
    if (filename.substring(filename.lastIndexOf(".") + 1).equals("ppm")) {
      int width;
      int height;
      int maxRGB;

      try {
        FileInputStream file = new FileInputStream(filename);
        Scanner sc = new Scanner(file);
        StringBuilder builder = new StringBuilder();
        //read the file line by line, and populate a string. This will throw away any comment lines
        while (sc.hasNextLine()) {
          String s = sc.nextLine();
          if (s.charAt(0) != '#') {
            builder.append(s + System.lineSeparator());
          }
        }

        //now set up the scanner to read from the string we just built
        sc = new Scanner(builder.toString());

        width = sc.nextInt();
        height = sc.nextInt();
        maxRGB = sc.nextInt();
        BufferedImage img;
        int[] arrayImage = new int[width * height * 3];

        int j = 0;
        while (sc.hasNextInt()) {
          arrayImage[j] = sc.nextInt();
          j++;
        }

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int i = 0;
        for (int k = 0; k < height; k++) {
          for (int p = 0; p < width; p++) {

            int col = new Color(arrayImage[i], arrayImage[i + 1], arrayImage[i + 2]).getRGB();
            img.setRGB(p, k, col);
            i += 3;
          }
        }
        ImageIcon icon = new ImageIcon(img);
        Image img1 = icon.getImage();


        imageLabel.setIcon(new ImageIcon(img1));

        imageVerticalScroll = new JScrollPane(imageLabel);
        imageVerticalScroll.setMaximumSize(new Dimension(500, 500));

        this.imagePanel.add(imageVerticalScroll);

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      //Used for bmp conversion.
    } else if (filename.substring(filename.lastIndexOf(".") + 1).equals("bmp")) {
      imemodel.Image img1 = new ImageImpl(filename);
      int height = img1.getHeight();
      int width = img1.getWidth();
      BufferedImage img = new BufferedImage(width, height,
              BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          img.setRGB(j, i, (img1.getPixels()[i][j][0] << 16) |
                  (img1.getPixels()[i][j][1] << 8) | img1.getPixels()[i][j][2]);
        }
      }
      ImageIcon icon = new ImageIcon(img);
      Image imgage = icon.getImage();


      icon.setImage(imgage);
      imageLabel.setIcon(icon);
      imageVerticalScroll = new JScrollPane(imageLabel);
      imageVerticalScroll.setMaximumSize(new Dimension(500, 500));

      this.imagePanel.add(imageVerticalScroll);

    } else {

      //for jpegs and png display.
      try {
        FileInputStream file = new FileInputStream(filename);
        BufferedImage bf = ImageIO.read(file);
        ImageIcon icon = new ImageIcon(bf);
        Image img = icon.getImage();


        icon.setImage(img);
        imageLabel.setIcon(icon);

        imageVerticalScroll = new JScrollPane(imageLabel);
        imageVerticalScroll.setMaximumSize(new Dimension(500, 500));
        this.imagePanel.add(imageVerticalScroll);

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }


    }
  }


  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
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

  /**
   * Used for making sure the image and histogram are setup correctly after changes have been
   * made to the current image or a new image has been loaded in.
   * @param panel The child panel to be setup.
   * @param parentPanel The parent of this panel to be.
   */
  private void setUp(JPanel panel, JPanel parentPanel) {
    panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
    parentPanel.add(panel);
  }


  @Override
  public void setController(IMEGUIController controller) {
    for (JButton button : buttons) {
      button.addActionListener(controller);
    }
    for (JList list : lists) {
      list.addListSelectionListener(controller);
    }

  }


  /**
   * This class represents a panel of the Histogram to be displayed of every image.
   * It has a set size of 600 pixels wide and 100 pixels tall.
   */
  private class DrawHist extends JPanel {

    private int[][][] img;
    private int width = 600;
    private int height = 100;


    /**
     * Takes a 3d int array representing the pixels of the image. It needs to take a 3d int array
     * so that it is always being fed the correct data, when changed.
     * @param image The 3d int array of pixels.
     */
    public DrawHist(int[][][] image) {

      img = image;
      setMaximumSize(new Dimension(600, 200));


    }


    @Override
    public Dimension getPreferredSize() {
      return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {

      Histogram h = new HistogramImpl(model);

      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      ArrayList<Point> redPoints = new ArrayList<Point>();
      ArrayList<Point> bluePoints = new ArrayList<Point>();
      ArrayList<Point> greenPoints = new ArrayList<Point>();
      ArrayList<Point> intPoints = new ArrayList<Point>();
      int[][] arr = h.getHistogramData(img);

      int maxValue = arr[0][0];

      //finds the max rgb value by which to scale the image down correctly.
      for (int j = 0; j < arr.length; j++) {
        for (int i = 0; i < 3; i++) {
          if (arr[j][0] > maxValue) {
            maxValue = arr[j][0];
          }
          if (arr[j][1] > maxValue) {
            maxValue = arr[j][1];
          }
          if (arr[j][2] > maxValue) {
            maxValue = arr[j][2];
          }
          if (arr[j][3] > maxValue) {
            maxValue = arr[j][3];
          }
        }
      }


      //set scale so it looks nicer within a 600px wide foundation.
      double xScale = 3;

      //uses the max value to determine the y scale factor.
      double yScale = ((double) height / (double) maxValue);

      //iterates through the image and gathers the info for each color and intensity of each pixel.
      for (int i = 0; i < arr.length; i++) {

        int x1 = (int) (i * xScale);
        int r1 = (int) (((maxValue - arr[i][0]) * yScale));
        int g1 = (int) (((maxValue - arr[i][1]) * yScale));
        int b1 = (int) (((maxValue - arr[i][2]) * yScale));
        int int1 = (int) (((maxValue - arr[i][3]) * yScale));
        redPoints.add(new Point(x1, r1));
        bluePoints.add(new Point(x1, b1));
        greenPoints.add(new Point(x1, g1));
        intPoints.add(new Point(x1, int1));

      }





      //draws red components
      g2.setColor(Color.red);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < redPoints.size() - 1; i++) {
        int x1 = redPoints.get(i).x;
        int y1 = redPoints.get(i).y;
        int x2 = redPoints.get(i + 1).x;
        int y2 = redPoints.get(i + 1).y;
        g2.drawLine(x1, y1, x2, y2);
      }

      //draws blue components
      g2.setColor(Color.blue);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < bluePoints.size() - 1; i++) {
        int x1 = bluePoints.get(i).x;
        int y1 = bluePoints.get(i).y;
        int x2 = bluePoints.get(i + 1).x;
        int y2 = bluePoints.get(i + 1).y;
        g2.drawLine(x1, y1, x2, y2);
      }

      //draws green components
      g2.setColor(Color.green);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < greenPoints.size() - 1; i++) {
        int x1 = greenPoints.get(i).x;
        int y1 = greenPoints.get(i).y;
        int x2 = greenPoints.get(i + 1).x;
        int y2 = greenPoints.get(i + 1).y;
        g2.drawLine(x1, y1, x2, y2);
      }

      //draws intensity components
      g2.setColor(Color.darkGray);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < intPoints.size() - 1; i++) {
        int x1 = intPoints.get(i).x;
        int y1 = intPoints.get(i).y;
        int x2 = intPoints.get(i + 1).x;
        int y2 = intPoints.get(i + 1).y;
        g2.drawLine(x1, y1, x2, y2);
      }

      //draws the x and y axis.
      g2.drawLine(0, getHeight() / 2, 0, 0);
      g2.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

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
}





