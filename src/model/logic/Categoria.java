package model.logic;

public class Categoria implements Comparable<Categoria>{
	private int id;
	private String nombre;
	public Categoria(int id, String nm) {
		this.id = id;
		nombre = nm;
	}
	
	public String darNombre(){
		return nombre;
	}
	
	public int darId(){
		return id;
	}

	public int compareTo(Categoria o) {
		return this.id-o.darId();
	}
}
