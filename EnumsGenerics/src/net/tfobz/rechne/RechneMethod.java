package net.tfobz.rechne;

public class RechneMethod 
{
	public static void rechne(Object[] o)
	{
		int sum = 0;
		int sum_final = 0;
		
		
		for (Object object : o) 
		{
			if(object instanceof Integer) sum += (Integer)object;
			else if( object instanceof Double) sum += (Double)object;
			else if(object instanceof String) 
			{
				System.out.println(object + ":" + sum);
				sum_final += sum;
				
			}
			
			
		}
		
		
	}
	
	public static void main(String[] args) 
	{
		
		
	}
	
	
}
