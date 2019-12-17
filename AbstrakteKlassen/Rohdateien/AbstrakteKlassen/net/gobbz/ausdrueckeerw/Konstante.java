package net.gobbz.ausdrueckeerw;

public class Konstante extends Operand
{
	private double ergebnis = 0.0;
	
	public Konstante(double ergebnis) {
		this.ergebnis = ergebnis;
	}
	public Konstante() {
		super();
	}
	
	public void setErgebnis(double ergebnis) {
		this.ergebnis = ergebnis;
	}
	public double getErgebnis() {
		return this.ergebnis;
	}
	public String toString() {
		return String.valueOf(this.ergebnis);
	}
}
