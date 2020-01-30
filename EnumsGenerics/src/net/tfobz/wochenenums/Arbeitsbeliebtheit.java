package net.tfobz.wochenenums;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Arbeitsbeliebtheit implements Iterable<Entry<Arbeitstage, Beliebtheit>> 
{
	EnumMap<Wochentag, Beliebtheit> map;
	
	public Arbeitsbeliebtheit() 
	{
		 map = new EnumMap<>(map);
	}
	
	public Arbeitsbeliebtheit(Arbeitstage arbeitstage, Beliebtheit beliebtheit)
	{
	
	}
	
	
	
	@Override
	public Iterator<Entry<Arbeitstage, Beliebtheit>> iterator() {
		return map.entrySet().iterator();
	}
	
	
}
