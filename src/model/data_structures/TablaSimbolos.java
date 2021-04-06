package model.data_structures;

public class TablaSimbolos<K extends Comparable<K>, V> implements ITablaSimbolos<K, V> {

	ListaEncadenada<NodoTS<K,V>> tabla;
	public TablaSimbolos() {
		tabla = new ListaEncadenada<NodoTS<K,V>>();
	}
	
	/**
	 * Agrega un elemento nuevo a la tabla 
	 * Complejidad O(N)
	 */
	public void put(K k, V v) {
		NodoTS<K,V> nodo = new NodoTS<K,V>(k,v);
		boolean stop = false;
		for(int i=1; i<=tabla.size()&&!stop;i++){
			if(nodo.compareTo(tabla.getElement(i))==0){
				tabla.getElement(i).asignarValor(v);
				stop = true;
			}
		}
		if(!stop){
			tabla.addLast(nodo);
		}
	}

	/**
	 * Metodo que retorna el valor de la llave dada por parametro
	 * Complejidad O(N)
	 */
	public V get(K k) {
		boolean stop = false;
		for(int i=1; i<=tabla.size()&&!stop;i++){
			if(k.compareTo(tabla.getElement(i).darLlave())==0){
				return tabla.getElement(i).darValor();
			}
		}
		return null;
		
	}

	/**
	 * Elimina el valor de una llave K
	 * Complejidad O(N)
	 */
	public V remove(K k) {
		V valor = null;
		for(int i=1; i<=tabla.size();i++){
			if(k.compareTo(tabla.getElement(i).darLlave())==0){
				valor = tabla.getElement(i).darValor();
				tabla.getElement(i).asignarValor(null);
				return valor;
			}
		}
		return null;
	}

	/**
	 * Retorna true si la llave esta almacenada
	 * Complejidad O(N)
	 */
	public boolean contains(K k) {
		for(int i=1; i<=tabla.size();i++){
			if(k.compareTo(tabla.getElement(i).darLlave())==0){
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna true si la tabla tiene datos
	 * Complejidad O(K)
	 */
	public boolean isEmpty() {
		return tabla.isEmpty();
	}

	/**
	 * Retorna la cantidad de duplas en la tabla
	 * Complejidad O(K)
	 */
	public int size() {
		return tabla.size();
	}

	/**
	 * 
	 */
	public ILista<K> keySet() {
		ArregloDinamico<K> llaves = new ArregloDinamico<K>(); 
		for(int i=1;i<=tabla.size();i++){
			llaves.addLast(tabla.getElement(i).darLlave());
		}
		return llaves;
	}

	public ILista<V> valueSet() {
		ArregloDinamico<V> valores = new ArregloDinamico<V>(); 
		for(int i=1;i<=tabla.size();i++){  
			valores.addLast(tabla.getElement(i).darValor());
		}
		return valores;
	}

}
