package net.tfobz.synchranisation;

public class SerializedCounterTest {

	public static void main(String[] args) 
	{
		SerializedCounter serializedCounter = new SerializedCounter();
		System.out.println("Old value : " + serializedCounter.getValue());
		
		CounterIncrementThread t = new CounterIncrementThread(serializedCounter);	
		CounterIncrementThread t1 = new CounterIncrementThread(serializedCounter);
		t.start();
		t1.start();
		
		try
		{
			t.join();
			t1.join();
		}catch(InterruptedException e){e.printStackTrace();}
		
		System.out.println("New value : " + serializedCounter.getValue());
		
		serializedCounter.resetCounter();
	}

}
