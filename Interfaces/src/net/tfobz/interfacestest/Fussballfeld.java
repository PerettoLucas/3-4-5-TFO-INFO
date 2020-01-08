package net.tfobz.interfacestest;

public class Fussballfeld implements Groesse
{
	private final long laenge = 105000;
	private final long breite = 70000;
	private final long hoehe  = 0;
	
	
	public long getGrundflaeche(Object o)
	{
		if(o instanceof Groesse) 
			return this.laenge * this.breite;
		return 0;
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
