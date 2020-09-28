package ftc.java;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class AutonomousMap extends JFrame {
	//Global Variables:
	private static int red = 127, green = 3, blue = 252, weight = 8;
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
	    setIconImage(new ImageIcon(ImageIO.read(new File("images/AMLogo.jpg"))).getImage());
		  setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("images/FTC-Field.png")))));
		  pack();
	  } 
	  
	  catch (Exception e) {
			//Error Debug:
		  e.printStackTrace();  
	  }

  	//Mouse Interaction:
	  addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			 //Checks Case:
			 if (startClicked == false) {
				 //First Click Setup:
			   startClicked = true;
			   startX = e.getX();
			   startY = e.getY();
			   
			   try {
					 //Prints the Information:
			  	 System.out.println("Selected X: " + startX + ", Selected Y: " + startY);
			   }
			   
			   catch (Exception ex) {
					 //Error Debugs:
			  	 ex.printStackTrace();
			   }
			 }	
			 
			 else if (startClicked == true) {
				 //Calculates the Distance:
			   startClicked = false;
			   finishX = e.getX();			  
			   finishY = e.getY();
				
				 //Pythagorean Setup:
			   leg1 = Math.abs(finishX-startX);
			   leg2 = Math.abs(finishY-startY);
			   distance = Math.hypot(leg1, leg2);
				 
				 //Distance Setup:
			   trueDistance = (distance*ratio);
			   rotations = trueDistance/WHEEL_CIRC;
				 
				 //Distance Calculations:
			   trueDistance = Math.round(trueDistance*100.0)/100.0;
			   rotations = Math.round(rotations*100.0)/100.0;
			   repaint();
			   
			   try {
			  	 //Output:
			  	 System.out.println("Selected X: " + finishX + ", Selected Y: " + finishY);
			  	 System.out.println("Distance: Inches: " + trueDistance + ", Rotations: " + rotations);
			   }
			   
			   catch (Exception ex) {
					 //Error Debugs:
				   ex.printStackTrace();   
			   }
			 }
			}
		});
  }
  
  //Draws Graphics on the Frame:
  public void paint(Graphics g) {  
		//Super Paint:
    super.paint(g);
		Graphics2D g2 = (Graphics2D)(g);
		
		//Draw Setup:
    int X1 = (int)(startX);
    int X2 = (int)(finishX);
    int Y1 = (int)(startY);
    int Y2 = (int)(finishY);
		
		//Draws:
    g2.setColor(new Color(red, green, blue));
    g2.setStroke(new BasicStroke(weight));
    g2.drawLine(X1, Y1, X2, Y2);
  }
}