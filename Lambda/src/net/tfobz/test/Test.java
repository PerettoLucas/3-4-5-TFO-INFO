package net.tfobz.test;

public class Test 
{
	public static void main(String[] args)
	{
		new Thread(()->	System.out.println("Beliebiger Text \n")).start();
		
		new Thread(()->
		{
			for(int i=0;i<1000;i++)	System.out.println("Beliebiger Text");
		}).start();
	}

}
