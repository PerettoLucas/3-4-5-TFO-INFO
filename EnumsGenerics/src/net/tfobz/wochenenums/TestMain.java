package net.tfobz.wochenenums;

public class TestMain
{

	public static void main(String[] args)
	{
		Arbeitstage a1 = new Arbeitstage();
		a1.remove(Wochentag.SONNTAG);
		System.out.println(a1);
		System.out.println(a1.getWochenart());
		
		Arbeitstage a2 = new Arbeitstage(Wochentag.MONTAG,Wochentag.FREITAG);
		System.out.println(a2);
		System.out.println(a2.getWochenart());
		
		Arbeitstage a3 = new Arbeitstage(Wochentag.MONTAG,Wochentag.SAMSTAG);
		System.out.println(a3);
		System.out.println(a3.getWochenart());
		
		
		Arbeitsbeliebtheit b1 = new Arbeitsbeliebtheit();
		System.out.println(b1);
		
		Arbeitsbeliebtheit b2 = new Arbeitsbeliebtheit(a2,Beliebtheit.MITTEL);
		System.out.println(b2);
		
		Arbeitsbeliebtheit b3 = new Arbeitsbeliebtheit(a3,Beliebtheit.MITTEL);
		b3.put(Wochentag.SAMSTAG,Beliebtheit.KLEIN);
		System.out.println(b3);
		
	}

}
