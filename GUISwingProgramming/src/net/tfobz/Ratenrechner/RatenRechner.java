package net.tfobz.Ratenrechner;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

public class RatenRechner
{
	private static final NumberFormat DEFAULT_FORMAT=new DecimalFormat();
	private boolean nachschuessig ;
	private double barwert;
	private double jahreszinssatz;
	private double laufzeitInJahren;
	private double ratenProJahr;
	private double rate;
	
	/**
	 * @return the tilgungsplan
	 * @throws RatenRechnerException 
	 */
	public String getTilgungsplan() throws RatenRechnerException
	{
		String zahlungsart = isNachschuessig() ? "nachschuessig" : "vorschuessig";
		String til="";
		String periode="";
		
		til+= "<!DOCTYPE html>\n" + 
				"<html lang=\"en\">\n" + 
				"<head>\n" + 
				"    <meta charset=\"UTF-8\">\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + 
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" + 
				"    <title>Tilgungsplan</title>\n" + 
				"</head>\n" + 
				"<body>\n" + 
				"    <h1>Tilgungsplan</h1>\n" + 
				"  \n" + 
				"    <table border=\"5\">\n" + 
				"        <tr>\n" + 
				"            <td>Zahlungsart: </td>\n" + 
				"            <td>"+	zahlungsart +"</td>\n" + 
				"        </tr> \n" + 
				"        <tr>\n" + 
				"            <td>Barwert: </td>\n" + 
				"            <td>"+ barwert +"</td>\n" + 
				"        </tr>\n" + 
				"        <tr>\n" + 
				"            <td>Jahreszinssatz: </td>\n" + 
				"            <td>" + jahreszinssatz + "%</td>\n" + 
				"        </tr>\n" + 
				"        <tr>\n" + 
				"            <td>Laufzeit in Jahren: </td>\n" + 
				"            <td>" + laufzeitInJahren + "</td>\n" + 
				"        </tr>\n" + 
				"        <tr>\n" + 
				"            <td>Rückzahlungsart: </td>\n" + 
				"            <td>" + ratenProJahr + "</td>\n" + 
				"        </tr>\n" + 
				"        <tr>\n" + 
				"            <td>Rate: </td>\n" + 
				"            <td>" + rate + "</td>\n" + 
				"        </tr>\n" +  
				"\n" + 
				"    </table>\n <table BORDER=\"5\"><tr><th>Periode</th><th>Rate</th><th>Restkapital</th> <th>Zinsen</th></tr>"; 
			
					final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;
					double restkapital = barwert;
					double roundedzinsen;
					for (int i = 1; i <= (int)(laufzeitInJahren*ratenProJahr); ++i) {
					final double zinsen;
					if (nachschuessig) {
						zinsen = restkapital * (q - 1.);
						restkapital = restkapital * q - rate;
					} else {
						zinsen = (restkapital - rate) * (q - 1.);
						restkapital = restkapital - rate + zinsen;
					}
					
						restkapital=round(restkapital,2);
						roundedzinsen=round(zinsen,3);
						
						periode+="<tr>\r\n" + 
						"		    <td>"+i+":</td>" + 
						"		    <td>"+getRate()+"</td>" + 
						"		    <td>"+restkapital+"�</td>" + 
						"		    <td>"+roundedzinsen+"</td>" + 
						"		  </tr>";
					}
				til += periode;
				til+= 
				"    </table>\n" + 
				"\n" + 
				"</body>\n" + 
				"</html>";
				
				return til;
	}
	
	public double round(double wert, int stellen) 
	{
		if (stellen < 0) throw new IllegalArgumentException();
		long factor = (long) Math.pow(10, stellen);
		wert = wert * factor;
		long tmp = Math.round(wert);
		return (double) tmp / factor;
	}
	
