package net.tfobz.interfacestest;

public class Auto implements Groesse 
{
	private long laenge;
	private long breite;
	private long hoehe;	
	
	public Auto(long laenge, long breite, long hoehe)
	{
		this.laenge = laenge;
		this.breite = breite;
		this.hoehe = hoehe;
	}

	public Auto()
	{
	}
	
	@Override
	public int compareTo(Groesse o)
	{
		if (this.laenge * this.breite < ((Groesse) o).getBreite() * ((Groesse) o).getLaenge()) return -1;
		else if (this.laenge * this.breite > ((Groesse) o).getBreite() * ((Groesse) o).getLaenge()) return 1;
		
		return 0;
	}
	
	public void setLaenge(long laenge)
	{
		this.laenge = laenge;
	}

	public void setBreite(long breite)
	{
		this.breite = breite;
	}

	public void setHoehe(long hoehe)
	{
		this.hoehe = hoehe;
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