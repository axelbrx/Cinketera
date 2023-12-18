package controleur;

import java.awt.Color;
import java.util.List;

import java.util.GregorianCalendar;

import ihm.FrameDonnees;
import ihm.FrameMenu;

import metier.Carte;
import metier.Ile;
import metier.Metier;
import metier.Route;


/**
 * Class utilisée pour faire le lien entre le fonctionnement du jeu et l'affichage du jeu
 * 
 * @author Célia ANTUNES
 * @author Axel BUREAUX
 * @author Antoine CARON
 * @author Tom GOUREAU
 * 
 * @see Metier
 * @see FramePlateau
 * @see Ile
 * @see Route
 * @see Carte
 * @see FrameMenu
 * @see FrameDonnees
 * 
 * @since 18.0.2.1 
 */
public class Controleur
{
	private FrameMenu ihm;
	private FrameDonnees ihmDonnees;
	private Metier metier;

	private GregorianCalendar gcDbt;

	/*----------------------------*/
	/* Constructeur               */
	/*----------------------------*/

	/**
	 * Constructeur du {@code Controleur}
	 */
	public Controleur()
	{
		this.metier     = null; // au lancement du Controleur, il n'y a pas de partie
		this.ihm        = new FrameMenu   (this);
		this.ihmDonnees = null;
		this.gcDbt      = new GregorianCalendar();
	}

	/*----------------------------*/
	/* Getteurs                   */
	/*----------------------------*/

	/**
	 * Getteur de {@link Metier#getLstSave}
	 * @return {@link Metier#getLstSave}
	 */
	public List<String[]> getLstSave() { return this.metier.getLstSave(); }

	/**
	 * Getteur de {@link Metier#getMain}
	 * @return {@link Metier#getMain}
	 */
	public Carte getMain() { return this.metier.getMain(); }

	/**
	 * Getteur de {@link Metier#getNbCarteP}
	 * @return {@link Metier#getNbCarteP}
	 */
	public int getNbCarteP() { return this.metier.getNbCarteP(); }

	/**
	 * Getteur de {@link Metier#getPioche}
	 * @return {@link Metier#getPioche}
	 */
	public List<Carte> getPioche() { return this.metier.getPioche(); }

	/**
	 * Getteur de {@link Metier#getCoulJoueur}
	 * @return {@link Metier#getCoulJoueur}
	 */
	public Color getCoulJoueur() { return this.metier.getCoulJoueur(); }

	/**
	 * Getteur de {@link Metier#getScore}
	 * @return {@link Metier#getScore}
	 */
	public int getScore() { return this.metier.getScore(); }

	/**
	 * Getteur de {@link Metier#getLstIle}
	 * @return {@link Metier#getLstIle}
	 */
	public List<Ile> getLstIle() { return this.metier.getLstIle(); }

	/**
	 * Getteur de {@link Metier#getLstRoute}
	 * @return {@link Metier#getLstRoute}
	 */
	public List<Route> getLstRoute() { return this.metier.getLstRoute(); }

	/**
	 * Getteur de {@link Metier#getIleSelect}
	 * @return {@link Metier#getIleSelect}
	 */
	public Ile getIleSelect() { return this.metier.getIleSelect(); }

	/**
	 * Getteur de {@link FrameMenu#getNbCrtPass()}
	 * @return {@link FrameMenu#getNbCrtPass()}
	 */
	public int getNbCrtPass() { return this.ihm.getNbCrtPass(); }

 
	/*----------------------------*/
	/* Setteurs                   */
	/*----------------------------*/


	/*----------------------------*/
	/* Autre méthodes             */
	/*----------------------------*/

	/**
	 * Méthode pour lancer le jeu
	 */
	public static void main(String[] args) 
	{
		new Controleur();
	}

	/**
	 * Méthode pour lancer un scénario
	 * @param num du scénario
	 */
	public void lancerScenario(int num)
	{
		this.metier = new Metier(this);
		this.ihmDonnees = new FrameDonnees(this);
		
		this.metier.lancerScenario(num);
	}

	/**
	 * Méthode pour lancer une partie
	 */
	public void lancerPartie()
	{
		this.metier = new Metier(this);
		this.ihmDonnees = new FrameDonnees(this);
		this.metier.lancerPartie();
	}

	/**
	 * Méthode pour selectionner une {@link Ile}, utilisant {@link Metier#selectionner(ile)}
	 * @param ile à selectionner
	 */
	public void selectionner(Ile ile) { this.metier.selectionner(ile); }

	/**
	 * Méthode pour tirer une {@link Carte}, utilisant {@link Metier#tirerCarte()}
	 */
	public void tirerCarte() { this.metier.tirerCarte(); }

	/**
	 * Méthode pour obtenir la défausse {@link Metier#getDefauss()}
	 */
	public List<Carte> getDefauss () { return this.metier.getDefauss(); }

	/**
	 * Méthode pour afficher le score, utilisant {@link FrameMenu#afficherScore(nbRegionsRouge,  maxRouge,  nbRegionsBleu,  maxBleu,  bonusRoute,  bonusIle)}
	 * @param nbRegionsRouge nombre de regions rouge
	 * @param maxRouge  
	 * @param nbRegionsBleu nombre de regions bleu
	 * @param maxBleu 
	 * @param bonusRoute 
	 * @param bonusIle 
	 */
	public void afficherScore(int nbRegionsRouge, int maxRouge, int nbRegionsBleu, int maxBleu, int bonusRoute, int bonusIle)
	{
		this.ihm.afficherScore( nbRegionsRouge,  maxRouge,  nbRegionsBleu,  maxBleu,  bonusRoute,  bonusIle);
	}

	/**
	 * Méthode pour afficher la bifurcation, utilisant {@link FrameMenu#afficherBifurcation()}
	 */
	public void afficherBifurcation() { this.ihm.afficherBifurcation(); }

	/**
	 * Méthode pour finir la partie, utilisant {@link FrameMenu#terminerPartie()}
	 */
	public void terminerPartie() { this.ihm.terminerPartie(); }

	/**
	 * Méthode pour mettre à jour la liste des Clients, utilisant {@link FrameDonnees#majListeClients()}
	 */
	public void majListeClients () { this.ihmDonnees.majListeClients(); }


	/**
	 * Méthode pour mettre a jour le jeu, utilisant FrameDonnees
	 */
	public void majIHM () { this.ihm.majIHM();}

	/**
	 * Méthode pour mettre a jour le jeu, utilisant {@link Metier#estColoriable(aColorier)}
	 * @param route a colorier
	 * @return true si l'{@link Ile} est coloriable, false sinon
	 */
	public boolean estColoriable(Route route)
	{
		return this.metier.estColoriable(route);
	}


	/**
	 * Getteur de {@code gcDbt}
	 * @return {@code gcDbt}
	 */
	public GregorianCalendar getGregorianCalendar () { return this.gcDbt;}

	/*----------------------------*/
	/* Méthodes d'affichage       */
	/*----------------------------*/
}


