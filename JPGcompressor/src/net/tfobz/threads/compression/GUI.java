package net.tfobz.threads.compression;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private JPanel contentPane;
	private ImageComponent imageComponent;
	private JPGImageCompress jpgImageCompress;
	private ArrayList<Thread> compressorThreadList = new ArrayList<Thread>();
	
	private JProgressBar progressBar;
	private int count = 0;
	
	private java.util.List<Future<BufferedImage>> compressFuturesList = new ArrayList<Future<BufferedImage>>();
	private ArrayList<Callable<BufferedImage>> compressCallableList = new ArrayList<Callable<BufferedImage>>();
	private ExecutorService executor = Executors.newCachedThreadPool();
	
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
		
		progressBar = new JProgressBar();
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
				
				if(path == null) {
					spinner.setEnabled(true);
					btnCompress.setEnabled(true);
					btnOpen.setEnabled(true);
					
					return;
				}
				
				BufferedImage image = null;
				try {
					image = ImageIO.read(chooser.getSelectedFile());
				} catch (IOException e2) {e2.printStackTrace();}
				
				final BufferedImage image_copy = image;
				double spinnervalue = (double) spinner.getValue();
				
				
				
				for (double quality = 0; quality < spinnervalue ; quality+= 0.1) 
				{
					final double quality_copy = Math.round(quality * 100.0) / 100.0;
					
					System.out.println("quality : " + quality_copy);
					
					CompressorThread t = new CompressorThread(image_copy, path, quality_copy);
					
					compressorThreadList.add(t);
				}
				
				Thread progressThread = new Thread(new Runnable()
				{
					
					@Override
					public void run()
					{
						int maxnum = compressorThreadList.size();
						progressBar.setMaximum(maxnum);
						
						System.out.println(maxnum);
						
						while(maxnum > count)
						{

							final int count_final = count;
							System.out.println(count_final);
							try
							{
								Thread.sleep(500);
							}catch(InterruptedException e)
							{e.printStackTrace();}
							
							
							SwingUtilities.invokeLater(() ->
							{
								progressBar.setValue(count_final);
							});
						} 
						
					}
				});
				progressThread.start();
				
				for (Thread thread : compressorThreadList) {
					thread.start();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {e1.printStackTrace();}
				}
				
				
				spinner.setEnabled(true);
				btnCompress.setEnabled(true);
				btnOpen.setEnabled(true);
				
			}
		});

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
				
				int maxnum = compressFuturesList.size();
				SwingUtilities.invokeLater(() -> {
					progressBar.setMaximum(maxnum);
				});
				
				Thread displayThread = new Thread(()->
				{
					int maxnum_copy = 0;
					
					for (Future<BufferedImage> future : compressFuturesList) 
					{
						while(!future.isDone()) {}
							maxnum_copy++;
							final int num = maxnum_copy;
							SwingUtilities.invokeLater(() -> {
								try {
									imageComponent.setImage(future.get());
									progressBar.setValue(num);
								} catch (InterruptedException | ExecutionException e11) {e11.printStackTrace();}
							});
						
						try {
							Thread.sleep(500);
						} catch (InterruptedException e12) {e12.printStackTrace();}
					}
					
					btnOpen.setEnabled(true);
					btnCompress.setEnabled(true);
					spinner.setEnabled(true);
					
				});
				displayThread.start();
			}
		});
		
	}

	class CompressorThread extends Thread
	{
		private BufferedImage image;
		private String path;
		private double quality;
		
		public CompressorThread(BufferedImage image, String path ,double quality)
		{
			this.image = image;
			this.path = path;
			this.quality = quality;
		}
		
		
		@SuppressWarnings({ "static-access" })
		@Override
		public void run()
		{
			final BufferedImage image_copy = image;
			
			try {
				jpgImageCompress.compressImage(image_copy, path + ".", quality);
			} catch (IOException e) {e.printStackTrace();}
			
			count++;
		}
	}
	
}
// C:\\Users\\lilboy\\Desktop\\SCHULE\\TESTBestImageCompressed\\
// H:\Desktop\\TestCompressed\\