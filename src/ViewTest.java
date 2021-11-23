//package imeview;
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import javax.swing.*;
//
//import imemodel.Histogram;
//import imemodel.HistogramImpl;
//import imemodel.ImageModel;
//import imemodel.ImageModelImpl;
//
//public class ViewTest extends JPanel {
//
//  private final Histogram model;
//  private String type;
//  private static final int MAX_SCORE = 1000;
//  private static final int PREF_W = 1400;
//  private static final int PREF_H = 700;
//  private static final int BORDER_GAP = 30;
//  private static final Color GRAPH_COLOR = Color.green;
//  private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
//  private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
//  private static final int GRAPH_POINT_WIDTH = 12;
//  private static final int Y_HATCH_CNT = 10;
//
//  public ViewTest(ImageModel mod, String type) {
//    mod = new ImageModelImpl("dog", "res/ella.ppm");
//    this.model = new HistogramImpl(mod);
//    this.type = type;
//
//  }
//
//  @Override
//  public Dimension getPreferredSize() {
//    return new Dimension(PREF_W, PREF_H);
//  }
//
//  @Override
//  protected void paintComponent(Graphics g) {
//    super.paintComponent(g);
//    Graphics2D g2 = (Graphics2D) g;
//    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
////    int xScale = 255;
////    int yScale = 1000;
//
//    double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (model.getRedData("dog").length - 1);
//    double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);
//    ArrayList<Point> graphPoints = new ArrayList<Point>();
//    ArrayList<Point> bluePoints = new ArrayList<Point>();
//    ArrayList<Point> greenPoints = new ArrayList<Point>();
//
//
//        for (int i = 0; i < model.getRedData("dog").length; i++) {
//          int x1 = (int) (i * xScale + BORDER_GAP);
//          int y1 = (int) ((MAX_SCORE - model.getRedData("dog")[i]) * yScale + BORDER_GAP);
//          graphPoints.add(new Point(x1, y1));
//        }
//
//        for (int i = 0; i < model.getBlueData("dog").length; i++) {
//          int x1 = (int) (i * xScale + BORDER_GAP);
//          int y1 = (int) ((MAX_SCORE - model.getBlueData("dog")[i]) * yScale + BORDER_GAP);
//          bluePoints.add(new Point(x1, y1));
//        }
//
//
//        for (int i = 0; i < model.getGreenData("dog").length; i++) {
//          int x1 = (int) (i * xScale + BORDER_GAP);
//          int y1 = (int) ((MAX_SCORE - model.getGreenData("dog")[i]) * yScale + BORDER_GAP);
//          greenPoints.add(new Point(x1, y1));
//        }
//
//
//
//
//
//
//    g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
//    g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);
//
//
//    Stroke oldStroke = g2.getStroke();
//
//    g2.setColor(Color.red);
//    g2.setStroke(GRAPH_STROKE);
//    for (int i = 0; i < graphPoints.size() - 1; i++) {
//      int x1 = graphPoints.get(i).x;
//      int y1 = graphPoints.get(i).y;
//      int x2 = graphPoints.get(i + 1).x;
//      int y2 = graphPoints.get(i + 1).y;
//      g2.drawLine(x1, y1, x2, y2);
//    }
//    g2.setColor(Color.blue);
//    g2.setStroke(GRAPH_STROKE);
//    for (int i = 0; i < bluePoints.size() - 1; i++) {
//      int x1 = bluePoints.get(i).x;
//      int y1 = bluePoints.get(i).y;
//      int x2 = bluePoints.get(i + 1).x;
//      int y2 = bluePoints.get(i + 1).y;
//      g2.drawLine(x1, y1, x2, y2);
//    }
//    g2.setColor(Color.green);
//    g2.setStroke(GRAPH_STROKE);
//    for (int i = 0; i < greenPoints.size() - 1; i++) {
//      int x1 = greenPoints.get(i).x;
//      int y1 = greenPoints.get(i).y;
//      int x2 = greenPoints.get(i + 1).x;
//      int y2 = greenPoints.get(i + 1).y;
//      g2.drawLine(x1, y1, x2, y2);
//    }
//
//
//
//  }
//
//  private static void createAndShowGui() {
//
//
//    int maxDataPoints = 16;
//    int maxScore = 20;
//
//    ViewTest mainPanel = new ViewTest(new ImageModelImpl("dog", "res/ella.ppm"), "red");
//    ViewTest blue = new ViewTest(new ImageModelImpl("dog", "res/ella.ppm"), "blue");
//    ViewTest green = new ViewTest(new ImageModelImpl("dog", "res/ella.ppm"), "green");
//
//    JFrame frame = new JFrame("DrawGraph");
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    frame.getContentPane().add(mainPanel);
//    frame.getContentPane().add(blue);
//    frame.getContentPane().add(green);
//    frame.pack();
//    frame.setLocationByPlatform(true);
//    frame.setVisible(true);
//  }
//
//  public static void main(String[] args) {
//    SwingUtilities.invokeLater(new Runnable() {
//      public void run() {
//        createAndShowGui();
//      }
//    });
//  }
//}
