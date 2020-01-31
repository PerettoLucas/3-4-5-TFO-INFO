package net.tfobz.wochenenums;

import java.util.EnumSet;
import java.util.Iterator;

public class Arbeitstage implements Iterable<Wochentag>
{
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
	
	public void add(Wochentag wochentag) 
	{
		enumSet.add(wochentag);
	}
	
	public boolean remove(Wochentag wochentag) 
	{
		return enumSet.remove(wochentag);
	}
	
	public boolean contains(Wochentag wochentag) 
	{
		return enumSet.contains(wochentag);
	}
	
	@Override
	public String toString() 
	{
		return enumSet.toString();
	}
	
	public Arbeitswoche getWochenart() 
	{
		if(enumSet.size() == 6) return Arbeitswoche.SECHSTAGE;
		else if(enumSet.size() == 5) return Arbeitswoche.FUENFTAGE;
		else return null;
	}
	
	
}
