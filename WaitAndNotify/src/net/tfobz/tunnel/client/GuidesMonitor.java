package net.tfobz.tunnel.client;

/**
 * An ihm kann ein F�hrer angefordert aber auch ein solcher zur�ckgegeben 
 * werden. Dieser muss eine Referenz auf ClientForm haben, damit die 
 * Statusmeldungen dort angezeigt werden k�nnen
 */
public class GuidesMonitor 
{
	/**
	 * Maximalanzahl der am Eingang vorhanden F�hrer
	 */
	protected final int MAX_GUIDES = 4;
	/**
	 * Anzahl der momentan verf�gbaren F�hrer
	 */
	protected int availableGuides = MAX_GUIDES;
	/**
	 * Referenz auf das ClientForm um Statustexte auszugeben
	 */
	protected ClientForm clientForm = null;
	
	/**
	 * Konstruktor, dem eine Referenz auf das ClientForm �bergeben wird
	 * @param clientForm
	 */
	public GuidesMonitor(ClientForm clientForm) {
	}
	
	/**
	 * Ein F�hrer wird angefordert, gleichzeitig werden die Statusmeldungen im
	 * ClientForm ausgegeben und die Benutzerschnittstelle angepasst
	 */
	public synchronized void request() {
	}
	
	/**
	 * F�hrer wird bei Beendigung einer F�hrung zur�ck gegeben. Statusmeldungen 
	 * werden ausgegeben und die Benutzerschnittstelle angepasst
	 */
	public synchronized void release() {
	}

	/**
	 * Die Anzahl der momentan verf�gbaren F�hrer wird zur�ck geliefert
	 * @return Anzahl der momentan verf�gbaren F�hrer
	 */
	public synchronized int getAvailableGuides() {
		return -1;
	}
}
