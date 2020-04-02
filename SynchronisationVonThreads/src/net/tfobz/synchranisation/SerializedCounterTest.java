package net.tfobz.synchranisation;

public class SerializedCounterTest {

	public static void main(String[] args) 
	{
		SerializedCounter serializedCounter = new SerializedCounter();
		
		System.out.println("Old value : " + serializedCounter.getValue());

		System.out.println(serializedCounter.getIncrementedValue());

		System.out.println(serializedCounter.getIncrementedValue());
		
		System.out.println("Old value : " + serializedCounter.getValue());
		
//		new CounterIncrementThread(serializedCounter).start();	

//		new CounterIncrementThread(serializedCounter).start();
		
		serializedCounter.resetCounter();
	}

}
