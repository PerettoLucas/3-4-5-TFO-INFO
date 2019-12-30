package net.tfobz.ausdruecke;

public class Logarithmus extends ArgOperation
{
	public Logarithmus(Argument argument, Operand operand)
	{
		super(argument, operand);
	}
	
	public Logarithmus()
	{
		super();
	}
	
	@Override
	public double getErgebnis()
	{
		double ergebnis = 0 ;
		if(this.getOperand() != null) 
			ergebnis = this.getOperand().getErgebnis();
		if(this.getArgument() != null)
			ergebnis = Math.log(this.getArgument().getErgebnis()) / Math.log(ergebnis);
		return ergebnis;
	}

	@Override
	public String toString()
	{
		if(this.getArgument() != null && this.getOperand() != null)
			return "(Log(" + this.getArgument() + "(" + this.getOperand() + "))=" + getErgebnis() + ")"  ;
		return null;
	}
	
}
