package net.tfobz.game.engine;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Player extends JPanel
{
	private Rectangle hitbox = null;
	private int JUMP_PIX_PER_STEPS = 10;
	private boolean in_air = false;
	private boolean dead = true;
	private BufferedImage dino = null;
	private BufferedImage[] dinosprite = null;
	private int animation_state = 0;
	private int rootpos_x = 0;
	private int rootpos_y = 0;
	private Timer timer = null;

	public boolean isDead() {
		return dead;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public boolean isIn_air() {
		return in_air;
	}

	public void setIn_air(boolean in_air) {
		this.in_air = in_air;
	}

	/**
	 * Initialisiert Spieler
	 * @param x Position Spieler
	 * @param y Position Spieler
	 * @param width Breite Spieler
	 * @param height Höhe Spieler
	 */
	public Player(int x, int width, int height) {

		dinosprite = new BufferedImage[3];
		laodspritesheet();
		rootpos_x = x;
		rootpos_y = Ground.GROUND_HEIGHT-height;
		hitbox = new Rectangle(rootpos_x, rootpos_y, dino.getWidth(), dino.getHeight()); 
		ActionListener animate = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (animation_state) {
				case 1:
					dino = dinosprite[animation_state];
					animation_state = 2;
					break;
				case 2:
					dino = dinosprite[animation_state];
					animation_state = 1;
					break;

				default:
					break;
				}
			}
		};
		timer = new Timer(100, animate);
		timer.start();
	}

	/**
	 * L�dt die einzellnen Bilder des Spielers in ein Feld.
	 */
	private void laodspritesheet() {
		
		try {
			dinosprite[0] = ImageIO.read(this.getClass().getResource("assets\\Dino-stand.png"));
			dinosprite[1] = ImageIO.read(this.getClass().getResource("assets\\Dino-left-up.png"));
			dinosprite[2] = ImageIO.read(this.getClass().getResource("assets\\Dino-right-up.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		dino = dinosprite[animation_state];
		animation_state++;
	}
	
	//Zeichnet spieler auf die �bergebene Fl�che
	public void paint_Player(Graphics g) {

		g.drawImage(dino, hitbox.x, hitbox.y, hitbox.width, hitbox.height, null);

	}

	/**
	 * Aktualisiert die POS des Spielers.
	 * @param x Position des Spielers
	 * @param y Position des Spielers
	 */
	public void updatePos(int x, int y) {
		hitbox.setLocation( x , y );
	}
	
	/**
	 * Updatet alle 20 millis die y pos des spielers und lässt ihn "springen"
	 */
	public void jump() {
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				in_air = true;
				timer.stop();
				for( int i = 0; i <= 15; i++) {
					updatePos(hitbox.x, hitbox.y - JUMP_PIX_PER_STEPS);
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				for( int i = 0; i <= 15; i++) {
					updatePos(hitbox.x, hitbox.y  + JUMP_PIX_PER_STEPS);
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				in_air = false;
				timer.start();
			}
		});
		t.start();
		System.out.println("[DEBUG] Player jumped");
	}
}
