package model.data_structures;

import java.util.Random;

import sun.invoke.empty.Empty;

public class TablaHashLinearProbing<K extends Comparable<K>, V> implements ITablaSimbolos <K, V>{

	private int n;
	private int m;
	private int p;
	private ArregloDinamico<NodoTS<K,V>> tablaHash; 
	private NodoTS<K, V> empty;

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
	
	public TablaHashLinearProbing(int c) {
		n = 0;
		if(!isPrime(c)){
			p = nextPrime(c); 
		}
		else{p = c;}
		tablaHash = new ArregloDinamico<NodoTS<K, V>>(p);
	}
	
	public void put(K k, V v) {
		int h = hash(k);
		NodoTS<K, V> nuevo = new NodoTS<K,V>(k, v);
		if(tablaHash.getElement(h)==null){
			tablaHash.insertElement(nuevo, h);
			n++;
		}
		else if(tablaHash.getElement(h) == empty){
			int aux = h+1;
			int x = 0;
			NodoTS<K, V> nodo = tablaHash.getElement(aux);
			while(nodo!=null){
				if(nodo == nuevo){
					tablaHash.insertElement(nuevo, aux);
					n++;
					break;
				}
				aux++;
				if(aux>tablaHash.size()) aux = 0;
				nodo = tablaHash.getElement(aux);
				x++;
				if(x>tablaHash.size()) break;
			}
			tablaHash.insertElement(nuevo, h);
		}
		else if(tablaHash.getElement(h)!=nuevo){
			int aux = h+1;
			int x = 0;
			NodoTS<K, V> nodo = tablaHash.getElement(aux);
			while(nodo!=null){
				if(nodo == nuevo){
					tablaHash.insertElement(nuevo, aux);
					n++;
					break;
				}
				aux++;
				if(aux>tablaHash.size()) aux = 0;
				nodo = tablaHash.getElement(aux);
				x++;
				if(x>tablaHash.size()) break;
			}
			if(nodo==null){
				tablaHash.insertElement(nuevo, aux);
				n++;
			}
		}
	}

	public V get(K k) {
		int h = hash(k);
		if(tablaHash.getElement(h).darLlave()==k){
			return tablaHash.getElement(h).darValor();
		}
		else if(tablaHash.getElement(h).darLlave()!=k){
			int aux = h+1;
			int x = 0;
			NodoTS<K, V> nodo = tablaHash.getElement(aux);
			while(nodo!=null){
				if(nodo.darLlave()==k){
					return nodo.darValor();
				}
				aux++;
				if(aux>tablaHash.size()) aux = 0;
				nodo = tablaHash.getElement(aux);
				x++;
				if(x>tablaHash.size()) break;
			}
		}
		return null;
	}

	public V remove(K k) {
		int h = hash(k);
		if(tablaHash.getElement(h).darLlave()==k){
			V valor = tablaHash.getElement(h).darValor();
			tablaHash.deleteElement(h);
			return valor; 
		}
		else if(tablaHash.getElement(h).darLlave()!=k){
			int aux = h+1;
			int x = 0;
			NodoTS<K, V> nodo = tablaHash.getElement(aux);
			while(nodo!=null){
				if(nodo.darLlave()==k){
					V valor = tablaHash.getElement(aux).darValor();
					tablaHash.deleteElement(aux);
					return valor; 
				}
				aux++;
				if(aux>tablaHash.size()) aux = 0;
				nodo = tablaHash.getElement(aux);
				x++;
				if(x>tablaHash.size()) break;
			}
		}
		return null;
	}			

	public boolean contains(K k) {
		return get(k)!=null;
	}

	public boolean isEmpty() {
		return n==0;
	}

	public int size() {
		return n;
	}

	public ILista<K> keySet() {
		ArregloDinamico<K> llaves = new ArregloDinamico<K>(); 
		for(int i=1;i<=tablaHash.size();i++){
			llaves.addLast(tablaHash.getElement(i).darLlave());
		}
		return llaves;
	}

	public ILista<V> valueSet() {
		ArregloDinamico<V> valores = new ArregloDinamico<V>(); 
		for(int i=1;i<=tablaHash.size();i++){  
			valores.addLast(tablaHash.getElement(i).darValor());
		}
		return valores;
	}

	@Override
	public int hash(K key) {
		int h = hash(key);
		Random r = new Random();
		int a = r.nextInt(p-1);
		int b = r.nextInt(p);
		return (Math.abs(a*(h)+b)%p)%m;
	}
	
	public void reHash(){
		 TablaHashLinearProbing<K, V> nueva = new TablaHashLinearProbing<>(nextPrime(m));
		 ArregloDinamico<K> llaves = (ArregloDinamico<K>) keySet();
		 for(int i=0; i<llaves.size();i++){
			 nueva.put(llaves.getElement(i), get(llaves.getElement(i)));
		 }
		 tablaHash = nueva;
	}
}
