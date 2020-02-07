package net.tfobz.testprogramm;

public class Main {

	public static void main(String[] args) 
	{
		MyThread t = new MyThread();
		MyThreadStateAnalyser tAnalyser = new MyThreadStateAnalyser(t);
		
		tAnalyser.start();
		
		System.out.println("MyThread started");
		t.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
		t.interrupt();
			
		
		
		
	}

}
