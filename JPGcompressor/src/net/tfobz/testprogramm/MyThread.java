package net.tfobz.testprogramm;

public class MyThread extends Thread 
{
	@Override
	public void run() 
	{
		while(!isInterrupted())
		{
			//Aufwendige Schleife
			for(int i = 0, j = 1; i < 1000000;i++)
			{
				j = j*2;
			}
			
			try {	
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				interrupt();
			}
		}
	}
	
	
}
