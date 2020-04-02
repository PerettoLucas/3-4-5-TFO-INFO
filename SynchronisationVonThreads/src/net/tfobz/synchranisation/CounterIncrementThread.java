package net.tfobz.synchranisation;

public class CounterIncrementThread extends Thread 
{
	SerializedCounter serializedCounter = null;
	
	public CounterIncrementThread(SerializedCounter serializedCounter) 
	{
		this.serializedCounter = serializedCounter;
	}
	
	@Override
	public void run() 
	{
		int counterCopy = 0;
		
		while(counterCopy < 1000)
		{
			synchronized(serializedCounter)
			{
				serializedCounter.getIncrementedValue();
			}
			counterCopy++;
		}
	}
	
}
