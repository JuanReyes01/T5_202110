package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import model.data_structures.ILista;
import model.logic.Modelo;
import model.logic.YoutubeVideo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}


	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		Object respuesta = null;

		
		while(!fin){
			view.printMenu();
			
			int option = lector.nextInt();
			switch(option){
				case 1:
					view.printMessage("Cargando datos en el sistema...");
					String r;
				try {
					modelo.cargarId();
					r = modelo.cargarDatos();
					view.printMessage("------------------------------------------");
					view.printMessage(r);
					view.printMessage("-------");
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}											
					break;

				case 2:
					view.printMessage("Ingrese: pais, categoria");
					dato = lector.next();
					String[] i = dato.split(",");
					ILista<YoutubeVideo> l  = modelo.req2(i[1].replace("-", " ").trim(), i[0].replace("-", " ").trim());
					if(l!=null)
						view.imprimirVideoReq1(l,l.size());
					else
						view.printMessage("Caso especial: no hay pais con la categoria seleccionada(o escribiste mal)");
					break;
					
				case 3:
					view.printMessage("Realizando Prueba...");
					view.printMessage("El tiempo promedio es: "+modelo.pruebaGet()+" Milisegundos");
					break;
				case 4: 
					view.printMessage("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;				
				
				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		}
	}
