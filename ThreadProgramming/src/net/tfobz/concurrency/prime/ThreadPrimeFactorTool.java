package net.tfobz.concurrency.prime;

public class ThreadPrimeFactorTool extends PrimeFactorTool
{
	
	@Override
	public void printPrimeFactors(int num)
	{
		Thread t = new Thread(new Runnable()
		{
			
			@Override
			public void run()
			{
				new PrimeFactorTool().printPrimeFactors(num);
			}
		});
		t.start();
	}
	
}
