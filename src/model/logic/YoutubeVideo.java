package model.logic;

import java.util.Comparator;
import java.util.Date;

import model.data_structures.NodoLista;

/**
 * @author Juan Camilo
 *
 */
public class YoutubeVideo implements Comparable<YoutubeVideo>  {

	private String videoID;
	private Date fechaT;
	private String titulo;
	private String canal;
	private Date fechaP;
	private String tags;
	private int id_categoria;
	private int views;
	private int likes;
	private int dislikes;
	private int comentarios;
	private String foto;
	private boolean comentariosHab;
	private boolean ratingsHab;
	private boolean vidErrRem;
	private String descripcion;
	private String pais;
	
	public YoutubeVideo(String ID, Date pFechaT, String pTitulo, String pCanal, int pCategoria, Date pFechaI, String pTags, int pViews, int pLikes, int pDislikes, int pComentarios,
			String pFoto, boolean pComH, boolean pRatH, boolean pVidErr, String pDesc, String pPais) {
	videoID = ID;
	fechaT = pFechaT;
	titulo = pTitulo;
	canal = pCanal;
	id_categoria = pCategoria;
	fechaP = pFechaI;
	tags = pTags;
	views = pViews;
	likes = pLikes;
	dislikes = pDislikes;
	comentarios = pComentarios;
	foto = pFoto;
	comentariosHab = pComH;
	ratingsHab = pRatH;
	vidErrRem = pVidErr;
	descripcion = pDesc;
	pais = pPais;
	}

	/**
	 * @return the videoID
	 */
	public String darVideoID() {
		return videoID;
	}

	/**
	 * @param videoID the videoID to asignar
	 */
	public void asignarVideoID(String videoID) {
		this.videoID = videoID;
	}

	/**
	 * @return the fechaT
	 */
	public Date darFechaT() {
		return fechaT;
	}

	/**
	 * @param fechaT the fechaT to asignar
	 */
	public void asignarFechaT(Date fechaT) {
		this.fechaT = fechaT;
	}

	/**
	 * @return the titulo
	 */
	public String darTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to asignar
	 */
	public void asignarTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the canal
	 */
	public String darCanal() {
		return canal;
	}

	/**
	 * @param canal the canal to asignar
	 */
	public void asignarCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * @return the id_categoria
	 */
	public int darId_categoria() {
		return id_categoria;
	}

	/**
	 * @param id_categoria the id_categoria to asignar
	 */
	public void asignarId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	/**
	 * @return the fechaP
	 */
	public Date darFechaP() {
		return fechaP;
	}

	/**
	 * @param fechaP the fechaP to asignar
	 */
	public void asignarFechaP(Date fechaP) {
		this.fechaP = fechaP;
	}

	/**
	 * @return the tags
	 */
	public String darTags() {
		return tags;
	}

	/**
	 * @param tags the tags to asignar
	 */
	public void asignarTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return the views
	 */
	public int darViews() {
		return views;
	}

	/**
	 * @param views the views to asignar
	 */
	public void asignarViews(int views) {
		this.views = views;
	}

	/**
	 * @return the likes
	 */
	public int darLikes() {
		return likes;
	}

	/**
	 * @param likes the likes to asignar
	 */
	public void asignarLikes(int likes) {
		this.likes = likes;
	}

	/**
	 * @return the dislikes
	 */
	public int darDislikes() {
		return dislikes;
	}

	/**
	 * @param dislikes the dislikes to asignar
	 */
	public void asignarDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	/**
	 * @return the comentarios
	 */
	public int darComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios the comentarios to asignar
	 */
	public void asignarComentarios(int comentarios) {
		this.comentarios = comentarios;
	}

	/**
	 * @return the foto
	 */
	public String darFoto() {
		return foto;
	}

	/**
	 * @param foto the foto to asignar
	 */
	public void asignarFoto(String foto) {
		this.foto = foto;
	}

	/**
	 * @return the comentariosHab
	 */
	public boolean darComentariosHab() {
		return comentariosHab;
	}

