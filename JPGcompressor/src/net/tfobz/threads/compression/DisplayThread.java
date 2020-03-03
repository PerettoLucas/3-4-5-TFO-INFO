package net.tfobz.threads.compression;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.SwingUtilities;

public class DisplayThread extends Thread 
{
	private ImageComponent imageComponent;
	private Future<BufferedImage> fBufferedImage;
	
	public DisplayThread(ImageComponent imageComponent, Callable<BufferedImage> callable, ExecutorService executor) 
	{
		this.imageComponent = imageComponent;
		this.fBufferedImage = executor.submit(callable);
	}
		
	@Override
	public void run()
	{
		try {
			while (!fBufferedImage.isDone())
				sleep(10);

			SwingUtilities.invokeAndWait(() ->
			{
				try {
					imageComponent.setImage(this.fBufferedImage.get());
				} catch (InterruptedException | ExecutionException e1) {e1.printStackTrace();}
			});
		} catch (InvocationTargetException | InterruptedException e) {e.printStackTrace();}
	}



}
