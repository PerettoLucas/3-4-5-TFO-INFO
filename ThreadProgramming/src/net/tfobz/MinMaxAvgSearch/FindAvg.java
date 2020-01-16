package net.tfobz.MinMaxAvgSearch;

import java.util.ArrayList;

import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class FindAvg extends Thread
{
	private ArrayList<Integer> fieldArrayList = new ArrayList<>();
	private JProgressBar progressbar = null;
	private JTextField TextFieldAvg = null;


	public FindAvg(ArrayList<Integer> Field, JProgressBar jProgressBar, JTextField jTextFieldParam)
	{
		this.progressbar = jProgressBar;
		this.TextFieldAvg = jTextFieldParam;
		this.fieldArrayList = Field;	
	}
	
	@Override
	public void run()
	{
		int zaehler = 0;
		int avg = 0;
		long  sum = 0;
		// Geht das 10.000.000 array durch und setzt das Jehweilige minimum 
		for(Integer i:fieldArrayList)
		{
			zaehler++;
			final int zaehlerConst = zaehler;
			if(i % 100 == 1)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						progressbar.setValue(zaehlerConst);		
					}
				});
					
			}
			
			sum += i;
			avg = (int)(sum/fieldArrayList.size());
			
		}
		final int avgConst = avg;
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				TextFieldAvg.setText("" + avgConst);		
			}
		});
	}
	
}

