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
	
	
}
