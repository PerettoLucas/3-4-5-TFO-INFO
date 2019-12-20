package net.tfobz.ausdruecke;

public class Potenz extends Operation
{

	public Potenz(Operand operand0 , Operand operand1)
	{
		this.setOperand(operand0);
		this.setOperand(operand1);
	}
	public Potenz()
	{
		super();
	}
	
	@Override
	public double getErgebnis()
	{
		double ergebnis = 0;
		if(this.getOperand(0) != null)
			ergebnis = this.getOperand(0).getErgebnis();
		if(this.getOperand(1) != null)
			ergebnis = Math.pow(ergebnis, this.getOperand(1).getErgebnis());
		return ergebnis;
	}
	
	@Override
	public String toString()
	{
		if(this.getOperand(0) != null && this.getOperand(1) != null)
			return "(" + "(" +this.getOperand(0) + ")" + "^" + this.getOperand(1)  + "=" + getErgebnis() +")";
		return null;
	}
}
