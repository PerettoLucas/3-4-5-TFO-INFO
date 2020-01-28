package net.tfobz.meinedefaultliste;

public interface MeineListe<T>
{
	/**
	 * F�gt am Beginn der Liste ein neues Objekt ein
	 * @param element das einzuf�gende Element
	 * @return false falls kein Element �bergeben wurde
	 */
	boolean einfuegenErstesElement(T element);
	/**
	 * L�scht das erste Element der Liste
	 * @return true falls das erste Element gel�scht werden konnte
	 */
	boolean loeschenErstesElement();
	/**
	 * Kontrolliert ob Liste leer ist
	 * @return true falls die Liste leer ist
	 */
	boolean istLeer();
	/**
	 * L�scht die gesamte Liste
	 */
	void leeren();
	/**
	 * Liefert einen Iterator zur�ck, welcher es erlaubt die Elemente
	 * der Liste nacheinander zu bearbeiten
	 * @return Iterator der die Liste bearbeiten l�sst
	 */
	MeinIterator elemente();
}
