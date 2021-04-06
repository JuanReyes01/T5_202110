package model.data_structures;

import java.util.ArrayList;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 * @param <T>
 *
 */
public class ArregloDinamico<T> implements ILista<T> { 
		/**
		 * Capacidad maxima del arreglo
		 */
        private int tamanoMax;
		/**
		 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
		 */
        private int tamanoAct;
        /**
         * Arreglo de elementos de tamaNo maximo
         */
        private T elementos[ ];
      

        /**
         * Construir un arreglo con la capacidad maxima inicial.
         * @param max Capacidad maxima inicial
         */
		public ArregloDinamico(int max)
        {
               elementos = (T[]) new Comparable[max];
               tamanoMax = max;
               tamanoAct = 0;
        }
		public ArregloDinamico()
        {
               elementos = (T[]) new Comparable[1];
               tamanoMax = 1;
               tamanoAct = 0;
        }
 /**
		public void invertir() {
			T[] invertida = ( T[]) new Comparable[elementos.length];
			for(int i=tamanoAct; i>0;i--){
				invertida[tamanoAct-i] = elementos[i-1]; 
			}
			elementos = invertida;
		}
		*--------------------------------------------------------------------------------
		*/

public void addFirst(T element) {
	if ( tamanoAct == tamanoMax )
    {  // caso de arreglo lleno (aumentar tamaNo)
        tamanoMax = 2 * tamanoMax;
        T[ ] copia = elementos;
        elementos = (T[]) new Comparable[tamanoMax];
        for ( int i = 0; i < tamanoAct; i++)
        {
         	 elementos[i] = copia[i];
        } 
	    //System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
   }	
   elementos[0] = element;
   int j = 1;
   for(T i:elementos){
	   elementos[j] = i;
	   j++;
   }
   tamanoAct++;
	
}


public void addLast(T element) {
	if ( tamanoAct == tamanoMax )
    {  // caso de arreglo lleno (aumentar tamaNo)
        tamanoMax = 2 * tamanoMax;
        T[ ] copia = elementos;
        elementos = (T[]) new Comparable[tamanoMax];
        for ( int i = 0; i < tamanoAct; i++)
        {
         	 elementos[i] = copia[i];
        } 
	    //System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
   }	
   elementos[tamanoAct] = element;
   tamanoAct++;
	
}


public void insertElement(T element, int pos) {
	if ( tamanoAct == tamanoMax )
    {  // caso de arreglo lleno (aumentar tamaNo)
        tamanoMax = 2 * tamanoMax;
        T[ ] copia = elementos;
        elementos = (T[]) new Comparable[tamanoMax];
        for ( int i = 0; i < tamanoAct; i++)
        {
         	 elementos[i] = copia[i];
        } 
	    //System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
    }
	tamanoAct++;
	T[] nuevo = (T[]) new Comparable[tamanoAct];
	int j=0;
	for(int i=0; i<tamanoAct; i++){
	    if(pos==i){
	    	nuevo[i] = element;
	    }
	    else{
		nuevo[i] = elementos[j];
		j++;
	    }
	}
}

public T removeFirst() {
	T eliminado=elementos[0];
	T[] nuevo = (T[]) new Comparable[elementos.length]; 							
	for(int i=0; i<tamanoAct;i++){
		elementos[i] = elementos[i+1]; 
	}
	tamanoAct = tamanoAct-1;
	return eliminado;
}


public T removeLast() {
	T eliminado = null;
	elementos[tamanoAct-1]=null;
	tamanoAct--;
	return eliminado;
}

public T deleteElement(int pos) {
	T eliminado = null;
	//Le resto 1 a la posicion para compensar por la diferencia 
	//entre la maquina y el usuario (donde el para el usuario quiere borrar
	//el elemento en pos=2 pero digita la posicion 3)
	int posReal = pos-1;
	boolean elimina = false;
	for(int i=0;i<tamanoAct;i++){
		if(i==posReal){
			elimina = true;
			eliminado = elementos[posReal];
		}
		else if(elimina){
			elementos[i-1] = elementos[i];
		}
	}
	tamanoAct--;
	return eliminado;
}

public T firstElement() {
	
	return elementos[0];
}
public T lastElement() {
	//Como tamaño actual actua como .size() tengo que restar -1 
	//para que me de la respuesta correcta
	return elementos[tamanoAct-1];
}

public T getElement(int pos) {
	T buscado = null;
	int posReal = pos-1;
	if(posReal<=tamanoMax){
		return elementos[posReal];
	}
	return buscado;
}

public int size() {

	return tamanoAct;
}

public boolean isEmpty() {
	return (tamanoAct==0)?false:true;
}

public int isPresent(T element) {
	int pos = -1;
	for(int i=0; i<elementos.length;i++){
		if(elementos[i]!=null&&(((Comparable) elementos[i]).compareTo(element))==0){
			//Sumo 1 para devolver la posicion aparente al usuario
			return i+1;
		}
	}
	return pos;
}

public void exchange(int pos1, int pos2) {
	int posReal1 = pos1-1;
	int posReal2 = pos2-1;
	T temp = elementos[posReal1];
	elementos[posReal1] = elementos[posReal2];
	elementos[posReal2] = temp;
}

public void changeInfo(int pos, T elem) {
	int posReal = pos-1;
	elementos[posReal] = elem;
}

/**
* Crear una sublista de la lista original (this).
* Los elementos se toman en el mismo orden como aparecen en la lista original (this).
* @param número de elementos que contendrá la sublista. Si el número es superior al tamaño
* original de la lista, se obtiene una copia de la lista original.
* @return sublista creada con la misma representación de la lista original (this).
*/ 
public ArregloDinamico<T> sublista(int numElementos) {
	ArregloDinamico<T> copia =  new ArregloDinamico<T>();
	if(numElementos >= tamanoAct){
		copia = this;
	}
	else{
		for(int i = 1; i<=numElementos;i++)
		{
			T x = getElement(i);
			copia.addLast(x);
		}
	}
	return copia;
}


@Override
public ArregloDinamico<T> subList(int i, int f) {
	ArregloDinamico<T> nueva =  new ArregloDinamico<T>();
	for(int j=i;j<=f;j++){
		nueva.addLast(getElement(j));
	}
	return nueva;
}
			
		}
