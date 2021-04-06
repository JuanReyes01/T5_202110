package model.data_structures;

public interface ILista <T>{
	
	void addFirst(T element);
	void addLast(T element);
	void insertElement(T element, int pos);
	T removeFirst();
	T removeLast();
	T deleteElement(int pos);
	T firstElement();
	T lastElement();
	T getElement(int pos);
	int size();
	boolean isEmpty();
	int isPresent(T element);
	void exchange(int pos1, int pos2);
	void changeInfo(int pos, T elem);
	
	/**
	* Crear una sublista de la lista original (this).
	* Los elementos se toman en el mismo orden como aparecen en la lista original (this).
	* @param número de elementos que contendrá la sublista. Si el número es superior al tamaño
	* original de la lista, se obtiene una copia de la lista original.
	* @return sublista creada con la misma representación de la lista original (this).
	*/
	ILista<T> sublista(int numElementos);
	ILista<T> subList(int i, int f);
}
