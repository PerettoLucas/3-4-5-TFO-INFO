package net.tfobz.kontoverwaltung;

public class Sparkonto extends Konto
{

	protected double sparrate = 0;
	
	
	public Sparkonto(double ersteZahlung, double sparrate) throws KontoException
	{
		super();
		if(ersteZahlung >0 && sparrate > 0 )
		{
			buchen(ersteZahlung);
			this.sparrate = sparrate;
		}
		else 
		{
			Konto.naechsteKontonummer -= 1;
			throw new KontoException("Erste Zahlung und sparrate muessen groesser als 0 sein!");
		}
	}
	
	
	
	@Override
	public void buchen(double betrag) throws KontoException 
	{
		
		if (betrag >= -3000) 
		{
			if (kontostand+betrag > 0) kontostand += betrag;
			else throw new KontoException("Der angebe Batrag kann nicht gebucht werder da der Kontostand sonst unter 0 faellt");
		} 
		else if (-betrag == kontostand) kontostand += betrag;
		else throw new KontoException("Betraege groesser als 3000 können nicht abgebucht werden");
		
	}

	public double getSparrate() {
		return sparrate;
	}

	public void setSparrate(double sparrate) throws KontoException {
		
		if (sparrate > 0) this.sparrate = sparrate;
		else throw new KontoException("Sparrate muss grösser als 0 sein");	
		
	}

	@Override
	public double getZinsen() {
		
		return kontostand * zinssatz;
	}

	@Override
	public double getSpesen() {
			
		
		return kontostand*0.001;
	}

	public void buchenSparrate() {
		kontostand += kontostand*sparrate;
	}
	
	@Override
	public String toString() 
	{
		return "Sparkonto [Sparrate=" + sparrate + ", Kontonummer=" + kontonummer + ", Kontostand=" + kontostand
				+ ", Zinssatz=" + zinssatz + ", Jahreszinsen=" + getZinsen() + ", Spesen=" + getSpesen() +"]";
	}

	
	
}
