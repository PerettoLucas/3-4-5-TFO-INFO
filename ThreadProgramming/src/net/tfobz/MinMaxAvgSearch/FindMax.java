package net.tfobz.MinMaxAvgSearch;

import java.util.ArrayList;

import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class FindMax extends Thread
{
	private ArrayList<Integer> fieldArrayList = new ArrayList<>();
	private JProgressBar progressbar = null;
	private JTextField jTextFieldMax = null;


	public FindMax(ArrayList<Integer> Field, JProgressBar jProgressBar, JTextField jTextFieldParam)
	{
		this.progressbar = jProgressBar;
		this.jTextFieldMax = jTextFieldParam;
		this.fieldArrayList = Field;	
	}
	
	@Override
	public void run()
	{
		int zaehler = 0;
		int max = 0;
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
			
			if(i > max)
			{
				max = i;
			}
		}
		final int maxConst = max;
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				jTextFieldMax.setText("" + maxConst);		
			}
		});
	}
	
}

