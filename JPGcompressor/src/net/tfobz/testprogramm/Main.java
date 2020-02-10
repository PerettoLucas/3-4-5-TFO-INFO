package net.tfobz.testprogramm;

public class Main {

	public static void main(String[] args) 
	{
		MyThread t = new MyThread();
		MyThreadStateAnalyser ts = new MyThreadStateAnalyser(t);
		ts.start();
		MyThreadTerminator tt = new MyThreadTerminator(t);
		tt.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {e1.printStackTrace();}
		
		
		t.start();
		
		try {
			t.join();
			ts.join();
			tt.join();
		} catch (InterruptedException e) {e.printStackTrace();}
	}
}
