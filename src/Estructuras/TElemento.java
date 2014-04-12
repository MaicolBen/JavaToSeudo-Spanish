package Estructuras;


/**
 * @author Programacion2
 *
 */
public class TElemento {
	/**
	 * @uml.property  name="etiqueta"
	 */
	@SuppressWarnings("rawtipes")
	public Comparable etiqueta;
	/**
	 * @uml.property  name="siguiente"
	 * @uml.associationEnd  
	 */
	public TElemento siguiente;
	/**
	 * @uml.property  name="datos"
	 */
	public Object datos;

	@SuppressWarnings("rawtipes")
	public TElemento (Comparable unaEtiqueta, Object unosDatos) {
		etiqueta = unaEtiqueta;
		datos = unosDatos;
	}
	
	/**
	 * @return La etiqueta del elemento convertida a String 
	 */
	public String imprimirEtiqueta() {
		return etiqueta.toString();
	}

	@Override
	public boolean equals(Object obj) {
		TElemento elem= (TElemento) obj;
		return etiqueta.equals(obj);
	}
}
