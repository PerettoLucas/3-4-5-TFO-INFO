package net.tfobz.DigitalClock;

import java.awt.Font;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class JDigitalClock extends JLabel implements Runnable
{
	
	private boolean stopped = false;

	public JDigitalClock()
	{
		this.setText(new Date().toString().substring(11,19));
		setFont(new Font("Tahoma", Font.PLAIN, 46));
	}


	@Override
	public synchronized void run()
	{
		while(true)
		{
			while(stopped)
				try{wait();}catch(InterruptedException e1){}
			
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
			if(!stopped) 
			{
				synchronized(this)
				{
					this.stopped = stopped;
					this.notifyAll();
				}
			}else {
				this.stopped = stopped;
			}
	}
	
}
