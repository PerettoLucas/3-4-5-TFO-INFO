package net.tfobz.ausdruecke;

public class Argument extends Konstante
{
	
	public Argument(double ergebnis)
	{
		setErgebnis(ergebnis); 
	}
	
	public Argument()
	{
		super();
	}
	
	@Override
	public void setErgebnis(double ergebnis)
	{
		ergebnis = Math.round(ergebnis*1000)/1000.0;
		if(ergebnis <= 1 && ergebnis != 0) ergebnis *= -1;
		
		super.setErgebnis(ergebnis);
	}
	
	public double getErgebnis()
	{
		return super.getErgebnis();
	}
	
}
