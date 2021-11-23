package imeview;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.ImageObserver;
import java.awt.image.Raster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import imecontroller.IMEGUIController;
import imecontroller.iguicommand.IGUICommand;
import imemodel.Formats;
import imemodel.Histogram;
import imemodel.HistogramImpl;
import imemodel.ImageImpl;
import imemodel.ImageModel;

public class IMEGUIViewImpl extends JFrame implements IMEGUIView {

  private JPanel mainPanel;
  private ArrayList<JButton> buttons; // A list of buttons so we can give them all action listeners.
  private ArrayList<JList> lists; // A list of lists so we can give them all action listeners.
  private final Map<String, IGUICommand> commands; // Commands to have buttons for.
  private final ImageModel model;
  private JPanel imagePanel;
  private JPanel histPanel;
  private JFileChooser fchooser;
  private String imageName;
  private JPanel rightPanel;
  private boolean opened;

  private JLabel imageLabel;


  JButton loadButton;
  JButton saveButton;

  JList<String> listOfFiles;

  private static final Stroke GRAPH_STROKE = new BasicStroke(2f);

  public IMEGUIViewImpl(ImageModel model, Map<String, IGUICommand> commands) {
    super();
    setTitle("Image Processor");
    setSize(800, 800);
    this.model = model;


    buttons = new ArrayList<JButton>();
    lists = new ArrayList<JList>();
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
    DefaultListModel<String> openFiles = new DefaultListModel<>();
    for (String file : model.getKeys()) {
      openFiles.addElement(file);
    }
    listOfFiles = new JList<>();
    listOfFiles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    lists.add(listOfFiles);
    updateOpenedFiles();
    filesPanel.add(new JScrollPane(listOfFiles));


    imageName = "";
    imageLabel = new JLabel();
    imagePanel = new JPanel();
    imagePanel.add(imageLabel);
    JScrollPane imageScroll = new JScrollPane(imagePanel);
   // add(imageScroll);
    rightPanel.add(imageScroll);

    histPanel = new JPanel();

    setUpVertPanel(histPanel, rightPanel);
    setUpVertPanel(imagePanel, rightPanel);


  }

  @Override
  public String getFilePath() {
    fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & PPM & PNG & BMP Images", "ppm", "png", "bmp", "jpg");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      String newName = f.getAbsolutePath();
      return newName;
    }
    else {
      return "";
    }
  }

  @Override
  public void setUpLoadedImageAndHistogram(String newName) {

    BufferedImage bf = Formats.makeBF(newName, model.getImageValues(newName));
    removeHist();
    imagePanel.remove(imageLabel);
    imagePanel.revalidate();
    imagePanel.repaint();
    ImageIcon icon = new ImageIcon(bf);
    Image img = icon.getImage();
    Image resi = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);


    //imageLabel.setIcon(new ImageIcon(resi));
    icon.setImage(resi);
    imageLabel.setIcon(icon);


    this.imagePanel.add(imageLabel);


    histPanel = new DrawHist(newName);

    setUpVertPanel(imagePanel, rightPanel);

    imageName = newName;
    setUpVertPanel(histPanel, rightPanel);
  }

  @Override
  public void updateOpenedFiles() {
    listOfFiles.setListData(model.getKeys().toArray(new String[0]));
  }


  @Override
  public void setUpImageAndHistogram(String newName) {
//    fchooser = new JFileChooser("");
//    FileNameExtensionFilter filter = new FileNameExtensionFilter(
//            "JPG & PPM & PNG & BMP Images", "ppm", "png", "bmp", "jpg");
//    fchooser.setFileFilter(filter);
//    int retvalue = fchooser.showOpenDialog(this);
//    if (retvalue == JFileChooser.APPROVE_OPTION) {
//      File f = fchooser.getSelectedFile();
//      String newName = f.getAbsolutePath();
//


      if (imageName.equals("")) {


        buildImagePanel(newName);
        histPanel = new DrawHist(newName);

        buildImagePanel(newName);


        setUpVertPanel(imagePanel, rightPanel);

        imageName = newName;
        setUpVertPanel(histPanel, rightPanel);
      } else {
        removeHist();
        imagePanel.remove(imageLabel);
        imagePanel.revalidate();
        imagePanel.repaint();
        buildImagePanel(newName);


        histPanel = new DrawHist(newName);

        setUpVertPanel(imagePanel, rightPanel);

        imageName = newName;
        setUpVertPanel(histPanel, rightPanel);
      }

    }




  private void removeHist() {
    rightPanel.remove(histPanel);

  }


  @Override
  public void buildHistPanel(String filename) {
    model.loadImage(filename, new ImageImpl(filename));
    histPanel = new DrawHist(filename);


  }

  @Override
  public JComponent getMainComponent() {
    return null;
  }

  @Override
  public void buildImagePanel(String filename) {


    if (filename.substring(filename.lastIndexOf(".") + 1).equals("ppm")) {

      int width, height, maxRGB;

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

        String token;

        token = sc.next();


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
        Image resi = img1.getScaledInstance(500, 500, Image.SCALE_SMOOTH);

        imageLabel.setIcon(new ImageIcon(img1));

        icon.setImage(img1);
        imageLabel.setIcon(icon);


        this.imagePanel.add(imageLabel);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
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
      Image resi = imgage.getScaledInstance(500, 500, Image.SCALE_SMOOTH);


      //imageLabel.setIcon(new ImageIcon(resi));
      icon.setImage(resi);
      imageLabel.setIcon(icon);


      this.imagePanel.add(imageLabel);
    } else {
      ImageIcon icon = new ImageIcon(filename);
      Image img = icon.getImage();
      Image resi = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);


      //imageLabel.setIcon(new ImageIcon(resi));
      icon.setImage(resi);
      imageLabel.setIcon(icon);


      this.imagePanel.add(imageLabel);


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


  @Override
  public void setController(IMEGUIController controller) {
    for (JButton button : buttons) {
      button.addActionListener(controller);
    }
    for (JList list : lists) {
      list.addListSelectionListener(controller);
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


      Histogram h = new HistogramImpl(model);
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


      ArrayList<Point> graphPoints = new ArrayList<Point>();
      ArrayList<Point> bluePoints = new ArrayList<Point>();
      ArrayList<Point> greenPoints = new ArrayList<Point>();
      ArrayList<Point> intPoints = new ArrayList<Point>();
      int[][] arr = h.getHistogramData(imageName);

      int maxValue = arr[0][0];

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


      double xScale = 3;
      double yScale = ((double) height / (double) maxValue);


      for (int i = 0; i < h.getHistogramData(name).length; i++) {

        int x1 = (int) (i * xScale);
        int r1 = (int) (((maxValue - arr[i][0]) * yScale));
        int g1 = (int) (((maxValue - arr[i][1]) * yScale));
        int b1 = (int) (((maxValue - arr[i][2]) * yScale));
        int int1 = (int) (((maxValue - arr[i][3]) * yScale));
        graphPoints.add(new Point(x1, r1));
        bluePoints.add(new Point(x1, b1));
        greenPoints.add(new Point(x1, g1));
        intPoints.add(new Point(x1, int1));

      }




      g2.drawLine(0, getHeight(), 0, 0);
      g2.drawLine(0, getHeight(), getWidth(), getHeight());


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

      g2.setColor(Color.darkGray);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < intPoints.size() - 1; i++) {
        int x1 = intPoints.get(i).x;
        int y1 = intPoints.get(i).y;
        int x2 = intPoints.get(i + 1).x;
        int y2 = intPoints.get(i + 1).y;
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
}





