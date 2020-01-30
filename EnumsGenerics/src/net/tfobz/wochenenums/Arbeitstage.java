package net.tfobz.wochenenums;

import java.util.EnumSet;
import java.util.Iterator;

public class Arbeitstage implements Iterable<Wochentag>
{
	public static void main(String[] args)
	{
		
		
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
	public Iterator<Wochentag> iterator()
	{
		return enumSet.iterator();
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
	
	private Arbeitswoche getWochenart() 
	{
		if(enumSet.size() == 6) return Arbeitswoche.SECHSTAGE;
		else if(enumSet.size() == 5) return Arbeitswoche.FUENFTAGE;
		else return null;
	}
	
	
}
