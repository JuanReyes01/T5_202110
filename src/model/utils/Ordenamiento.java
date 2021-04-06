package model.utils;

import java.util.Comparator;

import model.data_structures.ILista;

public final class Ordenamiento<T extends Comparable<T>> {

	public Ordenamiento() {
		
	}

	
	public ILista<T> ordenarInsercion(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	{
		 int n = lista.size();
		    for (int i = 1+1; i <= n; i++)
		     {
		        boolean enPosicion = false;
		           for (int j = i; j > 1 && !enPosicion; j -= 1)
					     {
						  int factorComparacion = (ascendente?1:-1) * criterio.compare(lista.getElement(j), lista.getElement(j-1));
							if (factorComparacion < 0){
								lista.exchange(j, j-1);
							   }
							else
							   {
							     enPosicion = true;
							   }
					     }
		     }
		    return lista;
	}
	
	
	public ILista<T> ordenarShell(ILista<T> lista, Comparator<T> criterio, boolean ascendente){
		int h = 1;
		while(h<(lista.size()/3)){
			h = 3*h+1; 
		}
		while(h>=1){
			for(int i=h+1; i<=lista.size();i++){
				boolean pos = false;
				for (int j=i; j>h&&!pos; j-=h){
					if(((ascendente?1:-1) * criterio.compare(lista.getElement(j), lista.getElement(j-h)))<0){
						lista.exchange(j, j-h);
					}
					else
						pos = true;
				}
			}
			h = h/3;
		}
		return lista;
	}

	
	 
  public ILista<T> ordenarMerge(ILista<T> lista, Comparator<T> criterio, boolean ascendente){
	  if(lista.size()>1){
		ILista<T> l1 =lista.subList(1, lista.size()/2); 
		ILista<T> l2 =lista.subList((lista.size()/2)+1, lista.size()); 
		ordenarMerge(l1, criterio, ascendente);
		ordenarMerge(l2, criterio, ascendente);
		int i,j,k;
		i=j=k=1;
		
		while(i<=l1.size() && j<=l2.size()){
			if((ascendente?1:-1)*(criterio.compare(l1.getElement(i), l2.getElement(j)))<=0){
				lista.changeInfo(k, l1.getElement(i));
				i++;
			}
			else{
				lista.changeInfo(k, l2.getElement(j));
				j++;
			}
			k++;
		}
		
		while(i<=l1.size()){
			lista.changeInfo(k, l1.getElement(i));
			i++;
			k++;
		}
		while(j<=l2.size()){
			lista.changeInfo(k, l2.getElement(j));
			j++;
			k++;
		}
	  }
	  return lista;
  }
  
  private int particion(ILista<T> lista, Comparator<T> criterio, boolean ascendente, int lo, int hi){
	  	 int i = lo;
	  	 int j = lo;
	  	 while(j<hi){
	  		 if(((ascendente?1:-1)*(criterio.compare(lista.getElement(j), lista.getElement(hi))))<0){
	  			 lista.exchange(i, j);
	  			 i++;
	  		 }
	  		 j++;
	  	 }
	  	 lista.exchange(i, hi);
	  	 return i;
  }
  
  private void sort(ILista<T> lista, Comparator<T> criterio, boolean ascendente, int lo, int hi){
	  if(lo<hi+1){
		  	int pivot = particion(lista, criterio, ascendente, lo, hi);
		  	sort(lista, criterio, ascendente, lo, pivot-1);
		  	sort(lista, criterio, ascendente, pivot+1, hi);
	  }
  }
  
  public ILista<T> ordenarQuickSort(ILista<T> lista, Comparator<T> criterio, boolean ascendente){
	  sort(lista, criterio, ascendente, 1, lista.size());
	  return lista;
  }
 
 
}
