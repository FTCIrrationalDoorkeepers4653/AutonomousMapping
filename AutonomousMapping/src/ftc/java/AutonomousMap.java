package ftc.java;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class AutonomousMap extends JFrame {
	/* MAPPING VARIABLES */

	//Global Objects:
	private static JFrame frame = new JFrame();
	private static JLabel coordinateOutput = new JLabel("Click to Begin!");
	private static JLabel distanceOutput = new JLabel();
	private static JLabel thetaOutput = new JLabel();

	//Global Variables:
	private static int red = 127, green = 3, blue = 252, weight = 8, textSize = 25;
	private static boolean startClicked = false;
	private static double startX = 0, finishX = 0, startY = 0, finishY = 0;
	private static double x = 0, y = 0, infoX = 0, infoY = 0;
	private static double leg1 = 0, leg2 = 0, distance = 0;
	private static double trueDistance = 0, rotations = 0, round = 100.0;
	private static double ratio = (144.0 / 760.0), theta = 0;
	private static double WHEEL_DIAM = 3.9;
	private static double WHEEL_CIRC = (WHEEL_DIAM * Math.PI);

	/* MAPPING APPLICATION METHODS */

	//Main Method:
	public static void main(String[] args) throws IOException {
		//Start Application: 
		new AutonomousMap();
		setInfoFrame();
	}

	//Constructor:
	public AutonomousMap() {
		try {
			//Frame Setup:
			setTitle("AM");
			setSize(760, 760);
			setLocationRelativeTo(null);
			setUndecorated(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
			setLayout(null);
			setVisible(true);

			//Mapping Content:
			setIconImage(new ImageIcon(ImageIO.read(new File("images/AMLogo.jpg"))).getImage());
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("images/FTC-Field.png")))));
			pack();
		}

		catch (Exception e) {
			//Error Debug:
			e.printStackTrace();
		}

		//Mouse Click:
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
						//Output:
						coordinateOutput.setText("Selected X: " + startX + ", Selected Y: " + startY);
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
					leg1 = Math.abs(finishX - startX);
					leg2 = Math.abs(finishY - startY);
					distance = Math.hypot(leg1, leg2);

					//Distance Output Setup:
					trueDistance = (distance * ratio);
					rotations = trueDistance / WHEEL_CIRC;
					theta = (Math.round(convertAngle(Math.atan2(leg1, leg2), true) * round) / round);

					//Distance Calculations:
					trueDistance = Math.round(trueDistance * 100.0) / 100.0;
					rotations = Math.round(rotations * 100.0) / 100.0;
					repaint();

					try {
						//Output:
						coordinateOutput.setText("Selected X: " + finishX + ", Selected Y: " + finishY);
						distanceOutput.setText("Distance: " + trueDistance + " Inches , Rotations: " + rotations);
						thetaOutput.setText("Angle: " + theta + " Degrees");
					}

					catch (Exception ex) {
						//Error Debugs:
						ex.printStackTrace();
					}
				}
			}
		});

		//Mouse Press Listener:
		addMouseListener(new MouseAdapter() {
			//Press of Mouse:
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
		});

		//Adds Mouse Drag Listener:
		addMouseMotionListener(new MouseAdapter() {
			//Drags the Mouse:
			public void mouseDragged(MouseEvent e) {
				//Moves the JFrame:
				setLocation((int) (e.getXOnScreen() - x), (int) (e.getYOnScreen() - y));
			}
		});

		//Key Listener:
		addKeyListener(new KeyAdapter() {
			//On Key Press:
			public void keyPressed(KeyEvent e) {
				//Checks the Case:
				if (e.getKeyCode() == KeyEvent.VK_E) {
					//Exits:
					System.exit(0);
				}
			}
		});
	}

	/* MAPPING APPLICATION EXTENSION METHODS */

	//Information Frame Settings:
	public static void setInfoFrame() throws IOException {
		//Frame Setup:
		setOutput();
		frame.setTitle("AM");
		frame.setSize(760, 120);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setIconImage(new ImageIcon(ImageIO.read(new File("images/AMLogo.jpg"))).getImage());
		frame.setVisible(true);

		//Mouse Press Listener:
		frame.addMouseListener(new MouseAdapter() {
			//Press of Mouse:
			public void mousePressed(MouseEvent e) {
				infoX = e.getX();
				infoY = e.getY();
			}
		});

		//Adds Mouse Drag Listener:
		frame.addMouseMotionListener(new MouseAdapter() {
			//Drags the Mouse:
			public void mouseDragged(MouseEvent e) {
				//Moves the JFrame:
				frame.setLocation((int) (e.getXOnScreen() - infoX), (int) (e.getYOnScreen() - infoY));
			}
		});

		//Key Listener:
		frame.addKeyListener(new KeyAdapter() {
			//On Key Press:
			public void keyPressed(KeyEvent e) {
				//Checks the Case:
				if (e.getKeyCode() == KeyEvent.VK_E) {
					//Exits:
					System.exit(0);
				}
			}
		});
	}

	//Output Settings Method:
	public static void setOutput() {
		//Sets the Foregrounds:
		coordinateOutput.setForeground(new Color(red, green, blue));
		distanceOutput.setForeground(new Color(0, 0, 0));
		thetaOutput.setForeground(new Color(red, green, blue));

		//Sets the Fonts:
		coordinateOutput.setFont(new Font("Arial", Font.PLAIN, textSize));
		distanceOutput.setFont(new Font("Arial", Font.PLAIN, textSize));
		thetaOutput.setFont(new Font("Arial", Font.PLAIN, textSize));

		//Sets the Bounds:
		coordinateOutput.setBounds(10, 0, 760, 40);
		distanceOutput.setBounds(10, 40, 760, 40);
		thetaOutput.setBounds(10, 80, 760, 40);

		//Adds to Frame:
		frame.add(coordinateOutput);
		frame.add(distanceOutput);
		frame.add(thetaOutput);
	}

	/* MAPPING UTILITY METHODS */

	//Angle Conversion Method:
	public static double convertAngle(double angle, boolean degrees) {
		//Degrees Double:
		double angleDegrees = ((180.0 / Math.PI) * angle);
		double angleRadians = ((Math.PI / 180.0) * angle);

		//Checks the Case:
		if (degrees) {
			//Returns the Angle:
			return angleDegrees;
		}

		else {
			//Returns the Angle:
			return angleRadians;
		}
	}

	//Draws Graphics on the Frame:
	public void paint(Graphics g) {
		//Super Paint:
		super.paint(g);
		Graphics2D g2 = (Graphics2D) (g);

		//Draw Setup:
		int X1 = (int) (startX);
		int X2 = (int) (finishX);
		int Y1 = (int) (startY);
		int Y2 = (int) (finishY);

		//Draws:
		g2.setColor(new Color(red, green, blue));
		g2.setStroke(new BasicStroke(weight));
		g2.drawLine(X1, Y1, X2, Y2);
	}
}