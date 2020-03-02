package net.tfobz.threads.compression;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JProgressBar;

public class ProgressBarThread extends Thread
{
	private JProgressBar jProgressBar;
	private ArrayList<CompressorThread> compressorThreadList;
	private double anzahl_gesamt;
	
	public ProgressBarThread(JProgressBar jProgressBar , ArrayList<CompressorThread> compressorThreadList)
	{
		this.jProgressBar = jProgressBar;
		this.compressorThreadList = compressorThreadList;
		this.anzahl_gesamt = compressorThreadList.size();
		
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
				CompressorThread compressorThread = compressorThreadList.get(i);
				EventQueue.invokeLater(()->jProgressBar.setValue((int)(anzahl_gesamt - compressorThreadList.size())));
				synchronized(compressorThreadList)
				{
					if(compressorThread.getState() == State.TERMINATED) compressorThreadList.remove(compressorThread);
				}
			}
		}
	}
}
