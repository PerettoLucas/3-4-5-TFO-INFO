package net.tfobz.personen;

public class Handwerker extends Person
{
	protected String beruf = null;
	
	public Handwerker(String name, int alter, String beruf) {
		super(name, alter);
		this.beruf = beruf;
	}

	public String getBeruf() {
		return beruf;
	}

	public void setBeruf(String beruf) {
		this.beruf = beruf;
	}
	
	@Override
	public String toString() {
		return super.toString() + ";" + beruf;
	}
}
