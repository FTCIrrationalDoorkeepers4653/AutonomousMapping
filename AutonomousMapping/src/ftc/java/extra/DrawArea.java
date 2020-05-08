package ftc.java.extra;
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
 
import javax.swing.JComponent;
 
@SuppressWarnings("serial")
public class DrawArea extends JComponent {
 
  // Image in which we're going to draw
  private Image image;
  // Graphics2D object ==> used to draw on
  private Graphics2D g2;
  // Mouse coordinates
  private int currentX, currentY, oldX, oldY;
 
  public DrawArea() {
    setDoubleBuffered(false);
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        //Get X and Y Coordinates:
        oldX = e.getX();
        oldY = e.getY();
      }
    });
 
    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        //Get X and Y Coordinates:
        currentX = e.getX();
        currentY = e.getY();
 
        if (g2 != null) {
          //Line Draw:
          g2.drawLine(oldX, oldY, currentX, currentY);
          // refresh draw area to repaint
          repaint();
          //Get X and Y Coordinates:
          oldX = currentX;
          oldY = currentY;
        }
      }
    });
  }
 
  protected void paintComponent(Graphics g) {
    if (image == null) {
      //Creates the Image:
      image = createImage(getSize().width, getSize().height);
      g2 = (Graphics2D) image.getGraphics();
      //Enable the Highlight:
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      //Clears Space:
      clear();
    }
 
    g.drawImage(image, 0, 0, null);
  }
 
  //Clear the Field:
  public void clear() {
    g2.setPaint(Color.white);
    //Fills the Area:
    g2.fillRect(0, 0, getSize().width, getSize().height);
    g2.setPaint(Color.black);
    repaint();
  }
 
  /* COLOR METHODS */
  
  public void red() {
    g2.setPaint(Color.red);
  }
 
  public void black() {
    g2.setPaint(Color.black);
  }
 
  public void magenta() {
    g2.setPaint(Color.magenta);
  }
 
  public void green() {
    g2.setPaint(Color.green);
  }
 
  public void blue() {
    g2.setPaint(Color.blue);
  }
 
}
