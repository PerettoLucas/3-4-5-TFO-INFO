package net.tfobz.methods;

import java.util.Hashtable;

public class Methods 
{
	public static void main(String[] args) 
	{
		rechne(1.45,0.79,19.9,"Ware",-3.0,1.5,"Pfand",-10,"Gutschein");
		zaehleZahlen(6,6,3,2,4,0,1,8,1,4);
	}
	

	public static void rechne(Object... o)
	{
		double sum = 0;
		double sum_final = 0;
		
		for (Object object : o) 
		{
			if(object instanceof Integer) sum += (Integer)object;
			else if( object instanceof Double) sum += (Double)object;
			else if(object instanceof String) 
			{
				System.out.println(object + ":" + sum);
				sum_final += sum;
				sum = 0;
			}
			
			
		}
		System.out.println("Gesamtsumme: " + sum_final);
		
	}
	
	public static void zaehleZahlen(int... list)
	{
		
		Hashtable<Integer,Integer> hashtable = new Hashtable<>();
		
		int sum = 0;
		
		for(int param:list)
		{
			for(int anzahl:list)
			{
				if(param == anzahl) sum += 1;
			}
			hashtable.put(param,sum);
			sum = 0;
		}
		System.out.println(hashtable.toString());
	}
	
}
