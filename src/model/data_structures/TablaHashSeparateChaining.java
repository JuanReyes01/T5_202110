package model.data_structures;

import java.util.Map;
import java.util.Queue;
import java.util.Random;

import jdk.nashorn.internal.runtime.arrays.NumericElements;

public class TablaHashSeparateChaining<K extends Comparable<K>, V> implements ITablaSimbolos <K, V> {
	
	private int n;
	private int m;
	private int p;
    private ArregloDinamico<ArregloDinamico<NodoTS>> tablaHash; 
    
    /*
     * Metodos enviados por el profesor
     */
    
    // Function that returns true if n
    // is prime else returns false
    static boolean isPrime(int n)
    {
        // Corner cases
        if (n <= 1) return false;
        if (n <= 3) return true;   
        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0) return false;       
        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;       
        return true;
    }

    // Function to return the smallest
    // prime number greater than N
    static int nextPrime(int N)
    {   
        // Base case
        if (N <= 1)
            return 2;  
        int prime = N;
        boolean found = false;

        // Loop continuously until isPrime returns
        // true for a number greater than n

        while (!found)
        {
            prime++;
            if (isPrime(prime))
                found = true;
        }
        return prime;
    }
   //-----------------------------------------------------------------------------

    /**
     * Inicializa una tabla de simbolos vacia.
     */
    public TablaHashSeparateChaining(int capacidad)  {
    	n = 0;
    	m = capacidad;
    	if(!isPrime(capacidad))
    		p = nextPrime(capacidad);
    	tablaHash = new ArregloDinamico<ArregloDinamico<NodoTS>>(capacidad);
    }
    
    public void put(K key, V val) {
    	int h = hash(key);
    	if(get(key)==null){
    		tablaHash.getElement(h).insertElement(new NodoTS<K,V>(key, val),h);
    	}
    	else{
    		for(int i=0; i<tablaHash.getElement(h).size();i++)
    			if(tablaHash.getElement(h).getElement(i).darLlave().equals(key)){
//        		if(tablaHash.getElement(h).getElement(i).darValor() instanceof ArregloDinamico){
//        			tablaHash.getElement(h).getElement(i).darValor().addLast(val);
//        		}
//        		else{
//        		ArregloDinamico<V> n = new ArregloDinamico<V>();
//        		n.addLast(tablaHash.getElement(h).getElement(i).darValor());
//        		n.addLast(element);
    				tablaHash.getElement(h).getElement(i).asignarValor(val);
    				n++;
//        		}
    			}
    	}
    } 
    

    /**
     * @param  key la llave a buscar
     * @return el valor asociado con la llave en la tabal de simbolos, null si no la encuentra
     * @throws IllegalArgumentException si la llave es null
     */
    public V get(K key) {
    	if (key == null) throw new IllegalArgumentException("argument to get() is null");
    	int i = hash(key);
        ArregloDinamico<NodoTS> lista = tablaHash.getElement(i);
        for(int j=0; i<lista.size();i++){
        	if(lista.getElement(j).darLlave().equals(key)){
        		return (V) lista.getElement(j).darValor();
        	}
        }
    	return null;
    } 
      
    
    /**
     * Borra la llave especifica y su valor asociado de la tabla.    
     * (si la llave esta en la tabla).  
     * @param  key la llave a remover
     * @throws IllegalArgumentException si la llave es null
     */
    public V remove(K key) {
    	int i = hash(key);
    	ArregloDinamico<NodoTS> lista = tablaHash.getElement(i);
    	if(get(key)!=null){
    		for(int j=0; i<lista.size();i++){
    			if(lista.getElement(j).darLlave().equals(key)){
    				V b = (V) lista.getElement(j).darValor();
    				lista.deleteElement(j);
    				return b;
    			}
    		}
    	}
    	return null;
    }
    

    /**
     * @param  key la llave a buscar
     * @return {@code true si el simbolo contiene la llave};
     *         {@code false de lo contrario.
     * @throws IllegalArgumentException si la llave es null
     */
    public boolean contains(K key) {
    	return (get(key)==null)?false:true;
    } 

    /**
     * @return el numero de parejas K-V en la tabla de simbolos.
     *
     */
    public int size() {
        return n;
    } 

    /**
     * @return {@code true si la tabla de simbolos esta vacia;
     *         {@code false de lo contrario.
     */
    public boolean isEmpty() {
        return size() == 0;
    }



    public ArregloDinamico<K> keySet(){
    	ArregloDinamico<K> llaves = new ArregloDinamico<K>();
    	for(int i=0; i<tablaHash.size(); i++){
    		ArregloDinamico<NodoTS> lista = new ArregloDinamico<NodoTS>();  
    		for(int j=0; j<lista.size();j++){
    			llaves.addLast((K) lista.getElement(j).darLlave());
    		}
    	}
    	return llaves;
    } 



	@Override
	public ILista<V> valueSet() {
		ArregloDinamico<V> value = new ArregloDinamico<V>();
		for(int i=0; i<tablaHash.size(); i++){
			ArregloDinamico<NodoTS> lista = new ArregloDinamico<NodoTS>();  
			for(int j=0; j<lista.size();j++){
				value.addLast((V) lista.getElement(j).darLlave());
			}
		}
		return value;
	} 

	
	@Override
	public int hash(K key) {
		int h = hash(key);
		Random r = new Random();
		int a = r.nextInt(p-1);
		int b = r.nextInt(p);
		return (Math.abs(a*(h)+b)%p)%m;
	}

}