	/**
	 * @param comentariosHab the comentariosHab to asignar
	 */
	public void asignarComentariosHab(boolean comentariosHab) {
		this.comentariosHab = comentariosHab;
	}

	/**
	 * @return the ratingsHab
	 */
	public boolean darRatingsHab() {
		return ratingsHab;
	}

	/**
	 * @param ratingsHab the ratingsHab to asignar
	 */
	public void asignarRatingsHab(boolean ratingsHab) {
		this.ratingsHab = ratingsHab;
	}

	/**
	 * @return the vidErrRem
	 */
	public boolean darVidErrRem() {
		return vidErrRem;
	}

	/**
	 * @param vidErrRem the vidErrRem to asignar
	 */
	public void asignarVidErrRem(boolean vidErrRem) {
		this.vidErrRem = vidErrRem;
	}

	/**
	 * @return the descripcion
	 */
	public String darDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to asignar
	 */
	public void asignarDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the pais
	 */
	public String darPais() {
		return pais;
	}

	/**
	 * @param pais the pais to asignar
	 */
	public void asignarPais(String pais) {
		this.pais = pais;
	}

	@Override
	/**
	 * retorna >0 cuando la fecha que entro por parametro es mas reciente
	 * retorna <0 cuando la fecha que entro por parametro es la mas antigua
	 * retorna =0 cuando las fechas son iguales
	 */
	public int compareTo(YoutubeVideo o) {
		//Otras posibles implementaciones:
		//return fechaT.compareTo(o.darFechaT);
		//Utilizando el compareTo implementado en la clase Date()
		//Solo que eso se me hace raro (aunque mucho mas efectivo)
		//otra opciï¿½n seria utilizar los metodos 
		// int retorno (fechaT.after(o.getFechaT))?1:-1;
		//retorno = fechaT.equals(o.getFechaT);
		int i = fechaT.getDay()+fechaT.getMonth()+fechaT.getYear();
		int j = o.fechaT.getDay()+o.fechaT.getMonth()+o.fechaT.getYear();
		return i-j;
	}
	
	/** Comparaciï¿½n natural de acuerdo a algï¿½n atributo con identificaciï¿½n ï¿½nica
	 * @return valor 0 si this y otro son iguales. Numero negativo si this es menor a otro. 
	 * Numero positivo this es mayor a otro */
	public int compareToLikes(YoutubeVideo otro)
	{
		int x = 0;
		if(darLikes() == otro.darLikes()){
			x = 0;
		}
		else if(darLikes() < otro.darLikes()){
			x = -1;
		}
		else if(darLikes() > otro.darLikes()){
			x = 1;
		}
		return x;
	}
	
	public int compareToViews(YoutubeVideo otro)
	{
		int x = 0;
		if(darViews() == otro.darViews()){
			x = 0;
		}
		else if(darViews() < otro.darViews()){
			x = -1;
		}
		else if(darViews() > otro.darViews()){
			x = 1;
		}
		return x;
	}
	
	// Comparador alterno de acuerdo al nï¿½mero de likes
	 public static class ComparadorXLikes implements Comparator<YoutubeVideo> {

	/** Comparador alterno de acuerdo al nï¿½mero de likes
	* @return valor 0 si video1 y video2 tiene los mismos likes.
	*valor negativo si video1 tiene menos likes que video2.
	*valor positivo si video1 tiene mï¿½s likes que video2. 
	*/
		 public int compare(YoutubeVideo video1 , YoutubeVideo video2) {
			 return video1.compareToLikes(video2);
		 }
	}

	// Comparador alterno de acuerdo al número de views
		 public static class ComparadorXViews implements Comparator<YoutubeVideo> {

		/** Comparador alterno de acuerdo al nï¿½mero de views
		* @return valor 0 si video1 y video2 tiene los mismos views.
		*valor negativo si video1 tiene menos views que video2.
		*valor positivo si video1 tiene mï¿½s views que video2. 
		*/
			 public int compare(YoutubeVideo video1 , YoutubeVideo video2) {
				 return video1.compareToViews(video2);
			 }
		}

	 


}
