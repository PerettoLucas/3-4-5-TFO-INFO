package net.tfobz.tunnel.server;

/**
 * Diese Klasse verwaltet die verfügbaren Besucher, welche eingelassen werden 
 * können
 */
public class VisitorsMonitor 
{
	/**
	 * Maximalanzahl der im Tunnel vorhanden Besucher
	 */
	protected final int MAX_VISITORS = 50;
	/**
	 * Anzahl der Besucher die in den Tunnel noch eingelassen werden können
	 */
	protected int availableVisitors = MAX_VISITORS;
	
	/**
	 * Fordert count Besucher an und gibt Statusmeldungen an der Serverkonsole
	 * aus
	 * @param count
	 */
	public synchronized void request(int count) {
	}
	
	/**
	 * Gibt count Besucher an den VisitorsMonitor zurück und gibt Statusmeldungen
	 * an der Serverkonsole aus
	 * @param count
	 */
	public synchronized void release(int count) {
	}
	
	/**
	 * Liefert die Anzahl der momentan noch verfügbaren Besucher zurück, die in den
	 * Tunnel eingelassen werden können
	 * @return Anzahl der noch in den Tunnel einlassbaren Besucher
	 */
	public synchronized int getAvailableVisitors() {
		return -1;
	}
}
