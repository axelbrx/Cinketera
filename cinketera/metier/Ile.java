package metier;

import java.util.*;
import java.awt.Color;

/**
 * Class utilisée pour représenter les iles du jeu
 * 
 * @author Célia ANTUNES
 * @author Axel BUREAUX
 * @author Antoine CARON
 * @author Tom GOUREAU
 * 
 * @see Route
 * @see Region
 * 
 * @since 18.0.2.1 
 */
public class Ile 
{
	private String nom;
	private int    centreX;
	private int    centreY;
	private int    imageX;
	private int    imageY;
	private String coul;
	private Region region;

	private List<Route> lstRoutes;

	/*----------------------------*/
	/* Constructeur               */
	/*----------------------------*/

	/**
	 * Constructeur d'{@code Ile}
	 * @param nom de l'{@code Ile}
	 * @param coul , couleur de l'{@code Ile}
	 * @param centreX coordonée X du centre de l'{@code Ile}
	 * @param centreY coordonée Y du centre de l'{@code Ile}
	 * @param imageX coordonée X de l'image
	 * @param imageY coordonée Y de l'image
	 */
	public Ile (String nom, String coul, int centreX, int centreY, int imageX, int imageY)
	{
		this.nom       = nom;
		this.centreX   = (int) (centreX/1.25);
		this.centreY   = (int) (centreY/1.25);
		this.imageX    = (int) (imageX/1.25);
		this.imageY    = (int) (imageY/1.25);
		this.coul      = coul;
		this.region    = null;
		this.lstRoutes = new ArrayList<Route>();
	}

	/*----------------------------*/
	/* Getteurs                   */
	/*----------------------------*/

	/**
	 * Getteur de {@code nom}
	 * @return {@code nom} de l'{@code Ile}
	 */
	public String getNom() { return this.nom; }

	/**
	 * Getteur de {@code centreX}
	 * @return {@code centreX} qui défini la coordonée X du centre
	 */
	public int getCentreX() { return this.centreX; }

	/**
	 * Getteur de {@code centreY}
	 * @return {@code centreY} qui défini la coordonée Y du centre
	 */
	public int getCentreY() { return this.centreY; }

	/**
	 * Getteur de {@code imageX}
	 * @return {@code imageX} qui défini la coordonée X de l'image
	 */
	public int getImageX() { return this.imageX; }

	/**
	 * Getteur de {@code imageY}
	 * @return {@code imageY} qui défini la coordonée X de l'image
	 */
	public int getImageY() { return this.imageY; }

	/**
	 * Getteur de {@code couleur}
	 * @return {@code couleur}, couleur de l'{@code Ile}
	 */
	public String getCouleur() { return this.coul; }

	/**
	 * Getteur de {@code region}
	 * @return {@code region}, la {@code Region} de l'{@code Ile}
	 */
	public Region getRegion() { return this.region; }

	/**
	 * Getteur de {@code lstRoutes}
	 * @return la {@code List} des {@code Route} {@code lstRoutes}
	 */
	public List<Route> getLstRoutes() { return this.lstRoutes; }

	/**
	 * Méthode pour obtenir la listes des {@code Ile} d'une couleur {@code coul} au quel this est lié
	 * @param coul couleur des {@code Ile}
	 * @return la liste des {@code Ile} au quel {@code this} est lié et dont la couleur est {@code coul}
	 */
	public List<Ile> getIleMmCouleur (String coul) 
	{
		List<Ile> lstRet = new ArrayList<Ile>();

		for (Route r : this.lstRoutes)
		{
			if (r.getAutre(this) != null && r.getAutre(this).getCouleur().equals(coul))
			{
				lstRet.add(r.getAutre(this));
			}
		}

		return lstRet;
	}

	/**
	 * Méthode pour obtenir la {@code Route} entre 2 {@code Ile}
	 * @param ile lié à {@code this}
	 * @return {@code Route} qui relie {@code this} et {@code ile}
	 */
	public Route getRouteInter (Ile ile)
	{
		if (ile == null)
			return null;
			
		for (Route route : this.lstRoutes)
		{
			if (route.getAutre(this) == ile)
			{
				return route;
			}
				
		}
		return null;
	}

	/**
	 * Méthode pour obtenir le nombre de couleur qui passe par l'{@code Ile}
	 * @return le nombre de couleur
	 */
	public int getNbCoul()
	{
		int nb = 0;
		boolean estRouge, estBleu;

		estRouge = estBleu = false;

		for (Route route : lstRoutes)
		{
			if (route.getCouleur() == Color.RED ) { estRouge = true; }
			if (route.getCouleur() == Color.BLUE) { estBleu  = true; }
		}

		if (estRouge) { nb++; }
		if (estBleu ) { nb++; }

		return nb;
	}

	/**
	 * Méthode pour obtenir le nombre de route lié à {@code this} d'une couleur
	 * @param coul {@code Color} des {@code Route}
	 * @return le nombre de {@code Route} de la {@code Color}
	 */
	public int getNbRouteCoul(Color coul)
	{
		int cpt = 0;

		for (Route r : lstRoutes)
		{
			if (r.getCouleur() == coul)
			{
				cpt++;
			}
		}

		return cpt;
	}

	/*----------------------------*/
	/* Setteurs                   */
	/*----------------------------*/

	/**
	 * Setteur de la {@code Region}
	 * @param region nouvelle {@code Region}
	 */
	public void setRegion(Region region) { this.region = region; }

	/*----------------------------*/
	/* Autre méthodes             */
	/*----------------------------*/

	/**
	 * Méthode pour ajouter une {@code Route}
	 * @param route à ajouter à la {@code List} des {@code Route} {@code lstRoutes}
	 */
	public void ajouterRoute(Route route) { this.lstRoutes.add(route); }

	/*----------------------------*/
	/* Méthodes d'affichage       */
	/*----------------------------*/

	/**
	 * Méthode d'affichage principale de l'{@code Ile}
	 * @return {@code nom} {@code coul} {@code centreX} {@code centreY} {@code imageX} {@code imageY} sous forme de {@link String}
	 */
	public String toString()
	{
		return this.nom;
	}

	/**
	 * Méthode d'affichage ajoutant les {@code Route} au {@code toString()}
	 * @return {@code toString()} suivi du numéro des {@code Route} d  {@code .lstRoutes}
	 */
	public String toStringRoutes()
	{
		String sRet = this.toString();
		for (Route route : this.lstRoutes)
		{
			sRet += "\t" + route.getNum();
		}

		return sRet;
	}
}