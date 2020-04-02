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
		while(this.I.i < 1000000) 
		{
			synchronized (I) {
				this.I.i = this.I.i + 1;
			}
			if(I.i % 10000 == 0) {
				
				System.out.println(this.I.i);
				try
				{
					Thread.sleep(20);
				}catch(InterruptedException e)
				{e.printStackTrace();}
			}
		}
	}
	
	
}
