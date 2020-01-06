package net.tfobz.interfacestest;

public class Main
{

	public static void main(String[] args)
	{
		Groesse[] g = new Groesse[8];
		g[0] = new Auto(4000, 2500, 1500);
		g[1] = new Auto(5600, 2800, 1000);
		g[2] = new Fussballfeld();
		g[3] = new Papierblatt(0);
		g[4] = new Papierblatt(1);
		g[5] = new Papierblatt(2);
		g[6] = new Papierblatt(3);
		g[7] = new Papierblatt(4);
		
		
		for (int i = 0; i < g.length; i++) 
		{
			System.out.println(g[i].getClass().getSimpleName() +" > Grundflaeche : " + g[i].getLaenge() * g[i].getBreite());
		}
	}

}