	/**
	 * @return the barwert
	 */
	public String getBarwert() throws RatenRechnerException {
		if (jahreszinssatz <= 0.)
			throw new RatenRechnerException("Jahreszinssatz nicht gesetzt");
		if (laufzeitInJahren <= 0.)
			throw new RatenRechnerException("Laufzeit nicht gesetzt");
		if (ratenProJahr <= 0)
			throw new RatenRechnerException("Raten pro Jahr nicht gesetzt");
		if (rate <= 0.)
			throw new RatenRechnerException("Rate nicht gesetzt");
		
		final double n = laufzeitInJahren * ratenProJahr;
		final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;
		if (nachschuessig)
			barwert = rate * (Math.pow(q, n) - 1.) / (Math.pow(q, n) * (q - 1.));
		else
			barwert = rate * (Math.pow(q, n)- 1.) / (Math.pow(q, n - 1.) * (q - 1.));
		//return String.valueOf(barwert);
		return ""+round(barwert,2);
	}
	/**
	 * @return the laufzeitInJahren
	 */
	public String getLaufzeitInJahren() throws RatenRechnerException {
		if (barwert <= 0.)
			throw new RatenRechnerException("Barwert nicht gesetzt");
		if (jahreszinssatz <= 0.)
			throw new RatenRechnerException("Jahreszinssatz nicht gesetzt");
		if (ratenProJahr <= 0)
			throw new RatenRechnerException("Raten pro Jahr nicht gesetzt");
		if (rate <= 0.)
			throw new RatenRechnerException("Rate nicht gesetzt");

		final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;
		
		if (nachschuessig)
			laufzeitInJahren = (-Math.log((rate - barwert * (q - 1.)) / rate)
					/ Math.log(q)) / ratenProJahr;
		else
			laufzeitInJahren = (1. - Math.log((q * rate - barwert * (q - 1.)) / rate)
					/ Math.log(q)) / ratenProJahr;
		
		//return String.valueOf(laufzeitInJahren);
		return ""+round(laufzeitInJahren,2);
	}
	/**
	 * @param ratenProJahr the ratenProJahr to set
	 */
	public void setRatenProJahr(String ratenProJahr) throws RatenRechnerException {
		ratenProJahr = ratenProJahr.split(" ", 2)[0];
		try {
			int tmp = Integer.parseInt(ratenProJahr);
			if (tmp <= 0)
				throw new RatenRechnerException("Mögliche Werte für Raten pro Jahr: 12, 6, 4, 1");
			if (!Arrays.asList(1, 4, 6, 12).contains(tmp))
				throw new RatenRechnerException("Mögliche Werte für Raten pro Jahr: 12, 6, 4, 1");
			this.ratenProJahr = tmp;
		} catch (NumberFormatException e) {
			throw new RatenRechnerException("Kein gültiger Ganzzahlwert");
		}
	}
	/**
	 * @return the rate
	 */
	public String getRate() throws RatenRechnerException {
		if (barwert <= 0.)
			throw new RatenRechnerException("Barwert nicht gesetzt");
		if (jahreszinssatz <= 0.)
			throw new RatenRechnerException("Jahreszinssatz nicht gesetzt");
		if (laufzeitInJahren <= 0.)
			throw new RatenRechnerException("Laufzeit in Jahren nicht gesetzt");
		if (ratenProJahr <= 0)
			throw new RatenRechnerException("Raten pro Jahr nicht gesetzt");
		
		final double n = laufzeitInJahren * ratenProJahr;
		final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;
		if (nachschuessig)
			rate = barwert * (Math.pow(q, n) * (q - 1.)) / (Math.pow(q, n) - 1.);
		else
			rate = barwert * (Math.pow(q, n - 1.) * (q - 1.)) / (Math.pow(q, n) - 1.);
		//return String.valueOf(rate);
		return ""+round(rate,2);
	}
	
	
	public void setJahreszinssatz(String jahreszinssatz) throws RatenRechnerException
	{
		try
		{
			if(Double.parseDouble(jahreszinssatz) > 0)
			{
				this.jahreszinssatz=Double.parseDouble(jahreszinssatz);
			} 
		}catch(NumberFormatException e)
		{
			if(jahreszinssatz.isEmpty()) throw new RatenRechnerException("JahreszinssatzField is Empty!");
			throw new RatenRechnerException("Jahreszinssatz ist nicht vom Typ Gleitkommawert");
		}
		
	}
	
	public void setBarwert(String barwert) throws RatenRechnerException
	{
		try {
			if(Double.parseDouble(barwert) <= 0) throw new RatenRechnerException("Barwert darf nicht kleiner gleich 0 sein.");
			else this.barwert=Double.parseDouble(barwert);
		}catch(NumberFormatException e)
		{
			if(barwert.isEmpty()) throw new RatenRechnerException("BarwertField is Empty!");
			throw new RatenRechnerException("Barwert ist nicht vom Format Gleichkomma");
		}
	}
	
	public void setLaufzeitInJahren(String laufzeitInJahren) throws RatenRechnerException
	{
		try
		{
			if(Double.parseDouble(laufzeitInJahren)  > 0)
			{
				this.laufzeitInJahren = Double.parseDouble(laufzeitInJahren);
			}
		}catch(NumberFormatException e)
		{
			if(laufzeitInJahren.isEmpty()) throw new RatenRechnerException("LaufZeitInJahrenField is Empty!");
			throw new RatenRechnerException("Laufzeit in Jahren nicht vom Typ Gleitkomma!");
		}
	}
	
	public void setRate(String rate) throws RatenRechnerException
	{
		try {
			if(Double.parseDouble(rate)  > 0) this.rate=Double.parseDouble(rate);
		} catch (NumberFormatException e) 
		{
			if(rate.isEmpty()) throw new RatenRechnerException("RateField is Empty!");
			throw new RatenRechnerException("Rate ist nicht vom Typ Gleitkomma");
		}	
			
	}
	
	
	
	public String getJahreszinssatz()
	{
		return "" + round(jahreszinssatz,2);
	}
	
	public String getRatenProJahr()
	{
		return "" + round(ratenProJahr,2);
	}
	
	public boolean isNachschuessig()
	{
		return nachschuessig;
	}
	
	public void setNachschuessig(boolean nachschuessig)
	{
		this.nachschuessig=nachschuessig;
	}
}
