package net.tfobz.concurrency.prime;

/**
 * Tool that generates the prime factors of a given number and put them on the
 * standard output. Also the number of finished method calls will be shown
 * at the standard output
 * @author Michael Wild
 */
public class PrimeFactorTool
{
	private int finishedMethodCalls = 0;
	
	public void printPrimeFactors(int num) {
		int whichprime = 1;
		String prefix = "primeFactors(" + num + ") = ";
		while (num > 1) {
			int prime = getPrime(whichprime);
			if (num % prime == 0) {
				prefix = prefix + prime + " ";
				num /= prime;
			} else
				++whichprime;
		}
		System.out.println(prefix + " finishedMethodCalls = " + ++finishedMethodCalls);
	}

	/**
	 * Gets a prime number. For example getPrime(2) gets the second prime 
	 * @param cnt
	 * @return the cnt prime
	 */
	private int getPrime(int cnt) {
		int i = 1;
		int ret = 2;
		while (i < cnt) {
			++ret;
			if (isPrime(ret))
				++i;
		}
		return ret;
	}

	/**
	 * Returns true if the given number is prime
	 * @param num
	 * @return true if num is prime
	 */
	private boolean isPrime(int num) {
		for (int i = 2; i < num; ++i)
			if (num % i == 0)
				return false;
		return true;
	}
}