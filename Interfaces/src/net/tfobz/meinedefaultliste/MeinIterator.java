package net.tfobz.meinedefaultliste;

public interface MeinIterator
{
	/**
	 * Kontrolliert ob ein nächstes Element in der Liste vorhanden ist
	 * @return true falls ein nächstes Element in der Liste vorhanden ist
	 */
	boolean hatNaechstesElement();
	/**
	 * Liefert das nächste Element in der Liste zurück
	 * @return das nächste Element in der Liste oder null, falls Element nicht
	 * vorhanden ist
	 */
	Object naechstesElement();
	/**
	 * Fügt nach dem Element auf dem Iterator zeigt das übergebene
	 * Element ein
	 * @param das einzufügende Element
	 * @return false wenn das einzufügende Element null ist
	 */
	boolean einfuegenElement(Object element);
	/**
	 * Setzt das Element auf welchem momentan der Iterator zeigt, indem es das 
	 * in der Liste vorhandene Element mit dem übergebenen Element ersetzt. Die
	 * Position des Iterators wird dabei nicht verändert 
	 * @param element das gesetzt werden soll
	 * @return false falls das übergebene Element null ist, falls die Liste leer ist, 
	 * oder noch nie mit naechstesElement() ein Element angesprungen wurde, oder über 
	 * das Listenende hinausgelesen wurde
	 */
	boolean setzenAktuellesElement(java.lang.Object element);
	/**
	 * Löscht das Element, welches momentan das aktuelle Element des Iterators ist. Beim 
	 * Löschen des aktuellen Elementes wird als neues aktuelles Element jenes Element 
	 * genommen, welches nach dem zu löschenden Element steht. Dabei wird von dieser 
	 * Methode der Iterator so gesetzt, dass der nachfolgende Aufruf von naechstesElement() 
	 * dieses Element zurückliefert. 
	 * @return false falls es noch kein aktuelles Element gibt, das gelöscht werden könnte
	 */
	boolean loeschenAktuellesElement();
}
