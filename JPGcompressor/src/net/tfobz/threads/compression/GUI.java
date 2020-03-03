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
import java.lang.Thread.State;
import java.lang.reflect.InvocationTargetException;
import java.time.chrono.JapaneseEra;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private JPanel contentPane;
	private ImageComponent imageComponent;
	private JPGImageCompress jpgImageCompress;
	private ArrayList<Thread> compressorThreadList = new ArrayList<Thread>();
	
	private java.util.List<Future<BufferedImage>> compressFuturesList = new ArrayList<Future<BufferedImage>>();
	private ArrayList<Callable<BufferedImage>> compressCallableList = new ArrayList<Callable<BufferedImage>>();
	private ExecutorService executor = Executors.newCachedThreadPool();
	
	private ExecutorService mainExecutor = Executors.newSingleThreadExecutor();
	
	
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
		jpgImageCompress = new JPGImageCompress();
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
					final double quality_copy = Math.round(quality * 100.0) / 100.0;
					
					Thread t = new Thread(new Runnable()
					{
						@SuppressWarnings("static-access")
						@Override
						public void run()
						{
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e1) {e1.printStackTrace();}
							
							
							try {
								jpgImageCompress.compressImage(image_copy, path + ".", quality_copy);
							} catch (IOException e) {e.printStackTrace();}
						}
					});
					
//					CompressorThread t = new CompressorThread(quality_copy, image_copy, path + chooser.getSelectedFile().getName());
					compressorThreadList.add(t);
				}
				
//				ProgressBarThread progressBarThread = new ProgressBarThread(progressBar, compressorThreadList);
//				progressBarThread.start();
				
				Thread progressThread = new Thread(new Runnable()
				{
					
					@Override
					public void run()
					{
						progressBar.setMaximum(compressorThreadList.size());
						
						while(compressorThreadList.size() > 0)
						{
							try
							{
								Thread.sleep(500);
							}catch(InterruptedException e)
							{e.printStackTrace();}
							
							progressBar.setValue(compressorThreadList.size());
						}
						
						
					}
				});
				progressThread.start();
				
				
//				for(int i = 0; i < compressorThreadList.size();i++)	
//				{
//					Thread compressorThread;
//					synchronized(compressorThreadList)
//					{
//						compressorThread = compressorThreadList.get(i);
//					}
//					
//					compressorThread.start();
//					try
//					{
//						compressorThread.join();
//					}catch(InterruptedException e1)
//					{e1.printStackTrace();}
//				}
				
				
				
				spinner.setEnabled(true);
				btnCompress.setEnabled(true);
				btnOpen.setEnabled(true);
				
			}
		});

		//TODO progressBar Thread
		
		btnCompress.addActionListener(new ActionListener() {
			
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

				BufferedImage image = null;
				try {
					image = ImageIO.read(chooser.getSelectedFile());
				} catch (IOException e2) {e2.printStackTrace();}
				
				final BufferedImage image_copy = image;
				double spinnervalue = (double) spinner.getValue();

				
				for (int quality = (int)(spinnervalue * 10); quality > -1 ; --quality) 
				{
					Callable<BufferedImage> t = new CompressorFutureThread(quality / 10., image_copy);
					System.out.println(quality / 10.);
					compressCallableList.add(t);
				}
				
				System.out.println("Added to List");
				
				try {
					compressFuturesList = executor.invokeAll(compressCallableList);
				} catch (InterruptedException e1) {e1.printStackTrace();}
				
				System.out.println("Invoked all");
				
				
				Thread displayThread = new Thread(()->
				{
					for (Future<BufferedImage> future : compressFuturesList) 
					{
						while(!future.isDone()) {}
							SwingUtilities.invokeLater(() -> {
								try {
									imageComponent.setImage(future.get());
								} catch (InterruptedException | ExecutionException e11) {e11.printStackTrace();}
							});
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e12) {e12.printStackTrace();}
					}
					
				});
				displayThread.start();
				
				
				mainExecutor.submit(() -> {
					SwingUtilities.invokeLater(() -> {
						btnOpen.setEnabled(true);
						btnCompress.setEnabled(true);
						spinner.setEnabled(true);
					});
				});			
			}
		});
		
		
		
	}

//	class CompressorThread extends Thread
//	{
//		private JPGImageCompress jpgImageCompress;
//		private BufferedImage image;
//		private double quality;
//		private String filename;
//		
//		public CompressorThread(double quality, BufferedImage image, String filename)
//		{
//			this.quality = quality;
//			this.image = image;
//			this.filename = filename;
//			this.jpgImageCompress = new JPGImageCompress();
//		}
//		
//		@SuppressWarnings("static-access")
//		@Override
//		public void run()
//		{
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e1) {e1.printStackTrace();}
//			
//			
//			try {
//				jpgImageCompress.compressImage(this.image, this.filename + ".", this.quality);
//			} catch (IOException e) {e.printStackTrace();}
//		}
//		
//	}

	
	
	class ProgressBarThread extends Thread
	{
		private JProgressBar jProgressBar;
		private ArrayList<Thread> compressorThreadList;
		private double anzahl_gesamt;
		
		public ProgressBarThread(JProgressBar jProgressBar , ArrayList<Thread> compressorThreadList2)
		{
			this.jProgressBar = jProgressBar;
			this.compressorThreadList = compressorThreadList2;
			this.anzahl_gesamt = compressorThreadList2.size();
			
		}
		
		
		@Override
		public void run()
		{
			jProgressBar.setMaximum(compressorThreadList.size() - 1);
			
			while(compressorThreadList.size() > 0)
			{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				for(int i = 0; i < compressorThreadList.size();i++)	
				{
					Thread compressorThread = compressorThreadList.get(i);
					System.out.println(compressorThreadList.size());
					EventQueue.invokeLater(()->jProgressBar.setValue((int)(anzahl_gesamt - compressorThreadList.size())));
					synchronized(compressorThreadList)
					{
						if(compressorThread.getState() == State.TERMINATED) compressorThreadList.remove(compressorThread);
					}
				}
			}
		}
	}
}
// C:\\Users\\lilboy\\Desktop\\SCHULE\\TESTBestImageCompressed\\
// H:\Desktop\\TestCompressed\\