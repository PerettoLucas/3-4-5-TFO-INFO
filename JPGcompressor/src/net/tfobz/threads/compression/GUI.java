package net.tfobz.threads.compression;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.chrono.JapaneseEra;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private JPanel contentPane;
	private ImageComponent imageComponent;
	private ArrayList<CompressorThread> compressorThreadList = new ArrayList<CompressorThread>();
	private ArrayList<Future<CompressorFutureThread>> compressFuturesList = new ArrayList<>();
	
	
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
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		
		
		imageComponent = new ImageComponent();
		JFileChooser chooser = new JFileChooser();
		
		
		contentPane.add(imageComponent, BorderLayout.CENTER);
		
		JPanel panelControl = new JPanel();
		contentPane.add(panelControl, BorderLayout.SOUTH);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "jpg");
				chooser.setFileFilter(filter);
				
				int returnVal = chooser.showOpenDialog(GUI.this);
				
				if(returnVal == JFileChooser.APPROVE_OPTION) 
				{
					try {
						imageComponent.setImage(chooser.getSelectedFile());
					} catch (IOException e1) {e1.printStackTrace();}
				}
				
			}
		});
		btnOpen.setBackground(new Color(240, 240, 240));
		btnOpen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelControl.add(btnOpen);
		
		SpinnerModel spinnerModel = new SpinnerNumberModel(0.1, 0.1, 1, 0.1);
		
		JSpinner spinner = new JSpinner(spinnerModel);
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
		
		btnCompressAndSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(chooser.getSelectedFile() == null)
				{
					JOptionPane.showMessageDialog(GUI.this, "A JPG must be Opened!");
					return;
				}
				
				btnOpen.setEnabled(false);
				btnCompress.setEnabled(false);
				spinner.setEnabled(false);
				
				String path = JOptionPane.showInputDialog("Path to Save Images to :");
				
				BufferedImage image = null;
				try {
					image = ImageIO.read(chooser.getSelectedFile());
				} catch (IOException e2) {e2.printStackTrace();}
				
				final BufferedImage image_copy = image;
				double spinnervalue = (double) spinner.getValue();
				
				
				
				for (double quality = 0; quality < spinnervalue ; quality+= 0.1) 
				{
					final double quality_copy = Math.round(quality * 100.0) / 100.0;;
					CompressorThread t = new CompressorThread(quality_copy, image_copy, path + chooser.getSelectedFile().getName());
					compressorThreadList.add(t);
				}
				
				for (CompressorThread compressorThread : compressorThreadList) {
					compressorThread.start();
				}
				
				ProgressBarThread progressBarThread = new ProgressBarThread(progressBar, compressorThreadList);
				progressBarThread.start();
				
				try
				{
					progressBarThread.join();
				}catch(InterruptedException e1)
				{e1.printStackTrace();}
				
				spinner.setEnabled(true);
				btnCompress.setEnabled(true);
				btnOpen.setEnabled(true);
				
			}
		});
		
	}

}
// C:\\Users\\lilboy\\Desktop\\SCHULE\\TESTBestImageCompressed\\