package net.tfobz.ausdruecke;

public class Argument extends Konstante
{

	private double argument = 0;
	
	public Argument(double argument)
	{
		this.argument = argument;
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
		
//		super.setErgebnis(ergebnis);
		this.argument = ergebnis;
	}
	
	public double getArgument()
	{
		return argument;
	}
	
	public void setArgument(int argument)
	{
		this.argument = argument;
	}
	
}
