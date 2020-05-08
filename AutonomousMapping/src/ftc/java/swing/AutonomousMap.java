package ftc.java.swing;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class AutonomousMap extends JFrame {
	//Global Variables:
  private static boolean startClicked = false;
  private static double startX, finishX, startY, finishY;
  private static double leg1, leg2, distance, trueDistance, rotations;
  private static double ratio = (144.0/760.0);
  private static double WHEEL_DIAM = 3.9;	
  private static double WHEEL_CIRC = (WHEEL_DIAM * Math.PI);
  
  //Main Method:
  public static void main(String[] args) {
  	//New AutonomousMap Object: 
	  new AutonomousMap();    
  }
  
  //Constructor:
  public AutonomousMap() {
  	try {
  		//Frame Setup:
	    setTitle("Autonomous Mapping");
	    setSize(760, 760);
	    setLayout(null);
	    setVisible(true);
      setLocationRelativeTo(null);
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  
	    //Image Content:
	    setIconImage(new ImageIcon(ImageIO.read(new File("images/LuxonsAMLogo.jpg"))).getImage());
		  setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("images/ftc2020Resized.png")))));
		  pack();
	  } 
	  
	  catch (Exception e) {
		  e.printStackTrace();  
	  }

  	//Mouse Interaction:
	  addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			 //Checks Case:
			 if (startClicked == false) {
			   startClicked = true;
			   
			   startX = e.getX();
			   startY = e.getY();
			   
			   try {
			     runNotification("Selected X: " + startX + ", Selected Y: " + startY, "Autonomous Mapping");
			  	 //System.out.println("Selected X: " + startX + ", Selected Y: " + startY);
			   }
			   
			   catch (Exception ex) {
			  	 ex.printStackTrace();
			   }
			 }	
			 
			 else if (startClicked == true) {
			   startClicked = false;
			   
			   //Calculates Real Time Distance:
			   finishX = e.getX();			  
			   finishY = e.getY();
				  
			   leg1 = Math.abs(finishX-startX);
			   leg2 = Math.abs(finishY-startY);
			   distance = Math.hypot(leg1, leg2);
			   
			   trueDistance = (distance*ratio);
			   rotations = trueDistance/WHEEL_CIRC;
			   
			   trueDistance = Math.round(trueDistance*100.0)/100.0;
			   rotations = Math.round(rotations*100.0)/100.0;
			   repaint();
			   
			   try {
			  	 //Output:
			  	 runNotification("Selected X: " + finishX + ", Selected Y: " + finishY, "Autonomous Mapping");
			     runNotification("Distance (in): " + trueDistance + ", Rotations: " + rotations, "Autonomous Mapping");
			  	 //System.out.println("Selected X: " + finishX + ", Selected Y: " + finishY);
			  	 //System.out.println("Distance (in): " + trueDistance + ", Rotations: " + rotations);
			   }
			   
			   catch (Exception ex) {
				   ex.printStackTrace();   
			   }
			 }
			}

		});
  }
  
  //Draws Graphics on the Frame:
  public void paint(Graphics g) {  
    super.paint(g);
    Graphics2D g2 = (Graphics2D) g;
    int X1 = (int)(startX);
    int X2 = (int)(finishX);
    int Y1 = (int)(startY);
    int Y2 = (int)(finishY);
    
    g2.setColor(Color.GREEN);
    g2.setStroke(new BasicStroke(9));
    g2.drawLine(X1, Y1, X2, Y2);
  }
  
  //Notification Method:
  public static void runNotification(String message, String applicationName) 
  		throws IOException, AWTException, MalformedURLException {
  	//Checks Case:
  	if (SystemTray.isSupported()) {
      //Obtain Instance of the SystemTray:
      SystemTray tray = SystemTray.getSystemTray();

      //Icon Setting:
      Image image = ImageIO.read(new File("images/LuxonsAMLogo.jpg"));

      //Starts Tray Icon:
      TrayIcon trayIcon = new TrayIcon(image, "Main Icon");
      //Let Resize the System Image:
      trayIcon.setImageAutoSize(true);
      //Set ToolTip text for the Tray Icon:
      tray.add(trayIcon);

      //Displays Message:
      trayIcon.displayMessage(message, applicationName, MessageType.INFO);
    } 
  	
  	else {
  	  //Error Debugs:
      System.err.println("System tray not supported!");
    }	
  }
 }