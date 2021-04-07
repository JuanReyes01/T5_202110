package model.data_structures;

import java.util.Map;

import sun.misc.Queue;

public class TablaHashLinearProbing<K extends Comparable<K>, V> implements ITablaSimbolos<K, V> {
	
	// debe ser potencia de 2
    private static final int CAPACIDAD_INICIAL = 4;

    private int n;           // numero de parejas K-V en la tabla de simbolos
    private int m;           // tamanio de la tabla linear probing
    private K[] llaves;      // llaves
    private V[] valores;    //  valores


    /**
     * Inicializa una tabla de simbolos vacia.
     */
    public TablaHashLinearProbing() {
        this(CAPACIDAD_INICIAL);
    }

    /**
     * Inicializa una tabla de simbolos vacia con la capacidad inicial especificada.
     *
     * @param capacidad capacidad inicial
     */
    public TablaHashLinearProbing(int capacidad) {
        m = capacidad;
        n = 0;
        llaves = (K[])   new Object[m];
        valores = (V[]) new Object[m];
    }

    /**
     * @return el numero de parejas K-V en la tabla de simbolos.
     */
    public int size() {
        return n;
    }

    /**
     * @return {@code  true si la tabla de simbolos esta vacia;
     *         {@code false de lo contrario.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
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

    // establece la tabla de hash dependiendo de la capacidad.
    private void resize(int capacidad) {
       TablaHashLinearProbing<K, V> temp = new TablaHashLinearProbing<K, V>(capacidad);
        for (int i = 0; i < m; i++) {
            if (llaves[i] != null) {
                temp.put(llaves[i], valores[i]);
            }
        }
       llaves = temp.llaves;
        valores = temp.valores;
        m    = temp.m;
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

        // dobla el tamaño de la tabla si ya tiene almenos la mitad llena
        if (n >= m/2) resize(2*m);

        int i;
        for (i = hash(key); llaves[i] != null; i = (i + 1) % m) {
            if (llaves[i].equals(key)) {
                valores[i] = val;
                return;
            }
        }
        llaves[i] = key;
        valores[i] = val;
        n++;
    }

    /**
     * @param  key la llave a buscar
     * @return el valor asociado con la llave en la tabal de simbolos, null si no la encuentra
     * @throws IllegalArgumentException si la llave es null
     */
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for (int i = hash(key); llaves[i] != null; i = (i + 1) % m)
            if (llaves[i].equals(key))
                return valores[i];
        return null;
    }

    /**
     * Borra la llave especifica y su valor asociado de la tabla.    
     * (si la llave esta en la tabla).  
     * @param  key la llave a remover
     * @throws IllegalArgumentException si la llave es null
     */
    public V remove(K key) {
        if (key == null) return null ;
        if (!contains(key)) ;

        // encuentra la posicion de la llave
        int i = hash(key);
        while (!key.equals(llaves[i])) {
            i = (i + 1) % m;
        }

        // borra la llave y el valor asociado
        llaves[i] = null;
        valores[i] = null;


        i = (i + 1) % m;
        while (llaves[i] != null) {
            // borra llaves[i] y valores[i] y reinserta
            K   llave = llaves[i];
            V valor = valores[i];
            llaves[i] = null;
            valores[i] = null;
            n--;
            put(llave, valor);
            i = (i + 1) % m;
        }

        n--;

        // reduce el tamaño de la lista si el factor de carga es mayor a 0.75
        if (n > 0 && n <= m/8) resize(m/2);

        assert check();
        return (V) llaves[i];
    }

    /**
     * Retorna todas las llaves en la tabla como un iterable.
     * @return todas las llaves en la tabla de simbolos
     */
    public Iterable<K> keys() {
        Queue<K> queue = new Queue<K>();
        for (int i = 0; i < m; i++)
            if (llaves[i] != null) queue.enqueue(llaves[i]);
        return (Iterable<K>) queue;
    }
    

    // check de integridad - no checkear despues de cada put(),
    // porque la integridad del check no se matiene durante un remove() 
     
    private boolean check() {

        // revisa que la tabla esta llena a la mitad
        if (m < 2*n) {
            System.err.println("Hash table size m = " + m + "; array size n = " + n);
            return false;
        }

        // revisa que cada llave en la tabla se puede encontrar con un get()
        for (int i = 0; i < m; i++) {
            if (llaves[i] == null) continue;
            else if (get(llaves[i]) != valores[i]) {
                System.err.println("get[" + llaves[i] + "] = " + get(llaves[i]) + "; vals[i] = " + valores[i]);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) { 
        TablaHashLinearProbing<String, Integer> st = new TablaHashLinearProbing<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keySet()) 
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
