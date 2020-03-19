	package net.tfobz.threads.compression;

import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

public class CompressorFutureThread implements Callable<BufferedImage>
{
	private JPGImageCompress jpgImageCompress;
	private BufferedImage image;
	private double quality;
	private BufferedImage result = null;
	
	public CompressorFutureThread(double quality, BufferedImage image)
	{
		this.quality = quality;
		this.image = image;
		this.jpgImageCompress = new JPGImageCompress();
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public BufferedImage call() throws Exception
	{
		this.result = jpgImageCompress.compressImage(this.image,this.quality);
		return this.result;
	}

	


}
