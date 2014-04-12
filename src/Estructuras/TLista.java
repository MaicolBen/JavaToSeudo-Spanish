package Estructuras;

/**
 * @author 220
 * @version 1.0
 */
public class TLista {

	/**
	 * Separador utilizado entre elemento y elemento al imprimir la lista
	 */
	public static final String SEPARADOR_ELEMENTOS_IMPRESOS = "-";
	/**
	 * Se identifica el primer elemento de la lista.
	 * @uml.property  name="primero"
	 * @uml.associationEnd  
	 */
	public TElemento primero;

	/**
	 * Constructor por defecto 
	 */
	public TLista () {
	}

	/**
	 * Este m�todo devuelve el primero de la lista, el cual es conocido por la misma,
	 *  ya sea nulo en el caso de que este vac�a.
	 * @return primero de la lista
	 */
	public TElemento obtenerPrimero() {
		return primero;
	}
	/**
	 * Si la lista no es vac�a, se recorre la misma evaluando que el siguiente a un elemento auxiliar no sea nulo ,
	 *  en caso de serlo se devuelve el elemento auxiliar. En caso de que sea vac�a se devuelve nulo.
	 * @return unElemento El ultimo elemento
	 */
	public TElemento obtenerUltimo() {
		if(!esVacia()){
			TElemento unElemento = primero;
			while( unElemento != null){
				if(unElemento.siguiente==null){
					return unElemento;
				}
				unElemento= unElemento.siguiente;
			}
		}
		return null;
	}

	/**
	 * Si el elemento a insertar es distinto de nulo ,
	 *  se le asigna como siguiente al elemento a insertar el elemento que anteriormente era el primero de la lista,
	 *   y luego al elemento a insertar se asigna como el primero,
	 *  este operaci�n devolver� Verdadero en todos los casos ya que te�ricamente siempre es posible insertar al principio.
	 * @param unElemento El elemento a insertar
	 * @return true En caso de ser Exitosa
	 */

	public boolean insertarAlPrincipio (TElemento unElemento){
		if(unElemento!=null){
			unElemento.siguiente=primero;
			primero=unElemento;

			return true;

		}return false;
	}

