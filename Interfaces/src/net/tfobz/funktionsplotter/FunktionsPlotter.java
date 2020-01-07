package net.tfobz.funktionsplotter;

public class FunktionsPlotter
{
	public static void printTable(Funktion funktion)
	{
		System.out.println("Wertetabelle " + funktion.getClass().getName());
		
		for(double x = 0.0; x <= 5; x += 1)
		{
			System.out.println(x + "->" + funktion.compute(x));
		}
		
	}
	
	
	public static void main(String[] args)
	{
		printTable(new Wurzel());
		printTable(new Quadrat());
	}

	
	
}
