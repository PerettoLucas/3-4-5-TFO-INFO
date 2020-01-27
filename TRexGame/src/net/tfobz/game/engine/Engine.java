package net.tfobz.game.engine;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.Timer;


public class Engine 
{
	private int score = 0;
	private int highscore = 0;
	public Player player = null;
	public Ground ground = null;
	public ArrayList<Obstacle> obstacles = null;
	private Timer gametimer = null;
	/**
	 * Initialisiert Engine, legt Arraylist aus Hindernissen an.
	 */
	public Engine(int gamespeed) {

		player = new Player(50, 30, 50);
		ground = new Ground();
		obstacles = new ArrayList<>();
		obstacles.add(0, new Obstacle(10000)); //Damit 0te stelle nicht leer ist und foreach keine exception wirft.
		gametimer = new Timer(gamespeed, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ground.moveground();
				if (obstacles.size()>1) {
					for (int i = 1; i < obstacles.size(); i++) {
						obstacles.get(i).moveobstacle();
					} 
				}
			}
		});
		
	}

	/**
	 * Generiert Hindernisse mit zuf�lligen Abstand zwischen einander
	 */
	public void generate_obstacles(int anzahl_obstacles) {
		int distance = 1000;
		for (int i = 0; i < anzahl_obstacles; i++) {
			distance += (int)(Math.random()*300+300);
			obstacles.add(new Obstacle(distance));
		}
		System.out.println("[DEBUG] Arraylist filled");
	}
	/**
	 * Initialisiert Bewegung der Objekte
	 */
	public void initGame() {
		gametimer.start();	
		System.out.println("[DEBUG] Obstacles movement initialized");
	}
	/**
	 * Zeichnet das Gesamte Spiel auf die �bergebene Fl�che
	 * @param g Zeichnfl�che
	 */
	public void draw(Graphics g) {
		ground.paint_ground(g);
		player.paint_Player(g);
		checkdeath();
		try {
			for (Obstacle obstacle : obstacles) {
				//Wenn ein hinderniss den spieler passiert wird ein punkt dazugez�hlt
				check_if_player_is_passed(obstacle);
				
				//Wenn Hinderniss den linken bildschirmrand passiert hat wird es aus dem array entfernt, sonst 
				//wird es gezeichnet.
				if(obstacle.getHitbox().getMaxX()>0) 
					obstacle.paint_obstacle(g);
				else {
					obstacles.remove(1);
				}
			}
		} catch (ConcurrentModificationException e) {
		
		}
	}
	//Wenn ein hinderniss den spieler passiert wird ein punkt dazugez�hlt
	private void check_if_player_is_passed(Obstacle obstacle) {
		
		if(obstacle.getHitbox().getMaxX()<player.getHitbox().getMinX() && !obstacle.isPlayer_passed()) {
			score++;
			if(score>highscore) {
				highscore=score;
			}
			obstacle.setPlayer_passed(true);
			
			int locx = (int) (obstacles.get(obstacles.size()-1).getHitbox().getMaxX() + (int)(Math.random()*300+300));
			Obstacle ob = new Obstacle(locx);
			obstacles.add(ob);
			
			System.out.println("[DEBUG] Obstacle " + obstacles.indexOf(obstacle)+ " passed");
		}
	}
	public int getHighscore() {
		return highscore;
	}

	//Kontrolliert ob der Spieler sirbt
	private void checkdeath() {
		
		if(checkcollision(player.getHitbox(), obstacles.get(1).getHitbox())) {
			player.setDead(true);
			System.out.println("[DEBUG] Player died and collided with " + obstacles.get(1).toString());
			
			for (int i = 1; i < obstacles.size(); i++) {
				obstacles.get(i).setLocx(-1); //Setzt alle Hindernisse hinter dein linken bildschirmrand.
				obstacles.get(i).setPlayer_passed(true);
				System.out.println("[DEBUG] Obstacle "  +i+ ": XPos set to -1" );
			}	
		}
		
	}
	//Kontrolliert Kollision zwischen zwei hitboxen.
	private boolean checkcollision(Rectangle r1, Rectangle r2) {
		return r1.intersects(r2);
	}
	
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void stopgametimer() {
		gametimer.stop();
	}
	
	
	
}