	/**
	 * Si el ultimo que se obtiene es nulo entonces el elemento a insertar es el primero ,
	 *  pero sino el que sea ultimo tiene que tener referencia como siguiente al elemento a insertar.
	 * 
	 * @param unElemento El elemento que queremos insertar
	 * @return true Si se pudo insertar
	 */
	public boolean insertarAlFinal (TElemento unElemento) {
		if(unElemento==null){
			return false;

		}
		if(esVacia()){
			insertarAlPrincipio(unElemento);
			return true;	
		}else {
			obtenerUltimo().siguiente= unElemento;
			unElemento.siguiente=null;
			return true;
		}	



	}
	/**
	 * Identifica  si el primero de la lista es nulo ,
	 *  de ser as� devuelve verdadero , de lo contrario falso.
	 * @return true En caso de estar vacia
	 */
	public boolean esVacia () {

		if( primero== null){
			return true;
		}
		return false;
	}
	/**
	 * El elemento ingresado para buscar es comparado con todos los elementos de la lista,
	 * comenzando por el primero hasta llegar al ultimo. 
	 * Si al recorrer la lista se verifica la igualdad entre el elemento ingresado y
	 * uno de los pertenecientes a la lista se devuelve verdadero.
	 * De lo contrario se devuelve falso.
	 * @param unElemento El elemento a  buscar
	 * @return true En caso de que el elemento exista en la lista
	 */
	public boolean existeElemento(TElemento unElemento) {
		if(unElemento!=null){
			return buscarEtiqueta(unElemento.etiqueta)!=null;
		}
		return false;
	}
	/**
	 * Se busca recorriendo la lista mediante una variable temporal comparando las etiquetas de cada uno de los elementos de la lista con la etiqueta a buscar ,
	 * si ambas etiquetas son iguales se devuelve el elemento que tenia esa etiqueta, por el contrario se devuelve nulo.
	 * @param unaEtiqueta La etiqueta a buscar en los elementos de la lista
	 * @return elementoActual El elemento cuya etiqueta es la buscada en caso de existir el mismo
	 */
	public TElemento buscarEtiqueta( Comparable unaEtiqueta) {
		TElemento elementoActual= primero;
		if(unaEtiqueta!=null){

			while((elementoActual!=null)&&(!unaEtiqueta.equals(elementoActual.etiqueta))){
				elementoActual=elementoActual.siguiente;


			}return elementoActual;
		}return null;
	}
	/**
	 * Si existe el elemento  y si ese elemento es el primero ,
	 *  el siguiente de este pasa a ser el primero , 
	 *  pero sino el anterior del elemento a eliminar cambia su referencia de siguiente hacia el siguiente del elemento a eliminar ,
	 *  y el  elemento a eliminar tiene como referencia al siguiente nulo ,
	 *  en cualquiera de estos dos casos se devuelve verdadero. Si no existe el elemento a elminar, se devuelve falso.
	 * @param unElemento El elemento que se quiere eliminar
	 * @return true En caso de ser exitosa la operacion
	 */
	public boolean eliminar (TElemento unElemento) {

		if(existeElemento(unElemento)!=false){
			if(unElemento==primero){
				primero=unElemento.siguiente;

			}
			else{
				anterior(unElemento).siguiente=unElemento.siguiente;


			}
			return true;
		}
		return false;


	}
	/**
	 * Si la etiqueta existe, elimina el elemento al que le pertenece esa etiqueta y retorna verdadero, si la etiqueta no existe, se devuelve falso.
	 * @param unaEtiqueta El elemento que se quiere eliminar
	 * @return true En caso de ser exitosa la operacion
	 */
	public boolean eliminarEtiqueta (Comparable unaEtiqueta) {
		boolean eliminado= false;
		if(buscarEtiqueta(unaEtiqueta)!=null){
			eliminado= eliminar(buscarEtiqueta(unaEtiqueta));


		}
		return eliminado;

	}
	/**
	 * Recorre la lista con una variable temporal inicializada en el primero y
	 *  con un contador entero se le va incrementando en uno cada vez que se encuentre un elemento siguiente a la variable temporal  distinto del nulo. 
	 *  Luego se devuelve el contador.
	 * @return cantidad 
	 */
	public int obtenerCantidadElementos() {
		TElemento unElemento=primero;
		int cantidad=0;
		while(unElemento!=null){
			cantidad++;
			unElemento=unElemento.siguiente;
		}
		return cantidad;
	}
	/**
	 * Se inserta ordenado un  elemento(no puede ser un elemento nulo)
	 *  comparando cada elemento de la lista hasta encontrar un elemento menor del elemento a insertar ,
	 * si ya menor que el primero se inserta al principio.
	 * @param unElemento El elemento a insertar
	 * @return true En caso de ser exitosa la operacion
	 */
	public boolean insertarOrdenado(TElemento unElemento) {
		if(unElemento==null){
			return false;
		}else{

			if((esVacia())|| (primero.etiqueta.compareTo(unElemento.etiqueta)>=0)){
				return insertarAlPrincipio(unElemento);

			}else{
				TElemento anterior=primero;
				TElemento actual= anterior.siguiente;
				//la lista tiene al menos un elemento menor al que quiero insertar

				while((actual!=null)&&(actual.etiqueta.compareTo(unElemento.etiqueta)<0)){
					anterior= actual;
					actual= actual.siguiente;
				}
				unElemento.siguiente=actual;
				anterior.siguiente=unElemento;
				return true;
			}	
		}
	}
	/**
	 * Solamente iguala al primer elemento al nulo.
	 */
	public void vaciar() {
		primero=null;


	}
	/**
	 * Se va insertando un nuevo elemento (que obtiene los datos y etiqueta de una variable temporal)al principio de la lista a una nueva lista partiendo del primero,
	 *  recorriendo la lista anterior con la variable temporal hasta llegar al siguiente del ultimo(del nulo).
	 * @return nuevaLista La lista invertida
	 */
	public TLista invertir() {
		TElemento actual=primero;		
		TLista nuevaLista=new TLista();		
		if(!esVacia()){			

			while(actual!=null){			
				TElemento nuevo= new TElemento(actual.etiqueta,actual.datos);		
				nuevaLista.insertarAlPrincipio(nuevo);
				actual=actual.siguiente;

			}


		}
		return nuevaLista;

	}
	/**
	 * A partir de dos elementos temporales, uno haciendo referencia al primero (actual)
	 *  y el otro a el elemento anterior a actual (anterior).
	 *  Mientras que actual sea distinto de nulo y distinto al elemento al que se le quiere buscar el anterior recorre la lista,
	 *  si actual es igual al elemento buscado devuelve  anterior, de lo contrario devuelve nulo.
	 * @param unElemento El elemento al cual se quiere buscar su anterior
	 * @return El elemento anterior si existe
	 */
	public TElemento anterior(TElemento unElemento) {
		TElemento actual=primero;
		TElemento anterior=null;
		while(( actual !=null)&& (actual!= unElemento)){
			anterior= actual;
			actual=actual.siguiente;
		}
		if( actual== null){
			return null;
		}
		return anterior;
	}
	/**
	 * Solamente se devuelve el anterior de la etiqueta buscada.
	 * @param unaEtiqueta La etiqueta del elemento al cual se quiere buscar su anterior
	 * @return El elemento anterior 
	 */
	public TElemento anterior(Comparable unaEtiqueta) {

		return anterior(buscarEtiqueta(unaEtiqueta));
	}
	/**
	 * En un nuevo vector creado , se le va asignando a cada posici�n del vector la etiqueta de un elemento de la lista 
	 * y realizando esto se va recorriendo la lista con una variable temporal hasta llegar al largo de la lista.
	 * @return vectorLista El vector con las etiquetas de la lista
	 */
	public Comparable[] etiquetasToArray() {		
		Comparable [] vectorLista = new Comparable[obtenerCantidadElementos()];

		TElemento elementoActual=primero;
		for(int i=0;i<vectorLista.length;i++){
			vectorLista[i]=elementoActual.etiqueta;
			elementoActual=elementoActual.siguiente;
		}

		return vectorLista;
	}


