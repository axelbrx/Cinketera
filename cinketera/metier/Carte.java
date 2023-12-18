package metier;

/**
 * Class utilisée pour représenter une carte de jeu
 * 
 * @author Célia ANTUNES
 * @author Axel BUREAUX
 * @author Antoine CARON
 * @author Tom GOUREAU
 * 
 * @since 18.0.2.1 
 */
public class Carte
{
	private boolean estPrimaire;
	private String  couleur;

	/*----------------------------*/
	/* Constructeur               */
	/*----------------------------*/

	/**
	 * Constructeur de {@code Carte}
	 * @param estPrimaire true si la {@code Carte} est primaire, false sinon
	 * @param couleur égale a "joker", "vert", "violet", "jaune" ou "brun"
	 */
	public Carte(boolean estPrimaire, String couleur)
	{
		this.estPrimaire = estPrimaire;
		this.couleur     = couleur; // possibilité d'utiliser un enum
	}

	/*----------------------------*/
	/* Getteurs                   */
	/*----------------------------*/

	/**
	 * Getteur de {@code estPrimaire}
	 * @return true si la carte est primaire, false sinon
	 */
	public boolean getEstPrimaire() { return estPrimaire; }

	/**
	 * Getteur de {@code couleur}
	 * @return "joker" ou "vert" ou "violet" ou "jaune" ou "brun"
	 */
	public String getCouleur() { return couleur; }

	/*----------------------------*/
	/* Setteurs                   */
	/*----------------------------*/

	/**
	 * Setteur de {@code estPrimaire}
	 * @param estPrimaire nouvel {@code estPrimaire}
	 */
	public void setPrimaire(boolean estPrimaire) { this.estPrimaire = estPrimaire; }

	/**
	 * Setteur de {@code  couleur}
	 * @param couleur nouvel {@code couleur}
	 */
	public void setCouleur(String couleur) { this.couleur = couleur; }

	/*----------------------------*/
	/* Autre méthodes             */
	/*----------------------------*/
	
	/*----------------------------*/
	/* Méthodes d'affichage       */
	/*----------------------------*/

	/**
	 * Méthode d'affichage principale de la {@code Carte}
	 * @return la {@code Carte} la forme "Primaire : " + {@code estPrimaire} + " | " + {@code couleur}
	 */
	public String toString()
	{
		String sRet;

		if (this.estPrimaire) { sRet = "Carte primaire ";   }
		else                  { sRet = "Carte secondaire "; }

		sRet += this.couleur;

		return sRet;
	}
}