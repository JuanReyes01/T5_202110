package model.data_structures;

public interface ITablaSimbolos<K extends Comparable<K>,V> {
	void put(K k, V v);
	V get(K k);
	V remove(K k);
	boolean contains (K k);
	boolean isEmpty ( );
	int size ( );
	ILista<K> keySet();
	ILista<V> valueSet();
}
