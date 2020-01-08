package net.tfobz.autolistegui;

public class Auto
{
	private String name = null;
	private int erstzulassung = 0;
	
	public Auto() {
	}
	public Auto(String name, int erstzulassung) {
		this.name = name;
		this.erstzulassung = erstzulassung;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setErstzulassung(int erstzulassung) {
		this.erstzulassung = erstzulassung;
	}
	public int getErstzulassung() {
		return this.erstzulassung;
	}
}
