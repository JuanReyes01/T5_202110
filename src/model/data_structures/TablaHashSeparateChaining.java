package model.data_structures;

import java.util.Map;
import java.util.Queue;

import jdk.nashorn.internal.runtime.arrays.NumericElements;

public class TablaHashSeparateChaining<K extends Comparable<K>, V> implements ITablaSimbolos <K, V> {
	
	private static final int CAPACIDAD_INICIAL = 4;

    private int n;                                // numero de parejas K-V
    private int m;                                // Tamanio de la tabla hash
    private TablaSimbolos<K, V>[]  st;  


    /**
     * Inicializa una tabla de simbolos vacia.
     */
    public TablaHashSeparateChaining()  {
        this(CAPACIDAD_INICIAL);
    } 

    /**
     * Inicializa una tabla de simbolos vacia con cadenas.
     * @param m Numero inicial de cadenas
     */
    public TablaHashSeparateChaining(int m) {
        this.m = m;
        st = (TablaSimbolos<K, V>[]) new TablaSimbolos<>()];
        for (int i = 0; i < m; i++)
            st[i] = new TablaSimbolos()<K, V>();
    } 

    // establece la tabla de hash dependiendo del numero de cadenas
    private void resize(int chains) {
        TablaHashSeparateChaining<K, V> temp = new TablaHashSeparateChaining<K, V>(chains);
        for (int i = 0; i < m; i++) {
            for (K key : st[i].keySet()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m  = temp.m;
        this.n  = temp.n;
        this.st = temp.st;
    }

    // funcion hash para las llaves - devuelve un valor entre 0 y m-1
    private int hashTextbook(K key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // funcion hash para las llaves - devuelve un valor entre 0 y m-1 (se asume que m es potencia de 2)
    public int hash(K key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (m-1);
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

    /**
     * @param  key la llave a buscar
     * @return {@code true si el simbolo contiene la llave};
     *         {@code false de lo contrario.
     * @throws IllegalArgumentException si la llave es null
     */
    public boolean contains(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    } 

    /**
     * @param  key la llave a buscar
     * @return el valor asociado con la llave en la tabal de simbolos, null si no la encuentra
     * @throws IllegalArgumentException si la llave es null
     */
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        return st[i].get(key);
    } 

    /**
     * Inserta la dupla de valor-llave en la tabla de simbolos, sobreescribiendo los valores antiguos
     * si la tabla ya contenia la llave especificada
     * Borra la llave especifica y su valor asociado de la tabla si el valor especifico en null.
     * @param  key la llave a ingresar 
     * @param  val el valor a ingresar
     * @throws IllegalArgumentException si la llave es null
     */
    public void put(K key, V val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            remove(key);
            return;
        }

        // dobla el tamaño de la lista si el ancho es mayor a 10
        if (n >= 10*m) resize(2*m);

        int i = hash(key);
        if (!st[i].contains(key)) n++;
        st[i].put(key, val);
    } 

    /**
     * Borra la llave especifica y su valor asociado de la tabla.    
     * (si la llave esta en la tabla).  
     * @param  key la llave a remover
     * @throws IllegalArgumentException si la llave es null
     */
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        if (st[i].contains(key)) n--;
        st[i].remove(key);

        //  reduce el tamaño de la lista si el ancho es menor a 2
        if (m > CAPACIDAD_INICIAL && n <= 2*m) resize(m/2);
		return (V) st[i];
    } 

    public Iterable<K> keys() {
        Queue<K> queue = new Queue<K>();
        for (int i = 0; i < m; i++) {
            for (K key : st[i].keySet())
                ((Object) queue).enqueue(key);
        }
        return queue;
    } 


    /**
     */
    public static void main(String[] args) { 
       TablaHashSeparateChaining<String, Integer> st = new TablaHashSeparateChaining<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys()) 
            StdOut.println(s + " " + st.get(s)); 

    }



	@Override
	public ILista<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILista<V> valueSet() {
		// TODO Auto-generated method stub
		return null;
	}


}


