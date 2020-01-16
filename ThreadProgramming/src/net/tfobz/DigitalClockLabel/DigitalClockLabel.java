package net.tfobz.DigitalClockLabel;

import java.awt.Font;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class DigitalClockLabel extends JLabel implements Runnable
{
	
	private JLabel jLabel = this;
	private boolean stopped = false;

	public DigitalClockLabel()
	{
		jLabel = new JLabel();
		this.setText(new Date().toString().substring(11,19));
		setFont(new Font("Tahoma", Font.PLAIN, 46));
	}


	@Override
	public void run()
	{
		while(true)
		{
			if(!stopped)
			{
				
				final JLabel jlbl = this;
					try {
						SwingUtilities.invokeAndWait(new Runnable() {
							
							@Override
							public void run()
							{
								jlbl.setText(new Date().toString().substring(11,19));
							}
						});
					} catch (InvocationTargetException e) {e.printStackTrace();
					} catch (InterruptedException e) {e.printStackTrace();}
					
			}
			try
			{
				Thread.sleep(1000);
			}catch(InterruptedException e)
			{e.printStackTrace();}
		}
		
	}

	public boolean isStopped()
	{
		return stopped;
	}
	
	public void setStopped(boolean stopped)
	{
		this.stopped = stopped;
	}
	
}
