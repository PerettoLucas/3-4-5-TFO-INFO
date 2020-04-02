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
			this.I.i = this.I.i + 1;
//			System.out.println(this.I.i);
			try
			{
				Thread.sleep(1);
			}catch(InterruptedException e)
			{e.printStackTrace();}
		}
	}
	
	
}
