package model.data_structures;

public class NodoTS<K extends Comparable<K>, V> implements Comparable<NodoTS<K,V>> {

	private K llave;
	private V valor;
	
	public NodoTS(K llave, V valor) {
		this.llave = llave;
		this.valor= valor;
	}
	
	public void asignarValor(V valor){
		this.valor = valor;
	}
	
	public K darLlave(){
		return llave;
	}
	
	public V darValor(){
		return valor;
	}
	
	
	
	/** La comparación de dos NodoTS depende de sus llaves */
	public int compareTo(NodoTS<K, V> otro) {		
		return this.llave.compareTo(otro.llave);
	}

}
