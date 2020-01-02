package net.tfobz.ausdruecke;

public class Wurzel extends ArgOperation
{
	public Wurzel(Argument argument , Operand operand)
	{
		super(argument, operand);
	}
	
	public Wurzel()
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
			ergebnis = Math.pow(ergebnis, (1 / this.getArgument().getErgebnis()));
		return ergebnis;
	}
	
	@Override
	public String toString()
	{
		if(this.getArgument() != null && this.getOperand() != null)
			return "(Wurzel(" + this.getArgument() + "(" + this.getOperand() + "))" + this.getErgebnis() + ")"  ;
		return null;
	}
	
}
