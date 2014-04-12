package Estructuras;



public class TStack extends TLista {

	/**
	 * Solamente es posible insertar al final del  stack
	 * @param unElemento, el elemento a insertar
	 * @return si fue posible la inserccion
	 */
	public boolean ponerEnStack(TElemento unElemento){
		if(unElemento!=null){
			TElemento insertar=new TElemento(unElemento.etiqueta,unElemento.datos);
			return insertarAlFinal(insertar);	
		}
		return false;
	}

	/**
	 * Solamente es posible quitar el ultimo del stack
	 * @return si fue posible la eliminacion
	 */
	public boolean quitarDeStack(){
		
		return eliminar(this.obtenerUltimo());
	}

}
