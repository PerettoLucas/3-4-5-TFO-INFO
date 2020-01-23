package net.tfobz.game.engine;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.StyleConstants;


@SuppressWarnings("serial")
public class GUI extends JFrame
{
	JLabel score = new JLabel();
	JLabel yourscore = new JLabel();
	JLabel highscore = new JLabel();
	JButton play = new JButton("Play");
	JButton exit = new JButton("Exit");
	
	private Engine engine = null;
	
	public static int sizex = 0;
	public static int sizey = 0;
	
	BufferedImage maincontainer = null;
	
	private int gamespeed = 20;
	
	
	/**
	 * Initialisiert und startet Hauptfenster
	 */
	public GUI() {
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		sizex = tk.getScreenSize().width;
		sizey = tk.getScreenSize().height;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(sizex, sizey);
		setLocationRelativeTo(null);
		setTitle("T-Rex Game");
		maincontainer = new BufferedImage(sizex, sizey, BufferedImage.TYPE_INT_RGB);
		
		engine = new Engine(gamespeed);
		
		Container contentpane = this.getContentPane();
		
		contentpane.setLayout(null);
		
		score.setText(generate_zeros(engine.getScore())); //Scoreboard wird gesetzt
		score.setBounds(sizex-80, 10, 100, 20);
		yourscore.setFont(new Font(Font.DIALOG, StyleConstants.ALIGN_CENTER, 24));
		yourscore.setBounds(sizex/2-90, sizey/2-100, 200, 30);
		highscore.setFont(new Font(Font.DIALOG, StyleConstants.ALIGN_CENTER, 24));
		highscore.setBounds(sizex/2-125, sizey/2+50, 300, 30);
		play.setBounds(sizex/2-50-60, sizey/2-50, 100, 50);
		play.setFont(new Font(Font.DIALOG, StyleConstants.ALIGN_CENTER, 18));
		exit.setFont(new Font(Font.DIALOG, StyleConstants.ALIGN_CENTER, 18));
		exit.setBounds(sizex/2-50+60, sizey/2-50, 100, 50);
		
		play.setFocusable(false);
		exit.setFocusable(false);
		yourscore.setFocusable(false);
		score.setFocusable(false);
		highscore.setFocusable(false);
		
		contentpane.add(yourscore);
		contentpane.add(highscore);
		contentpane.add(score);
		contentpane.add(play);
		contentpane.add(exit);
		contentpane.setBackground(Color.WHITE);
		
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar()==' ' && !engine.player.isIn_air()) {
					System.out.println("[DEBUG] Space pressed");
					engine.player.jump();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				engine.player.setDead(false);
				engine.generate_obstacles(5);
				engine.initGame();
				engine.setScore(0);
				
				System.out.println("[DEBUG] Play pressed");
			}
		});
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("[DEBUG] Quit pressed");
				System.exit(0);
			}
		});
		
		setVisible(true);
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		draw(maincontainer.getGraphics());
		g.drawImage(maincontainer, 0, 0, sizex, sizey, null);
		repaint();
		
		
	}

	/**
	 * @param g
	 */
	private void draw(Graphics g) {
		super.paint(g);
			
		if(engine.player.isDead()) { //Men�, wenn spieler stirbt oder am Fensterstart
			
			engine.stopgametimer();
			yourscore.setVisible(true);
			score.setVisible(false);
			play.setVisible(true);
			exit.setVisible(true);
			highscore.setVisible(true);
			yourscore.setText("Score: "+generate_zeros(engine.getScore())+engine.getScore());
			highscore.setText("HIGHSCORE: "+generate_zeros(engine.getScore())+engine.getHighscore());
			
		} else { //Wenn Play gedr�ckt wird 
			
			score.setVisible(true);
			yourscore.setVisible(false);
			play.setVisible(false);
			exit.setVisible(false);
			highscore.setVisible(false);
			
			engine.draw(g);
			score.setText(generate_zeros(engine.getScore())+engine.getScore());
			
		}
	}
	
	//Zur Anzeige des Scores
	private String generate_zeros(int score) {
		
		String ret = "";
		
		if(score >= 10)
			if(score >= 100)
				if(score >= 1000)
					if(score >= 10000)
						if(score >= 100000)
							ret="";
						else
							ret="00";
					else
						ret="000";
				else
					ret="0000";
			else
				ret="00000";
		else
			ret="000000";
		
		return ret;
	}

	
	
	
}
