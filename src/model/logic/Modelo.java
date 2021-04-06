package model.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;
import model.data_structures.ILista;
import model.data_structures.ITablaSimbolos;
import model.data_structures.ListaEncadenada;
import model.data_structures.TablaSimbolos;
import model.utils.Ordenamiento;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	private static final String VIDEO = "./data/videos-all.csv";
	/**
	 * Atributos del modelo del mundo
	 */
	private ILista<Categoria> categorias;
	private ILista<YoutubeVideo> datos;
	private ITablaSimbolos<String, ILista<YoutubeVideo>> tabla;
	private Ordenamiento<YoutubeVideo> o;
	
	public Modelo()
	{
		datos = new ArregloDinamico<YoutubeVideo>();
		categorias = new ArregloDinamico<Categoria>();
		tabla = new TablaSimbolos<String,ILista<YoutubeVideo>>();
		o = new Ordenamiento<YoutubeVideo>();
		
	}	
	
	public String cargarDatos() throws IOException, ParseException{
		Reader in = new FileReader(VIDEO);
		int c = 0;
		long tot = 0;
		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);	
		for (CSVRecord record : records) {
		    String id = record.get(0);
		    String trending = record.get(1);
		    String titulo = record.get(2);
		    String canal = record.get(3);
		    String YoutubeVideo = record.get(4);
		    String fechaP = record.get(5);
		    String tags = record.get(6);
		    String vistas = record.get(7);
		    String likes  = record.get(8);
		    String dislikes = record.get(9);
		    String coment = record.get(10);
		    String foto = record.get(11);
		    String nComent = record.get(12);
		    String rating = record.get(13);
		    String vidErr = record.get(14);
		    String descripcion = record.get(15);
		    String pais = record.get(16);
		    //--------------------------------------------------------------------
		    if(!id.equals("video_id")){
		    SimpleDateFormat formato = new SimpleDateFormat("yyy/MM/dd");
		    String[] aux = trending.split("\\.");
		    Date fechaT = formato.parse(aux[0]+"/"+aux[2]+"/"+aux[1]);
		    SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");		   
		    Date fechaPu = formato2.parse(fechaP);
		    YoutubeVideo nuevo = new YoutubeVideo(id, fechaT, titulo, canal, Integer.parseInt(YoutubeVideo), fechaPu, tags, Integer.parseInt(vistas), Integer.parseInt(likes), Integer.parseInt(dislikes), Integer.parseInt(coment), foto, (nComent.equals("FALSE")?false:true), (rating.equals("FALSE")?false:true), (vidErr.equals("FALSE")?false:true), descripcion, pais);
		    String cat = darNombreCategoria(nuevo.darId_categoria());
		    String key = nuevo.darPais().trim()+"-"+cat.trim();
		    int aux2 = tabla.keySet().isPresent(key);
		    if(aux2 ==-1){
		    	ArregloDinamico<YoutubeVideo> valor = new ArregloDinamico<YoutubeVideo>();
		    	valor.addLast(nuevo);
		    	long miliI = System.currentTimeMillis();
		    	tabla.put(key, valor);
		    	long miliF = System.currentTimeMillis();
		    	tot += (miliF-miliI);
		    }
		    else{
		    	ArregloDinamico<YoutubeVideo> valor = (ArregloDinamico<model.logic.YoutubeVideo>) tabla.get(key);
		    	valor.addLast(nuevo);
		    	//long miliI = System.currentTimeMillis();
		    	//tabla.put(key, valor);
		    	//long miliF = System.currentTimeMillis();
		    	//tot += (miliF-miliI); 
		    }
		    c++;
		    }
		}
		float f = (float) ((tot*1.0)/tabla.size());
		return "Tiempo de ejecución promedio: "+f+" milisegundos, \nTotal llaves: "+ tabla.size()+" \nTotal datos cargados: "+c ;
	}

	public void cargarId() throws IOException, FileNotFoundException{
		Reader in = new FileReader("./data/category-id.csv");
		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);	
		for (CSVRecord record : records) {
			String n = record.get(0);
			if(!n.equals("id	name")){
				String[] x = n.split("	 ");
				Categoria nueva =  new Categoria(Integer.parseInt(x[0]), x[1].trim());
				agregarCategoria(nueva);
			}
		}
		
	}
	
	public ILista<Categoria> darCategorias(){
		return categorias;
	}
	
	public void agregarCategoria(Categoria elem){
		categorias.addLast(elem);
	}
	
	/**
	 * Da el nombre de la categoria segun el numero
	 * @param categoria, numero de la categoria
	 * @return el nombre de la categoria
	 */
	public String darNombreCategoria(int categoria){
		for(int i=1; i<=categorias.size();i++){
			if(categorias.getElement(i).darId()==(categoria)){
				return categorias.getElement(i).darNombre(); 
			}
		}
		return null;
	}
	
	//Busqueda binaria, para practicar
	/**
	 * Metodo que sobreescribe la busqueda que realiza arreglo dinamico con una busqueda binaria
	 * Esto es posible porque la lista de categorias esta ordenada desde que se carga 
	 */
	public Categoria buscarCategoriaBin(int pos){
		int i = 1;
		int f = categorias.size();
		int elem = -1;
		boolean encontro = false;
		while ( i <= f && !encontro )
		{
		int m = (i + f) / 2;
		if ( categorias.getElement(m).darId() == pos )
		{
		elem = m;
		encontro = true;
		}
		else if ( categorias.getElement(m).darId() > pos )
		{
		f = m - 1;
		}
		else
		{
		i = m + 1;
		}
		}
		return categorias.getElement(elem);
	}

public ILista<YoutubeVideo> req2(String categoria, String pais){
	String key = pais+"-"+categoria;
	if(tabla.keySet().isPresent(key)==-1)
		return null;
	else{
		return tabla.get(key);
	}
}

public int rand(){
	return (int) (Math.random() * (datos.size())+1);
}

public float pruebaGet(){
	int i = 0;
	long total = 0;
	ILista<String> llaves = tabla.keySet();
	long miliI = 0;
	long miliF = 0;
	while(i<700){
		String key = llaves.getElement(rand());
		miliI = System.currentTimeMillis();
		tabla.get(key);
		miliF = System.currentTimeMillis();
		total+=(miliF-miliI);
		i++;
	}
	while(i<1000){
		String key = (char)rand()+""+(char)rand();
		miliI = System.currentTimeMillis();
		tabla.get(key);
		miliF = System.currentTimeMillis();
		total+=(miliF-miliI);
		i++;
	}
	return (float) ((total*1.0)/i);
}
}


		