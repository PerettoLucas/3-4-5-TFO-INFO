package net.tfobz.meinedefaultliste;

public interface MeineListe
{
	/**
	 * Fügt am Beginn der Liste ein neues Objekt ein
	 * @param element das einzufügende Element
	 * @return false falls kein Element übergeben wurde
	 */
	boolean einfuegenErstesElement(Object element);
	/**
	 * Löscht das erste Element der Liste
	 * @return true falls das erste Element gelöscht werden konnte
	 */
	boolean loeschenErstesElement();
	/**
	 * Kontrolliert ob Liste leer ist
	 * @return true falls die Liste leer ist
	 */
	boolean istLeer();
	/**
	 * Löscht die gesamte Liste
	 */
	void leeren();
	/**
	 * Liefert einen Iterator zurück, welcher es erlaubt die Elemente
	 * der Liste nacheinander zu bearbeiten
	 * @return Iterator der die Liste bearbeiten lässt
	 */
	MeinIterator elemente();
}
