package metier;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;

/**
 * Class utilisée pour représenter un joueur du jeu
 * 
 * @author Célia ANTUNES
 * @author Axel BUREAUX
 * @author Antoine CARON
 * @author Tom GOUREAU
 * 
 * @see Carte
 * @see Route
 * @see Ile
 * @see Metier
 * @see Color
 * 
 * @since 18.0.2.1 
 */
public class Joueur
{
	private final static Color[] TAB_COUL = {Color.RED, Color.BLUE};
	
	private static Carte[] TAB_CARTE_DEPART = {
		new Carte(true , "Joker"), new Carte(true , "Vert"), new Carte(true , "Violet"), new Carte(true , "Jaune"), new Carte(true , "Brun"),
		new Carte(false, "Joker"), new Carte(false, "Vert"), new Carte(false, "Violet"), new Carte(false, "Jaune"), new Carte(false, "Brun")};
	
	private Metier metier;

	private Carte       main;
	private List<Carte> pioche;
	private List<Carte> defauss;

	private Color       couleur;

	private int         nbCartePrimaire;
	private int         score;

	private List<Route> lstRoutesPossedees;
	private List<Ile>   lstIlesPossedees  ;

	/*----------------------------*/
	/* Constructeur               */
	/*----------------------------*/

	/**
	 * Constructeur de {@code Joueur}
	 * @param metier de cinketera
	 */
	public Joueur(Metier metier)
	{
		this.metier = metier;

		this.defauss = new ArrayList<Carte>();
		this.pioche  = new ArrayList<Carte>();

		this.lstRoutesPossedees = new ArrayList<Route>();
		this.lstIlesPossedees   = new ArrayList<Ile>();

		this.reinitialiserPioche();

		this.couleur = TAB_COUL[(int) (Math.random()*2)];

		this.nbCartePrimaire = 0;

		this.main = null;
		this.score = 0;

	}

	/*----------------------------*/
	/* Getteurs                   */
	/*----------------------------*/

	/**
	 * Getteur de {@code main}
	 * @return {@code main}, {@link Carte} sur le point d'etre utilisée par la {@code Joueur}
	 */
	public Carte getMain() { return this.main; }

	/**
	 * Getteur de {@code couleur}
	 * @return {@code couleur}, soit {@code Color.RED} ou {@code Color.BLUE}
	 */
	public Color getCouleur() { return this.couleur; }

	/**
	 * Getteur de {@code nbCartePrimaire}.
	 * Permet définir le changement de {@code couleur} et la fin de partie
	 * @return {@code nbCartePrimaire}, nombre de {@link Carte} dont {@link Carte#estPrimaire} est true
	 */
	public int getNbCarteP() { return this.nbCartePrimaire; }

	/**
	 * Getteur de {@code pioche}
	 * @return {@code pioche}, {@code List} des {@link Carte} tirables
	 */
	public List<Carte> getPioche() { return this.pioche; }

	/**
	 * Getteur de {@code defauss}
	 * @return {@code defauss}, {@code List} des {@link Carte} déjà tirées
	 */
	public List<Carte> getDefauss() { return this.defauss; }

	/**
	 * Getteur de {@code lstRoutesPossedees}
	 * @return {@code lstRoutesPossedees}, {@link List} des {@link Route} possedées par le {@link Joueur}
	 */
	public List<Route> getLstRoutesPossedees() { return this.lstRoutesPossedees; }

	/**
	 * Getteur de {@code score}
	 * @return {@code score}, nombre de points gagnés par le {@code Joueur}
	 */
	public int getScore() { return this.score; }

	/*----------------------------*/
	/* Setteurs                   */
	/*----------------------------*/

	/**
	 * Setteur de {@code nbCartePrimaire}
	 * @param nb nouveau {@code nbCartePrimaire}
	 */
	public void setNbCartesPrimaires(int nb) { this.nbCartePrimaire = nb; }

	/**
	 * Setteur de {@code couleur}
	 * @param couleur nouvelle {@code couleur} en fonction de de si le paramètre est égal à "ROUGE" ou "BLEU"
	 */
	public void setCouleur(String couleur)
	{
		if (couleur.equals("ROUGE"))
			this.couleur = Color.RED;

		if (couleur.equals("BLEU"))
			this.couleur = Color.BLUE;
	}

