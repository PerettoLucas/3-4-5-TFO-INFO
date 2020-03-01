package net.tfobz.threads.compression;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JProgressBar;

public class ProgressBarThread extends Thread
{
	private JProgressBar jProgressBar;
	private ArrayList<CompressorThread> compressorThreadList;
	private double anzahl_gesamt;
	
	public ProgressBarThread(JProgressBar jProgressBar , ArrayList<CompressorThread> compressorThreadList, double quality)
	{
		this.jProgressBar = jProgressBar;
		this.compressorThreadList = compressorThreadList;
		this.anzahl_gesamt = quality * 10;
		
	}
	
	
	@Override
	public void run()
	{
		int anzahl_aktuell = compressorThreadList.size() - 1;
		
		while(anzahl_aktuell >= 0)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//TODO Invertieren 
			double invertiert = (int) (anzahl_gesamt - anzahl_aktuell);
			
			
			double percent = (invertiert / anzahl_gesamt) * 100;
			
			System.out.println("anzahl_aktuell :" + anzahl_aktuell + "percent : " + percent);
			
			EventQueue.invokeLater(() -> jProgressBar.setValue((int)percent));
			
			for (CompressorThread compressorThread : compressorThreadList) 
			{
				
				if(compressorThread.getState() == State.TERMINATED) anzahl_aktuell -= 1;
			}
		}
		
		
	}
	
	
	
}
