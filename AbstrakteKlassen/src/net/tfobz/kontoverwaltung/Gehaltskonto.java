package net.tfobz.kontoverwaltung;

public class Gehaltskonto extends Konto
{
	
	protected static double startueberziehung = 0.0;
	protected double ueberziehung = 0.0;
	
	public Gehaltskonto()
	{
		super();
		this.ueberziehung = startueberziehung;
	}
	
	
	
	public static double getStartueberziehung()
	{
		return startueberziehung;
	}

	public static void setStartueberziehung(double startueberziehung) throws KontoException
	{
		if (startueberziehung <= 0) Gehaltskonto.startueberziehung = startueberziehung;
		else throw new KontoException("Überziehungsrahmen muss kleiner als Null sein");
	}

	public double getUeberziehung()
	{
		return ueberziehung;
	}

	public void setUeberziehung(double ueberziehung) throws KontoException 
	{
		if (ueberziehung <= 0) this.ueberziehung = ueberziehung;
		else throw new KontoException("Überziehungsrahmen muss kleiner als Null sein");
	}

	@Override
	public double getZinsen()
	{
		if(kontostand > 0)
			return kontostand * zinssatz * (365 - java.time.LocalDate.now().getDayOfYear()) / 365;
		else return 0;
	}

	@Override
	public double getSpesen() 
	{
		if (kontostand < 0) return 50;
		return 0;
		
	}

	@Override
	public void buchen(double betrag) throws KontoException 
	{
		if (kontostand + betrag < ueberziehung) 
			throw new KontoException("Der Betrag kann nicht gebucht werden da er den Ueberziehungsrahmen ueberschtreitet");
		else kontostand += betrag;
		
	}

	@Override
	public String toString() 
	{
		return "Gehaltskonto [Überziehung=" + ueberziehung + ", Kontonummer=" + kontonummer + ", Kontostand="
				+ kontostand + ", Zinssatz=" + zinssatz + ", Zinsen bis Jahresende=" + getZinsen() + ", Spesen=" + getSpesen() +"]";
	}
}
