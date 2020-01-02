package net.tfobz.kontoverwaltung;

public class Sparkonto extends Konto
{

	@Override
	public double getZinsen()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSpesen()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void buchen(double betrag) throws KontoException
	{
		if (betrag <= 0 && betrag*-1 == betrag) ;
		
	}

	
	
}
