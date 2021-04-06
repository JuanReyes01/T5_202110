package model.data_structures;

public class NodoLista<T extends Comparable<T>> {
	private T info;			
	private NodoLista<T> next;
	
	public NodoLista(T informacion){
		info = informacion;
	}
	
	public T getInfo(){
		return info;
	}
	
	public NodoLista<T> getNext(){
		return next;
	}
	
	public void setNext(NodoLista<T> nodeNext){
		next = nodeNext;
	}
	
	public void disconnectNext(NodoLista<T> node){
		node.setNext(null);
	}
	
	public void setInfo(T info){
		this.info = info;
	}
}
