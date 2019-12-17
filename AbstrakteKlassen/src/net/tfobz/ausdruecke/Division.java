package net.tfobz.ausdruecke;

public class Division extends Operation
{
	public Division(Operand operand0, Operand operand1) {
		super(operand0, operand1);
	}
	public Division() {
		super();
	}
	public double getErgebnis() {
		double ret = 0.0;
		if (this.getOperand(0) != null)
			ret = this.getOperand(0).getErgebnis();
		if (this.getOperand(1) != null)
			ret = ret / this.getOperand(1).getErgebnis();
		return ret;
	}
	public String toString() {
		if(this.getOperand(0) != null && this.getOperand(1) != null)
			return "(" + this.getOperand(0) + "/" + this.getOperand(1) +")" + "=" + getErgebnis() + ")";
		return null;
	}

}
