package net.tfobz.interfacestest;

public class Papierblatt implements Groesse
{
	private long laenge;
	private long breite;
	private long hoehe;
	
	private final long [][] groessen = 
		{{1189, 841}, {841, 594}, {594, 420}, {420, 297}, {297, 210}};
	
	public Papierblatt (int format) 
	{
		this.hoehe = 0;
		switch (format) 
		{
			case 0: 
			{
				this.laenge = groessen[0][0];
				this.breite = groessen[0][1];
				break;
			}
			case 1: 
			{
				this.laenge = groessen[1][0];
				this.breite = groessen[1][1];
				break;
			}
			case 2: 
			{
				this.laenge = groessen[2][0];
				this.breite = groessen[2][1];
				break;
			}
			case 3: 
			{
				this.laenge = groessen[3][0];
				this.breite = groessen[3][1];
				break;
			}
			case 4: 
			{
				this.laenge = groessen[4][0];
				this.breite = groessen[4][1];
				break;
			}
		}
	}
	
	
	@Override
	public int compareTo(Groesse o)
	{
		if (this.laenge * this.breite < ((Groesse) o).getBreite() * ((Groesse) o).getLaenge()) return -1;
		else if (this.laenge * this.breite > ((Groesse) o).getBreite() * ((Groesse) o).getLaenge()) return 1;
		
		return 0;
	}
	
	@Override
	public long getLaenge()
	{
		return this.laenge;
	}

	@Override
	public long getBreite()
	{
		return this.breite;
	}

	@Override
	public long getHoehe()
	{
		return this.hoehe;
	}

}
