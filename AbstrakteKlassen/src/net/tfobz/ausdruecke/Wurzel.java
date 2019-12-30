package net.tfobz.ausdruecke;

public class Wurzel extends ArgOperation
{
	
	
	
	@Override
	public double getErgebnis()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	@Override
	public String toString()
	{
		if(this.getArgument() != null && this.getOperand() != null)
			return "(Wurzel(" + this.getArgument() + "(" + this.getOperand() + ")))"  ;
		return null;
	}
	
}
