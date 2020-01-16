package net.tfobz.concurrency.prime;

public class PrimeFactorToolMain
{
	public static final int MIN_NUM = 10000;
	public static final int MAX_NUM = 10250;
	
	public static void main(String[] args) {
		PrimeFactorTool pt = new ThreadPrimeFactorTool();
		
		for (int num = MIN_NUM; num <= MAX_NUM; num++)
			pt.printPrimeFactors(num);
	}
}
