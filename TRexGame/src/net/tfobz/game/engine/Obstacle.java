package net.tfobz.game.engine;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Obstacle extends JPanel
{

	private boolean player_passed = false;
	private Rectangle hitbox = null;
	private int locx = 0;

	private int locy = 0;
	private int sizex = 0;
	private int sizey = 0;
	private int speed = 10;
	private BufferedImage cactus = null;
	
	/**
	 * Instanziert Hinderniss, generiert zuf�llige Groesse.
	 * @param locx
	 */
	public Obstacle(int locx) {
		
		this.locx = locx;
		hitbox = new Rectangle(Randomize(locx));
		
	}
	

	private Rectangle Randomize(int locx) {
		
		int rand = (int) (Math.random()*4+1);
		switch (rand) {
			case 1:
				try {
					cactus = ImageIO.read(this.getClass().getResource("assets\\Cactus-1.png"));
				} catch (IOException e) {
				}
				break;
			case 2:
				try {
					cactus = ImageIO.read(this.getClass().getResource("assets\\Cactus-2.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					cactus = ImageIO.read(this.getClass().getResource("assets\\Cactus-3.png"));
				} catch (IOException e) {}
			case 4:
				try {
					cactus = ImageIO.read(this.getClass().getResource("assets\\Cactus-4.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 5:
				try {
					cactus = ImageIO.read(this.getClass().getResource("assets\\Cactus-5.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
		}
		
		sizex = cactus.getWidth();
		sizey = cactus.getHeight();
		locy = Ground.GROUND_HEIGHT-sizey;
		Rectangle ret = new Rectangle(locx, locy, sizex, sizey);
	
		
		return ret;
	}
	
	@Override
	public String toString() {
		return "Obstacle [hitbox=" + hitbox.toString() + ", speed=" + speed + "]";
	}
	
	
	//Startet einen Thread der im 20 millis takt die posx des Objekts um "speed" verschiebt
	//Darf dur einmal aufgerufen werden.
	@Deprecated
	public void updatePos() {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(hitbox.getMaxX()>0) {
					locx -= speed;
					hitbox.setBounds(locx, locy, sizex, sizey);
//					System.out.println("[DEBUG]: Updated Pos (X/Y)" + locx +" / " + locy );
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
				}
			}
		});
		t.start();
		
	}

	public void moveobstacle() {
		
		locx -= speed;
		hitbox.setBounds(locx, locy, sizex, sizey);
		
	}
	
	
	//Zeichnet Objekt auf die �bergebene Fl�che
	public void paint_obstacle(Graphics g) {
		g.drawImage(cactus, locx, locy, sizex, sizey, null);
	}

	public boolean isPlayer_passed() {
		return player_passed;
	}

	public void setPlayer_passed(boolean player_passed) {
		this.player_passed = player_passed;
	}
	public int getLocx() {
		return locx;
	}
	
	public void setLocx(int locx) {
		this.locx = locx-sizex;
	}
	public Rectangle getHitbox() {
		return hitbox;
	}
}
