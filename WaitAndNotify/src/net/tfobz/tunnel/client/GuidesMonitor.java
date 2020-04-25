package net.tfobz.tunnel.client;

/**
 * An ihm kann ein Führer angefordert aber auch ein solcher zurückgegeben 
 * werden. Dieser muss eine Referenz auf ClientForm haben, damit die 
 * Statusmeldungen dort angezeigt werden können
 */
public class GuidesMonitor 
{
	/**
	 * Maximalanzahl der am Eingang vorhanden Führer
	 */
	protected final int MAX_GUIDES = 4;
	/**
	 * Anzahl der momentan verfügbaren Führer
	 */
	protected int availableGuides = MAX_GUIDES;
	/**
	 * Referenz auf das ClientForm um Statustexte auszugeben
	 */
	protected ClientForm clientForm = null;
	
	/**
	 * Konstruktor, dem eine Referenz auf das ClientForm übergeben wird
	 * @param clientForm
	 */
	public GuidesMonitor(ClientForm clientForm) {
	}
	
	/**
	 * Ein Führer wird angefordert, gleichzeitig werden die Statusmeldungen im
	 * ClientForm ausgegeben und die Benutzerschnittstelle angepasst
	 */
	public synchronized void request() {
	}
	
	/**
	 * Führer wird bei Beendigung einer Führung zurück gegeben. Statusmeldungen 
	 * werden ausgegeben und die Benutzerschnittstelle angepasst
	 */
	public synchronized void release() {
	}

	/**
	 * Die Anzahl der momentan verfügbaren Führer wird zurück geliefert
	 * @return Anzahl der momentan verfügbaren Führer
	 */
	public synchronized int getAvailableGuides() {
		return -1;
	}
}
