package net.tfobz.wochenenums;

import java.util.EnumSet;
import java.util.Iterator;

public class Arbeitstage implements Iterable<Arbeitswoche>
{
	public static void main(String[] args)
	{
		Arbeitstage arbeitstage = new Arbeitstage(Wochentag.DIENSTAG,Wochentag.SONNTAG);
	}
	
	EnumSet<Wochentag> enumSet = EnumSet.noneOf(Wochentag.class);;
	
	
	public Arbeitstage()
	{
		enumSet = EnumSet.allOf(Wochentag.class);
	}

	public Arbeitstage(Wochentag beginn, Wochentag ende)
	{
		enumSet = EnumSet.range(beginn,ende);		
	}
	

	@Override
	public Iterator<Arbeitswoche> iterator()
	{
		return null;
	}
	
	private void add(Wochentag wochentag) 
	{
		enumSet.add(wochentag);
	}
	
	private void remove(Wochentag wochentag) 
	{
		if(enumSet.contains(wochentag)) enumSet.remove(wochentag);
		else throw new IllegalArgumentException("Wochentag not contained in EnumSet");
	}
	
	private boolean contains(Wochentag wochentag) 
	{
		if(enumSet.contains(wochentag)) return true;
		else return false;
	}
	
	@Override
	public String toString() 
	{
		return enumSet.toString();
	}
	
	private void getWochenart() 
	{
		
	}
	
	
}
