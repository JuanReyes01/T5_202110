package view;

import model.data_structures.ILista;
import model.logic.Categoria;
import model.logic.Modelo;
import model.logic.YoutubeVideo;

public class View 
{
	
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Inicializar estructuras de datos");
			System.out.println("2. Requerimiento 2");
			System.out.println("3. Prueba .get()");
			System.out.println("4. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		

		
		/**
		 * Metodo que imprime una tabla con la informacion del video, se imprime desde el primero hasta s.
		 * @param l, lista de los videos que se quieren imprimir.
		 * @param s, numero de videos de la lista que se quieren imprimir
		 */
		public void imprimirVideoReq1(ILista<YoutubeVideo> l, int s){
			System.out.println("=====================================");
			System.out.println("LISTA VIDEOS:");
			System.out.println("=====================================");
			for(int i=1;i<=s;i++){
				YoutubeVideo a = (YoutubeVideo) l.getElement(i); 
				System.out.println("Video #"+i);
				System.out.println(" title: "+a.darTitulo());
				System.out.println(" views: "+a.darViews());
				System.out.println(" likes: "+a.darLikes());
				System.out.println(" dislikes: "+a.darDislikes());
			}
			System.out.println("=====================================");
		}

		public void imprimirVideoReq4(ILista<YoutubeVideo> l, int s){
			System.out.println("=====================================");
			System.out.println("LISTA VIDEOS:");
			System.out.println("=====================================");
			for(int i=1;i<=s;i++){
				YoutubeVideo a = (YoutubeVideo) l.getElement(i); 
				System.out.println("Video #"+i);
				System.out.println(" title: "+a.darTitulo());
				System.out.println(" cannel_title: "+a.darCanal());
				System.out.println(" publish_time: "+a.darFechaP());
				System.out.println(" views: "+a.darViews());
				System.out.println(" likes: "+a.darLikes());
				System.out.println(" dislikes: "+a.darDislikes());
				System.out.println(" tags: "+a.darTags());			
			}
			System.out.println("=====================================");
		}
		
		
		public void printCategorias(Modelo modelo){
			ILista<Categoria> l = modelo.darCategorias();
			for(int i=1; i<=l.size();i++){
				System.out.println(l.getElement(i).darNombre()+": "+l.getElement(i).darId());
			}
		}
}
