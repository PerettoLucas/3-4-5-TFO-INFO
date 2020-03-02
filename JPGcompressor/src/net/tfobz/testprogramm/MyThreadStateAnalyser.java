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
		System.out.println("started " + this.myThread.isAlive());
		while(this.myThread.getState() == State.NEW || this.myThread.isAlive()) 
		{
			System.out.println(this.myThread.getState());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {}
		}
		System.out.println(this.myThread.getState());
	}
}
