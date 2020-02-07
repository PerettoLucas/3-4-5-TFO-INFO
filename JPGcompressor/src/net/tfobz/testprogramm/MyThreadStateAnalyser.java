package net.tfobz.testprogramm;

public class MyThreadStateAnalyser extends Thread 
{
	private MyThread myThread;
	
	
	public MyThreadStateAnalyser(MyThread myThread) 
	{
		this.myThread = myThread;		
	}
	
	
	@Override
	public void run() 
	{
		while(this.myThread.isAlive()) 
		{
			System.out.println(this.myThread.getState());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {}
		}
	
	}
	
	
	
}