	/**
	 * Setteur de la {@code main}
	 * @param main nouvelle {@code main}
	 */
	public void setMain(Carte main) { this.main = main; }

	/*----------------------------*/
	/* Autre methodes             */
	/*----------------------------*/

	/**
	 * Méthode pour réinitialiser la {@code pioche}.
	 * Vide {@code pioche} et y remet toutes les {@link Carte} de {@code TAB_CARTE_DEPART}
	 */
	public void reinitialiserPioche()
	{
		this.pioche .clear();
		this.defauss.clear();
		for (Carte carte : Joueur.TAB_CARTE_DEPART)
		{
			this.pioche.add( carte );
		}
	}

	/**
	 * Méthode pour vider {@code pioche}
	 */
	public void viderPioche() { this.pioche.clear(); }

	/**
	 * Méthode pour ajouter une {@link Carte} dans {@code pioche}
	 * @param carte à ajouter à {@code pioche}
	 */
	public void ajouterPioche( Carte carte ) { this.pioche.add( carte ); }

	/**
	 * Méthode pour ajouter une {@link Carte} dans {@code defauss}. 
	 * Si la {@link Carte} à {@code estPrimaire} à true, {@code nbCartePrimaire} augmente de 1.
	 * Si {@code nbCartePrimaire} est plus grand ou égale à 10, la fin du jeu est activée dans le {@link Metier} {@code metier}
	 * @param carte à ajouter à {@code defauss}
	 */
	public void ajouterDefauss( Carte carte )
	{
		this.defauss.add( carte );
		if ( carte.getEstPrimaire() )
				this.nbCartePrimaire++;
		
		if (this.nbCartePrimaire >= 10)
		{
			this.calculScore();
			this.metier.terminerPartie();
		}
	}

	/**
	 * Méthode pour tirer une {@link Carte} de {@code pioche} et le mettre dans {@code main}.
	 * La {@link Carte} {@code main} passe dans {@code defauss}
	 */
	public void tirerCarte ()
	{
		if (this.main != null && this.nbCartePrimaire < 10)
				this.ajouterDefauss(this.main);

		if (this.nbCartePrimaire < 10)
		{
			Carte cRet;

			if (this.nbCartePrimaire == 5 && this.main.getEstPrimaire())
			{
				this.changerCouleur();
				if ( !metier.getEstScenario() )
					this.reinitialiserPioche();
			}

			if ( !metier.getEstScenario() )
				cRet = this.pioche.get((int) (Math.random()*this.pioche.size()));
			else
				cRet = this.pioche.get(0);
			
			this.pioche.remove(cRet);

			this.main = cRet;
		}

		if ( this.metier.getBifurcation() == this.defauss.size() )
		{
			this.metier.afficherBifurcation();
		}

		
	}

	/**
	 * Méthode pour passer {@code couleur} {@code Color.RED} à {@codeColor.BLUE} et inverssement
	 */
	public void changerCouleur()
	{
		if (this.couleur.equals(TAB_COUL[1])) { this.couleur = TAB_COUL[0]; }
		else                                  { this.couleur = TAB_COUL[1]; }

		System.out.println("set ile début");
		this.metier.setIleDbt();

		this.metier.getControleur().majIHM();
	}

	/**
	 * Méthode pour savoir si une {@code Route} est possedée par le joueur
	 * @param route contenue ou non dans {@code lstRoutesPossedees}
	 * @return true si la {@code Route} est dans {@code lstRoutesPossedees}, false sinon
	 */
	public boolean possedeRoute (Route route) { return this.lstRoutesPossedees.contains(route); }

	/**
	 * Méthode pour ajouter une {@code Route} dans {@code lstRoutesPossedees}
	 */
	public void ajouterRoute (Route route) { this.lstRoutesPossedees.add(route); }

