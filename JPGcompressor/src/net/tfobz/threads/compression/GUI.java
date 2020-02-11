package net.tfobz.threads.compression;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private JPanel contentPane;
	private ImageComponent imageComponent;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		imageComponent = new ImageComponent();
		contentPane.add(imageComponent, BorderLayout.CENTER);
		
		JPanel panelControl = new JPanel();
		contentPane.add(panelControl, BorderLayout.SOUTH);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.setBackground(new Color(240, 240, 240));
		btnOpen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelControl.add(btnOpen);
		
		JSpinner spinner = new JSpinner();
		spinner.setToolTipText("Granulit\u00E4t");
		panelControl.add(spinner);
		
		JButton btnCompressAndSave = new JButton("Compress and Save");
		btnCompressAndSave.setBackground(new Color(240, 240, 240));
		btnCompressAndSave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelControl.add(btnCompressAndSave);
		
		JButton btnCompress = new JButton("Compress");
		btnCompress.setBackground(new Color(240, 240, 240));
		btnCompress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelControl.add(btnCompress);
		
		JProgressBar progressBar = new JProgressBar();
		panelControl.add(progressBar);
		
		
		
		
		
		
		
	}

}
