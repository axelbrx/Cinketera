package metier;

import java.util.List;
import java.util.ArrayList;


/**
 * Class utilisée pour représenter les regions du jeu
 * 
 * @author Célia ANTUNES
 * @author Axel BUREAUX
 * @author Antoine CARON
 * @author Tom GOUREAU
 * 
 * @see Ile
 * 
 * @since 18.0.2.1 
 */
public class Region
{
	private String    nom;

	private List<Ile> lstIle;

	private int       nbIlesRouge;
	private int       nbIlesBleu ;

	/*----------------------------*/
	/* Constructeur               */
	/*----------------------------*/

	/**
	 * Constructeur de la {@code Region}
	 * @param nom de la {@code Region}
	 */
	public Region(String nom)
	{
		this.nom    = nom;
		this.lstIle = new ArrayList<Ile>();

		this.nbIlesRouge = 0;
		this.nbIlesBleu  = 0;
	}

	/*----------------------------*/
	/* Getteurs                   */
	/*----------------------------*/

	/**
	 * Getteur de {@code nom}
	 * @return {@code nom}
	 */
	public String getNom() { return this.nom; }

	/**
	 * Getteur de {@code lstIle}
	 * @return {@code lstIle}, {@code List} des {@link Ile}
	 */
	public List<Ile> getLstIle() { return this.lstIle; }

	/**
	 * Getteur de {@code nbIlesRouge}
	 * @return {@code nbIlesRouge}, {@link List} des {@link Ile}, qui ont leur {@code couleur} égale à "rouge"
	 */
	public int getNbIlesRouge() { return this.nbIlesRouge; }

	/**
	 * Getteur de {@code nbIlesRouge}
	 * @return {@code nbIlesRouge}, {@link List} des {@link Ile}, qui ont leur {@code couleur} égale à "bleu"
	 */
	public int getNbIlesBleu() { return this.nbIlesBleu; }

	/*----------------------------*/
	/* Setteurs                   */
	/*----------------------------*/

	/**
	 * Setteur de {@code nbIlesRouge}
	 * @param nbIlesRouge nouvel {@code nbIlesRouge}
	 */
	public void setNbIlesRouge(int nbIlesRouge) { this.nbIlesRouge = nbIlesRouge; }

	/**
	 * Setteur de {@code nbIlesBleu}
	 * @param nbIlesBleu nouvel {@code nbIlesBleu}
	 */
	public void setNbIlesBleu(int nbIlesBleu) { this.nbIlesBleu = nbIlesBleu; }

	/*----------------------------*/
	/* Autre méthodes             */
	/*----------------------------*/

	/**
	 * Méthode pour ajouter des {@link Ile} à la {@link List} des {@link Ile} {@code lstIle}
	 * @param ile ajoutée à {@code lstIle} et qui met sa {@code region} à {@code this}
	 */
	public void ajouterIle (Ile ile)
	{
		this.lstIle.add(ile);
		ile.setRegion(this);
	}

	/**
	 * Méthode qui ajoute 1 à {@code nbIlesRouge}
	 */
	public void ajouterNbIlesRouge() { this.nbIlesRouge++; }

	/**
	 * Méthode qui ajoute 1 à {@code nbIlesRouge}
	 */
	public void ajouterNbIlesBleu() { this.nbIlesBleu ++; }

	/*----------------------------*/
	/* Méthodes d'affichage       */
	/*----------------------------*/

	/**
	 * Méthode d'affichage de la {@code Region}
	 * @return {@code nom} suivi de {@link Ile#nom} de toutes les {@link Ile} de {@code lstIle}
	 */
	public String toString()
	{
		String sRet = this.nom;

		for ( Ile ile : this.lstIle )
		{
			sRet += ile.getNom();
		}
		return sRet;
	}
}