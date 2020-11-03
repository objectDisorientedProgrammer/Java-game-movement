/**
 * 	Title:		PlayerMovement.java
 * 	Author:		Douglas Chidester
 * 	Created:	October 6, 2012
 * 	Finished:	[10/6/12]
 * 
 * 	Shows how to make an image move across the screen
 * 	when the user presses a key (WASD/UDLR).
 * 
 *	Square image is 96x96 px.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlayerMovement extends JPanel {

	private static int frameWidth = 700;
	private static int frameHeight = 600;
	private int imagePxSize = 45;	// image size
	private Image sqr;
	private int x;
	private int xMax, xMin;
	private int y;
	private int yMax, yMin;
	private int moveSpeed = 10;
	private String imageName = "src/main/resources/images/square.png";
	
	private ImageIcon sq;

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g; // initialize graphics
		g2d.drawImage(sqr, x, y, this); // draw
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public PlayerMovement() {
		xMin = yMin = 0;
		xMax = frameWidth - imagePxSize - 3;		// max width of panel
		yMax = frameHeight - imagePxSize - 23;	// max height of panel
		x = y = 10;	// starting coords

		setBackground(Color.white);
		
		// Load in image
		sq = new ImageIcon(this.getClass().getResource(imageName));

		setDoubleBuffered(true); // better draw quality
		sqr = sq.getImage();		
		
		this.setFocusable(true);	// need this for keyListener
		this.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				
			}

			public void keyReleased(KeyEvent e) {
				
			}

			public void keyPressed(KeyEvent ke) {
				int keyCode = ke.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
					if(y - moveSpeed <= 0) // check if its going off the screen
						y = 0;
					else
						y -= moveSpeed;		// Up
					break;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
					if(y + moveSpeed >= yMax) // check if its going off the screen
						y = yMax;
					else
						y += moveSpeed;		// Down
					break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					if(x - moveSpeed <= 0)			// check if its going off the screen
						x = 0;
					else
						x -= moveSpeed;		// Left
					break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					if(x + moveSpeed >= xMax)			// check if its going off the screen
						x = xMax;
					else
						x += moveSpeed;		// Right
					break;
				}
				repaint();
			}
		});
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("Game");
		f.setSize(frameWidth, frameHeight);
		f.add(new PlayerMovement());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
	}
}
