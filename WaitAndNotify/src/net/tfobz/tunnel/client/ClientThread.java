package net.tfobz.tunnel.client;

/**
 * Jede Anfrage um Start einer Besichtigung oder Beendigung einer solchen muss in 
 * einem eigenen Thread durchgeführt werden, da insbesondere bei nicht 
 * Vorhandensein eines Führers oder bei nicht verfügbarem Besucherkontingent eine 
 * solche Anfrage längere Zeit warten und deshalb das ClientFormu blockiert 
 * würde.<br>
 * Damit der Thread seine Aufgabe durchführen kann, muss er einerseits den 
 * GuidesMonitor als Referenz enthalten, um die Führeranforderung zu stellen. 
 * Andererseits muss er ClientForm kennen, um die Ausgaben und Anpassungen an 
 * der Benutzerschnittstelle vornehmen zu können.<br>
 * Der Thread erhält die Besucheranzahl. Ist diese positiv, so fordert er zuerst 
 * beim GuidesMonitor einen Führer für die Gruppe an. Erhält er diese, so wird 
 * über eine Netzwerkverbindung mit dem Programm ServerMain Verbindung aufgenommen 
 * und um die eingegebene Anzahl von Besuchern angefragt.<br>
 * Ist die Besucheranzahl negativ, so bedeutet dies, dass die Führung beendet 
 * wird, der Führer dem GuidesMonitor zurück gegeben und beim Server ebenfalls 
 * die Besucheranzahl zurück gegeben wird.<br>
 * Ist die Besucheranzahl gleich 0, so wird der Thread anweisen beim Server die 
 * Anzahl der verfügbaren Besucher nachzufragen, die noch im Tunnel Platz haben
 */
public class ClientThread extends Thread
{
	/**
	 * IP-Adresse des Besucherservers
	 */
	protected static final String HOST = "localhost";
	/**
	 * Port
	 */
	protected static final int PORT = 65535;
	/**
	 * Falls positiv: Anzahl der anzufordernden Besucher die eine Besichtigung machen 
	 * möchten<br>
	 * Falls negativ: Anzahl der Besucher die eine Besichtigung beenden möchten<br>
	 * Falls 0: Besucherserver muss angewiesen werden, die Anzahl der Besucher
	 * zurückzuliefern, welche noch in den Tunnel eingelassen werden können
	 */
	protected int count = 0;
	/**
	 * Referenz auf das ClientForm. Diese ist notwendig, damit der ClientThread
	 * die Benutzerschnittstelle aktualisieren und z. B. Statusmeldungen dort
	 * anzeigen kann
	 */
	protected ClientForm clientForm = null;
	/**
	 * Referent auf den GuidesMonitor. Diese ist notwendig, dass der ClientThread
	 * an diesem einen Führer anfordern bzw. nach Beendigung einer Führung den
	 * Führer zurückgeben kann
	 */
	protected GuidesMonitor guidesMonitor = null;
	
	/**
	 * Konstruktor dem die Anzahl der Besucher, das ClientForm und der 
	 * GuidesMonitor übergeben wird
	 * @param anzahl
	 * @param clientForm
	 * @param guidesMonitor
	 */
	public ClientThread(int anzahl, ClientForm clientForm, 
		GuidesMonitor guidesMonitor) {
	}
	
	/**
	 * In dieser Methode wird die eigentliche Arbeit des Threads erledigt. Ausgehend
	 * vom Wert der Variable count wird folgendes erledigt:<br><br>
	 * <b>count > 0: Eine neue Besichtigung soll durchgeführt werden</b><br>
	 * In einem ersten Schritt wird am GuidesMonitor ein Führer angefordert. War
	 * dies erfolgreich, so wird in einem zweiten Schritt eine Socket-Verbindung
	 * mit dem Server aufgebaut. Konnte die Verbindung nicht aufgebaut werden, so 
	 * wird der Führer wieder zurück gegeben. Bei aufrechter Verbindung wird die
	 * Anzahl übermittelt. Dann wartet der Thread auf die Antwort des Servers. Da
	 * der Thread neben anderen Threads eigenständig wartet, werden alle anderen
	 * Aktivitäten nicht blockiert. Nachdem die Antwort des Servers da ist, muss
	 * auch noch die JList mit einem neuen Eintrag ergänzt werden.Bei diesem Vorgang 
	 * müssen Änderungen und Ausgaben von Statusmeldungen am ClientFormular erfolgen<br><br>
	 * <b>count < 0: Eine Besichtigung soll beendet werden</b><br>
	 * Zuerst wird der Führer dem GuidesMonitor zurück gegeben. Dann wird eine
	 * Verbindung zum Server aufgebaut und diesem die Anzahl übergeben. Dann wird
	 * auf die Antwort des Servers gewartet und danach die JListeinträge aktualisiert. 
	 * Während dieses Vorganges werden die Inhalte von ClienForm angepasst<br><br>
	 * <b>count == 0: Eine Anfrage an den Server soll ermitteln, wie viele 
	 * Besucher noch im Tunnel Platz finden</b><br>
	 * Der Thread erstellt eine Socketverbindung mit dem Server, und schickt diesem
	 * eine 0. Der Server - falls aktiv - antwortet mit der aktuellen Besucheranzahl
	 * die noch in den Tunnel einglassen werden dürfen. Diese Anzahl wird im
	 * ClientForm ausgegeben
	 */
	public void run() {
	}

	/**
	 * Methode zur Behandlung der Netzwerkexceptions
	 * @param e
	 */
	public void behandleException(Exception e) {
	}
}