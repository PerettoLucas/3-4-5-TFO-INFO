package net.tfobz.game.engine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Ground extends JPanel
{

	private BufferedImage ground = null;
	public static int GROUND_HEIGHT = GUI.sizey-80;
	private int pos = 0;
	private int speed = 10;

	public Ground() {
		
		try {
			ground = ImageIO.read(this.getClass().getResource("assets\\Ground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	//Zeichnet Boden
	public void paint_ground(Graphics g) {
		
		g.drawImage(ground, 0*ground.getWidth()-pos, GROUND_HEIGHT-20, GUI.sizex, 100, null);
		g.drawImage(ground, 1*ground.getWidth()-pos, GROUND_HEIGHT-20, GUI.sizex, 100, null);
		g.drawImage(ground, 2*ground.getWidth()-pos, GROUND_HEIGHT-20, GUI.sizex, 100, null);
		g.drawImage(ground, 3*ground.getWidth()-pos, GROUND_HEIGHT-20, GUI.sizex, 100, null);
		
	}
	//Bewegt Boden um speed auf player zu
	public void moveground() {
		if(pos <= ground.getWidth())
			pos += speed;
		else
			pos = 0;
		
	}
	
}
