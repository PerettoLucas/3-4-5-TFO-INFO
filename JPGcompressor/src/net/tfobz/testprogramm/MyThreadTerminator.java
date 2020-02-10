package net.tfobz.testprogramm;

public class MyThreadTerminator extends Thread 
{
	private MyThread t = null;
	
	public MyThreadTerminator(MyThread t) {
		this.t = t;
	}
	@Override
	public void run() 
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {e.printStackTrace();}
		this.t.interrupt();
	}
}
