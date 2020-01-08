package net.tfobz.interfacestest;

import java.util.ArrayList;

public class SortiertUnsortiertTest
{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args)
	{
		Groesse[] g = new Groesse[10];
		g[0] = new Auto(4000, 2500, 1500);
		g[1] = new Auto(5600, 2800, 1000);
		g[2] = new Auto(2200, 2100, 1800);
		g[3] = new Fussballfeld();
		g[4] = new Fussballfeld();
		g[5] = new Papierblatt(0);
		g[6] = new Papierblatt(1);
		g[7] = new Papierblatt(2);
		g[8] = new Papierblatt(3);
		g[9] = new Papierblatt(4);

		System.out.println("Ausgabe unsortiert: ");
		
		for (int i = 0; i < g.length; i++) 
		{
			System.out.println(i + ". " + g[i].getClass().getSimpleName() + " L = " + g[i].getLaenge() + " B = " 
			+ g[i].getBreite() + " H = "+ g[i].getHoehe() + " G = " + g[i].getBreite() * g[i].getLaenge());
		}
		
		
		//Sorting Array with SelectionSort
		for (int i = 0; i < g.length; i++) 
		{
			int min = i;
			for (int j = i + 1; j < g.length; j++) 
			{
				if (((Comparable) g[j]).compareTo(g[min]) < 0) min = j;
			}
			Groesse tmp = g[i];
			g[i] = g[min];
			g[min] = tmp;
		}
		
		System.out.println("\nAusgabe sortiert: ");
		
		for (int i = 0; i < g.length; i++) 
		{
			System.out.println(i + ". " + g[i].getClass().getSimpleName() + " L = " + g[i].getLaenge() + " B = " 
			+ g[i].getBreite() + " H = "+ g[i].getHoehe() + " G = " + g[i].getBreite() * g[i].getLaenge());
		}
		
	}

}
