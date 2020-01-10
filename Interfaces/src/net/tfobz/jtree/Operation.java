package net.tfobz.jtree;

/**
 * Achtung: Die Operanden werden immer der Reihe nach in die Operation geh�ngt. Mit
 * setOperand kann man beim ersten Aufruf den ersten Operanden f�llen, ruft man
 * setOperand nochmals auf, so wird der zweite Operand gef�llt. Beim L�schen werden 
 * die Operanden unter Umst�nden nach vorne verschoben.
 * @author Michael Wild
 */
public abstract class Operation extends Operand
{
	private Operand[] operand = new Operand[2];
	
	public Operation(Operand operand0, Operand operand1) {
		this.setOperand(operand0);
		this.setOperand(operand1);
	}
	public Operation() {
		super();
	}
	public void setOperand(Operand operand) {
		if (this.operand[0] == null)
			this.operand[0] = operand;
		else
			if (this.operand[1] == null)
				this.operand[1] = operand;
	}
	public Operand getOperand(int position) {
		if (position >= 0 && position <= 1)
			return this.operand[position];
		else
			return null;
	}
	public void vertausche() {
		if (this.operand[0] != null && this.operand[1] != null) {
			Operand operand = this.operand[0];
			this.operand[0] = this.operand[1];
			this.operand[1] = operand;
		}
	}
	public void loescheOperand(int position) {
		if (position == 0) {
			this.operand[0] = this.operand[1];
			this.operand[1] = null;
		} else
			if (position == 1)
				this.operand[1] = null;
	}
}