	/**
	 * Méthode pour calculer {@code score} en fin de partie
	 * @return le score sous forme | bonusIle | bonusRoute | nbRegionsBleu*maxBleu | nbRegionsRouge*maxRouge | score |
	 */
	public void calculScore()
	{
		int score = 0;
		int nbRegionsBleu  = 0;
		int nbRegionsRouge = 0;

		int maxIles1   = 0;
		int maxIles2   = 0;

		int maxRouge   = 0;
		int maxBleu    = 0;

		int bonusRoute = 0;
		int bonusIle   = 0;

		List<Region> lstRegionsR, lstRegionsB;

		lstRegionsR = new ArrayList<Region>();
		lstRegionsB = new ArrayList<Region>();


		for (Route route : this.lstRoutesPossedees)
		{
			Ile ile1 = route.getIle1();
			Ile ile2 = route.getIle2();

			for ( Route route1 : ile1.getLstRoutes() )
			{
				if ( route1.getCouleur() == Color.RED )
				{
					if ( !lstRegionsR.contains( ile1.getRegion() ) )
					{
						lstRegionsR.add( ile1.getRegion() );
					}
					
				}

				if (route1.getCouleur() == Color.BLUE)
				{
					if ( !lstRegionsB.contains( ile1.getRegion() ) )
					{
						lstRegionsB.add( ile1.getRegion() );
					}
				}
			}

			for ( Route route2 : ile2.getLstRoutes() )
			{
				if ( route2.getCouleur() == Color.RED )
				{
					if ( !lstRegionsR.contains( ile2.getRegion() ) )
						lstRegionsR.add( ile2.getRegion() );
					
				}
				
				if ( route2.getCouleur() == Color.BLUE )
				{
					if ( !lstRegionsB.contains( ile2.getRegion() ) )
						lstRegionsB.add( ile2.getRegion() );	
				}
			}
				
		}

		nbRegionsBleu  = lstRegionsB.size();
		nbRegionsRouge = lstRegionsR.size();

		// Ajout des iles dans les régions, par couleur //

		// Réinitialisation
		for (Region region : lstRegionsR)
		{
			region.setNbIlesBleu(0) ;
			region.setNbIlesRouge(0);
		}
		for (Region region : lstRegionsB)
		{
			region.setNbIlesBleu(0) ;
			region.setNbIlesRouge(0);
		}

		// Ajout pour les rouges
		for ( Region region : lstRegionsR )
		{
			for ( Ile ile : region.getLstIle() )
			{
				for ( Route route : ile.getLstRoutes() )
				{
					if ( route.getCouleur() == Color.RED)
					{ 
						region.ajouterNbIlesRouge();
						break;
					}
				}
			}
		}

		// Ajout pour les bleus
		for ( Region region : lstRegionsB )
		{
			for ( Ile ile : region.getLstIle() )
			{
				for ( Route route : ile.getLstRoutes() )
				{
					if ( route.getCouleur() == Color.BLUE)
					{ 
						region.ajouterNbIlesBleu();
						break;
					}
				}
			}
		}

		// On récupère le nb de noeuds possedé par la 1ere région de la liste (après avoir récupéré ce nombre)
		for ( Region region : lstRegionsR )
		{
			
			int nbNoeuds = region.getNbIlesRouge();
			if ( nbNoeuds > maxIles1 )
			{
				maxIles1 = nbNoeuds;
			}

		}
		maxRouge = maxIles1;


		// On récupère le nb de noeuds possedé par la 1ere région de la liste (après avoir récupéré ce nombre)
		for ( Region region : lstRegionsB )
		{

			int nbNoeuds = region.getNbIlesBleu();
			if ( nbNoeuds > maxIles2 )
				maxIles2 = nbNoeuds;

		}
		maxBleu = maxIles2;

		// Ajout du bonus de passage de deux couleurs sur une ile
		for (Route route : lstRoutesPossedees)
		{
			Ile ile1 = route.getIle1();
			Ile ile2 = route.getIle2();

			if ( !lstIlesPossedees.contains(ile1) ) { lstIlesPossedees.add(ile1); }
			if ( !lstIlesPossedees.contains(ile2) ) { lstIlesPossedees.add(ile2); }
		}

		for (Ile ile : lstIlesPossedees)
		{
			if ( ile.getNbCoul() == 2 ) { bonusIle += 2; }
		}


		// Ajout du BONUS
		for (Route route : lstRoutesPossedees)
		{
			bonusRoute += route.getBonus();
		}

		score = nbRegionsRouge*maxRouge + nbRegionsBleu*maxBleu + bonusRoute + bonusIle;

		this.score = score;

		this.metier.afficherScore( nbRegionsRouge,  maxRouge,  nbRegionsBleu,  maxBleu,  bonusRoute,  bonusIle);
	}

	/*----------------------------*/
	/* Methodes d'affichage       */
	/*----------------------------*/
}