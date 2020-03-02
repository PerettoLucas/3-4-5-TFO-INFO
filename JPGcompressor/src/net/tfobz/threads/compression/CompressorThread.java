package net.tfobz.threads.compression;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class CompressorThread extends Thread
{
	private JPGImageCompress jpgImageCompress;
	private BufferedImage image;
	private double quality;
	private String filename;
	
	public CompressorThread(double quality, BufferedImage image, String filename)
	{
		this.quality = quality;
		this.image = image;
		this.filename = filename;
		this.jpgImageCompress = new JPGImageCompress();
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {e1.printStackTrace();}
		
		
		try {
			jpgImageCompress.compressImage(this.image, this.filename + ".", this.quality);
		} catch (IOException e) {e.printStackTrace();}
	}
	
}
