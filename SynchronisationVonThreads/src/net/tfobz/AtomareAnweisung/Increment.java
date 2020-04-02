package net.tfobz.AtomareAnweisung;

public class Increment extends Thread
{
	private Int I = null;
	
	public Increment(Int I)
	{
		this.I = I;
	}
	
	
	@Override
	public void run()
	{	
		int copyi = 0;
		while(copyi < 1000000) 
		{
			copyi++;
			synchronized (I) {
				this.I.i++;
			}
			if(I.i % 10000 == 0) {
				try
				{
					Thread.sleep(20);
				}catch(InterruptedException e)
				{e.printStackTrace();}
			}
		}
	}
	
	
}
