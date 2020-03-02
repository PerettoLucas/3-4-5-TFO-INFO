package net.tfobz.wochenenums;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Arbeitsbeliebtheit implements Iterable<Entry<Wochentag,Beliebtheit>>
{
	EnumMap<Wochentag,Beliebtheit> map;
	
	public Arbeitsbeliebtheit() 
	{
		map = new EnumMap<Wochentag,Beliebtheit>(Wochentag.class);
	}
	
	public Arbeitsbeliebtheit(Arbeitstage arbeitstage, Beliebtheit beliebtheit)
	{
		map = new EnumMap<Wochentag,Beliebtheit>(Wochentag.class);
		
		for(Wochentag wochentag:arbeitstage)
		{
			map.put(wochentag,beliebtheit);
		}
		
	}
	
	@Override
	public Iterator<Entry<Wochentag, Beliebtheit>> iterator() {
//		return map.entrySet().iterator();
		return null;
	}
	
	public void put(Wochentag wochentag, Beliebtheit beliebtheit)
	{
		map.put(wochentag,beliebtheit);
	}
	
	public void remove(Wochentag wochentag)
	{
		map.remove(wochentag);
	}
	
	public Beliebtheit get(Wochentag wochentag)
	{
		return map.getOrDefault(wochentag,null); 
	}
	
	public boolean contains(Beliebtheit beliebtheit)
	{
		return map.containsValue(beliebtheit);
	}
	
	@Override
	public String toString()
	{
		return map.toString();
	}
	}