	/**
	 * Si la lista no es vac�a se inicializa un elemento temporal como el primero de la lista y con el mismo se recorre la lista sea distinto de nulo, 
	 * es as� que mientras se va concatenando en una cadena con las etiquetas de cada elemento separadas por una constante que hace de separador ,luego de agregar la etiqueta del ultimo de la lista,
	 *  se devuelve la cadena con las etiqueta/as o nula en el caso de que la lista sea vac�a.		
	 * @return cadena Con todas las etiquetas de los elementos separados por el SEPARADOR_ELEMENTOS_IMPRESOS
	 */
	public String imprimirEtiquetas(){
		String cadena="";
		if(!esVacia()){
			TElemento elementoActual=primero;
			cadena=elementoActual.imprimirEtiqueta();
			elementoActual=elementoActual.siguiente;
			while((elementoActual!=null)){
				cadena=cadena + SEPARADOR_ELEMENTOS_IMPRESOS+elementoActual.imprimirEtiqueta();
				elementoActual=elementoActual.siguiente;
			}
		}
		return cadena;
	}
	
	/**
	 * Se toman los datos y etiquetas de los elementos de la lista en una variable temporal,
	 *  comenzando por el primero  hasta llegar al ultimo y se van insertando cada uno ordenado en una nueva lista.
	 * 
	 * @return nuevaLista Una nueva lista ordenada
	 */
	public TLista ordenar() {
		TElemento actual=obtenerPrimero();
		TLista nuevaLista=new TLista();
		if(!esVacia()){			
			while(actual!=null){
				TElemento nuevo= new TElemento(actual.etiqueta,actual.datos);
				nuevaLista.insertarOrdenado(nuevo);
				actual=actual.siguiente;
			}			
		}
		return nuevaLista;
	}
	/**
	 * Se quita y devuelve el primero de la lista y el siguiente a este pasa a ser el primero. 
	 * @return temp El primer elemento de la lista
	 */
	public TElemento quitarPrimero() {
		TElemento temp= primero;
		if(temp!=null){
			primero=primero.siguiente;
			return temp;
		}
		return temp;
	}
	
	public void eliminaDuplicados(){
		TElemento unElemento=primero;
		TElemento otro=new TElemento(null,null);
		while(unElemento!=null){
			otro=unElemento.siguiente;
			while(otro!=null){
				if(otro.etiqueta==unElemento.etiqueta){
					eliminar(otro);
				}
				otro=otro.siguiente;
				
			}
			unElemento=unElemento.siguiente;
		}		
	}

}

