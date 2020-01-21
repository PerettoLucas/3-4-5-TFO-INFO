package net.tfobz.MinMaxAvgSearch;

import java.util.ArrayList;

import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class FindMin extends Thread
{
	private ArrayList<Integer> fieldArrayList = new ArrayList<>();
	private JProgressBar progressbar = null;
	private JTextField jTextFieldMin = null;


	public FindMin(ArrayList<Integer> Field, JProgressBar jProgressBar, JTextField jTextFieldParam)
	{
		this.progressbar = jProgressBar;
		this.jTextFieldMin = jTextFieldParam;
		this.fieldArrayList = Field;	
	}
	
	@Override
	public void run()
	{
		int zaehler = 0;
		int min = Integer.MAX_VALUE;
		// Geht das 10.000.000 array durch und setzt das Jehweilige minimum 
		for(Integer i:fieldArrayList)
		{
			zaehler++;
			final int zaehlerConst = zaehler;
			if(i % 100 == 0)
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
			
			if(i < min)
			{
				min = i;
			}
		}
		final int minConst = min;
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				jTextFieldMin.setText("" + minConst);		
			}
		});
	}
	
}
