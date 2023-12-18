package metier;

import controleur.*;

import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.geom.Line2D;


import iut.algo.Decomposeur;

/**
 * Class du jeu
 * 
 * @author Célia ANTUNES
 * @author Axel BUREAUX
 * @author Antoine CARON
 * @author Tom GOUREAU
 * 
 * @see Joueur
 * @see Route
 * @see Ile
 * @see Region
 * @see Controleur
 * 
 * @since 18.0.2.1 
 */
public class Metier
{
	private Controleur     ctrl;
	private Joueur         joueur;

	private List<Ile>      lstIle;
	private List<Route>    lstRoutes;
	private List<Region>   lstRegions;
	private Ile            ileSelect;
	private Ile            ileArrive;
	private Ile            ileDepart;
	private Ile            ileBifurc;

	private boolean        estScenario;

	private int            bifurcation;

	private List<String[]> lstSave;

	/*----------------------------*/
	/* Constructeur               */
	/*----------------------------*/

	/**
	 * Constructeur de {@code Metier}
	 * @param ctrl {@link Controleur} du jeu
	 */
	public Metier(Controleur ctrl)
	{
		this.ctrl        = ctrl;

		this.estScenario = false;

		this.lstIle      = new ArrayList<Ile>();
		this.lstRoutes   = new ArrayList<Route>();
		this.lstRegions  = new ArrayList<Region>();

		this.lstSave     = new ArrayList<String[]>();

		this.lstIle     = Generer.genererIles   ();
		this.lstRoutes  = Generer.genererRoutes (this.lstIle);
		this.lstRegions = Generer.genererRegions(this.lstIle);

		this.ileDepart   = this.ileArrive = this.ileSelect = this.ileBifurc = null;
		this.bifurcation = -1;

		this.joueur      = new Joueur(this);

	}

	/*----------------------------*/
	/* Getteurs                   */
	/*----------------------------*/

	/**
	 * Getteur de {@code ctrl}
	 * @return {@code ctrl}
	 */
	public Controleur getControleur () { return this.ctrl;}

	/**
	 * Getteur de {@code lstIle}
	 * @return {@code lstIle}
	 */
	public List<Ile> getLstIle() { return this.lstIle; }

	/**
	 * Getteur de {@code lstRoutes}
	 * @return {@code lstRoutes}
	 */
	public List<Route> getLstRoute() { return this.lstRoutes; }

	/**
	 * Getteur de {@code bifurcation}
	 * @return {@code bifurcation}
	 */
	public int getBifurcation() { return this.bifurcation; }

	/**
	 * Getteur de {@code estScenario}
	 * @return {@code estScenario}
	 */
	public boolean getEstScenario() { return this.estScenario; }

	/**
	 * Getteur de {@code lstSave}
	 * @return {@code lstSave}
	 */
	public List<String[]> getLstSave() { return this.lstSave; }

	/**
	 * Getteur de {@code ileSelect}
	 * @return {@code ileSelect}
	 */
	public Ile getIleSelect() { return this.ileSelect; }

	/**
	 * Getteur de {@code joueur}
	 * @return {@code joueur}
	 */
	public Joueur getJoueur() { return this.joueur; }

	/**
	 * Getteur de {@link Joueur#getCouleur()}
	 * @return {@link Joueur#getCouleur()}
	 */
	public Color getCoulJoueur() { return this.joueur.getCouleur(); }

	/**
	 * Getteur de {@link Joueur#getScore()}
	 * @return {@link Joueur#getScore()}
	 */
	public int getScore() { return this.joueur.getScore(); }

	/**
	 * Getteur de {@link Joueur#getNbCarteP()}
	 * @return {@link Joueur#getNbCarteP()}
	 */
	public int getNbCarteP() { return this.joueur.getNbCarteP(); }

	/**
	 * Getteur de {@link Joueur#getMain()}
	 * @return {@link Joueur#getMain()}
	 */
	public Carte getMain() { return this.joueur.getMain();}

	/**
	 * Getteur de {@link Joueur#getPioche()}
	 * @return {@link Joueur#getPioche()}
	 */
	public List<Carte> getPioche() { return this.joueur.getPioche(); }

	/**
	 * Getteur de {@link Joueur#getDefauss()}
	 * @return {@link Joueur#getDefauss()}
	 */
	public List<Carte> getDefauss() { return this.joueur.getDefauss(); }

	/*----------------------------*/
	/* Setteurs                   */
	/*----------------------------*/

	/**
	 * Setteur de {@code ileDepart}, {@code ileArrive}, {@code ileSelect} à partir de {@link Joueur#couleur}
	 */
	public void setIleDbt ()
	{

		if (this.joueur.getCouleur() == Color.RED)
		{
			for (Ile i : lstIle)
			{
				if (i.getNom().equals("Ticó"))
				{
					this.ileDepart = this.ileArrive = this.ileSelect = null;
					this.ileDepart = this.ileArrive = this.ileSelect = i;
					System.out.println("tico   " + this.ileSelect.getNom() + " " + this.ileDepart.getNom() + " ---> " + this.ileArrive.getNom());
				}
			}
		}
		else
		{
			for (Ile i : lstIle)
			{
				if (i.getNom().equals("Mutaa"))
				{
					this.ileDepart = this.ileArrive = this.ileSelect = null;
					this.ileDepart = this.ileArrive = this.ileSelect = i;
					System.out.println("mutaa   " + this.ileSelect.getNom() + " " + this.ileDepart.getNom() + " ---> " + this.ileArrive.getNom());
				}
			}
		}

		this.ileBifurc = null;
		this.bifurcation = (int) (Math.random()*10);

		
	}

	/*----------------------------*/
	/* Autre methodes             */
	/*----------------------------*/

	/**
	 * Méthode pour lancer la partie
	 */
	public void lancerPartie()
	{
		this.joueur.tirerCarte();
		this.joueur.setNbCartesPrimaires(0);
		this.setIleDbt();
	}

	/**
	 * Méthode pour demander au {@link Joueur} du tirer une {@link Carte} 
	 * de {@link Joueur#pioche} et le mettre dans {@link Joueur#main}
	 */
	public void tirerCarte() 
	{ 
		this.joueur.tirerCarte(); 

		this.ajouterActions("Tire la carte : " + this.joueur.getMain());

		if (this.getDefauss().size() == this.bifurcation)
			this.ctrl.afficherBifurcation();
	}

	/**
	 * Méthode pour ajouter une action la {@link List} de sauvegarde {@code lstSave}
	 * @param action qui vient d'etre effectuée
	 */
	public void ajouterActions(String action)
	{
		this.lstSave.add(new String[3]);
		if (this.joueur.getNbCarteP() <= 5)
		{
			this.lstSave.get(this.lstSave.size()-1)[0] = "0";
		}
		else 
		{
			this.lstSave.get(this.lstSave.size()-1)[0] = "1";	
		}

		this.lstSave.get(this.lstSave.size()-1)[1] = "" + this.joueur.getDefauss().size();

		this.lstSave.get(this.lstSave.size()-1)[2] = action;

		this.ctrl.majListeClients();
	}

	/**
	 * Méthode pour selectionner une {@link Ile} ou la déselectionnée si elle est {@code ileSelect}.
	 * Quand 2 Iles sont sélectionnées, on colorie la {@link Route} qu'il y a entre les deux.
	 * Quand une {@code Route} est coloriée, on change tire une nouvelle {@link Carte}.
	 * @param ile a selectionner
	 */
	public void selectionner(Ile ile)
	{

		if ( (ile != null && ile == this.ileSelect) || ile == this.ileArrive   || ile == this.ileDepart || ile == this.ileBifurc || 
		     (this.ileBifurc == null && this.bifurcation == this.joueur.getDefauss().size() && ile.getNbRouteCoul(joueur.getCouleur()) >= 1))
		{
			if (ile == this.ileSelect)
			{
				this.ileSelect = null;
			}
			else 
			{
				if (ile == this.ileArrive) { this.ileSelect =  this.ileArrive; }
				if (ile == this.ileDepart) { this.ileSelect =  this.ileDepart; }
				if (ile == this.ileBifurc) { this.ileSelect =  this.ileBifurc; }

				if (this.ileBifurc == null && this.bifurcation == this.joueur.getDefauss().size() && ile.getNbRouteCoul(joueur.getCouleur()) >= 1)
				{
					this.ileBifurc = ile;
					this.ajouterActions("selection de l'ile ou la bifurcation a lieu : " + ile.getNom());
				}
				
			}

		}
		else 
		{
			if (ileSelect != null)
			{
				if (ileSelect.getRouteInter(ile) != null)
				{
					Route aColorier = this.ileSelect.getRouteInter(ile);

					if (this.estColoriable(aColorier))
					{					
						aColorier.setCouleur(this.joueur.getCouleur());
						joueur.ajouterRoute(aColorier);

						
						this.joueur.calculScore();

						if (this.ileSelect == this.ileDepart)
						{
							this.ileDepart =  ile;
						}
						else 
						{
							if (this.ileSelect == this.ileArrive)
							{
								this.ileArrive =  ile;
							} 
							else
							{
								if (this.ileSelect == this.ileBifurc)
								{
									this.ileBifurc =  ile;
								}
							}
						}
						
						
						this.ajouterActions("Creation d'un chemin entre " + this.ileSelect + " et " + ile);

						this.ileSelect = ile;

						this.joueur.tirerCarte ();

						this.ctrl.majIHM();
					}
				}
			}
		}
		this.ctrl.majIHM();
	}

	/**
	 * Méthode pour savoir si une {@link Route} ets coloriable.
	 * Pour cela, elle ne doit pas etre
	 * <ul>
	 * 	<li> deja coloriée </li>
	 * 	<li> deja coloriée </li>
	 * </ul>
	 */
	public boolean estColoriable(Route aColorier)
	{
		boolean estColoriable = true;

		if (aColorier == null)
		{
			return false;
		}

		// qui est deja coloriée
		if (aColorier.getCouleur() != null)
		{
			return false;
		}

		// part de l'ile precedente et cherche une ile de la bonne couleur sinon faux
		
		if ( !this.joueur.getMain().getCouleur().equals("Joker") && !aColorier.getAutre(this.ileSelect).getCouleur().equals(this.joueur.getMain().getCouleur()))
		{
			return false;
		}
		

		// jeu fini
		if (this.joueur.getNbCarteP()>=10)
		{
			return false;
		}
			

		// pas plus de 2 routes de la meme couleur de relier a cette ile
		if (this.joueur.getCouleur() == Color.BLUE)
			if (aColorier.getAutre(this.ileSelect).getNbRouteCoul(Color.BLUE) >= 2)
			{
				estColoriable = false;
			}
		if (this.joueur.getCouleur() == Color.RED)
			if (aColorier.getAutre(this.ileSelect).getNbRouteCoul(Color.RED) >= 2)
			{
				estColoriable = false;
			}
				

		// intersection de 2 points
		for ( Route r : this.joueur.getLstRoutesPossedees())
		{
			if ( intersection(r, aColorier))
			{
				if (!r.aIleCommune(aColorier))
				{
					estColoriable = false;
				}
			}
		}

		//verifie qu'on retourne pas a l'ile de depart
		if (this.joueur.getCouleur() == Color.BLUE && aColorier.getAutre(this.ileSelect).getNom().equals("Mutaa") && this.joueur.getNbCarteP() <= 5)
		{
			return false;
		}
		
		if (this.joueur.getCouleur() == Color.RED  && aColorier.getAutre(this.ileSelect).getNom().equals("Ticó") && this.joueur.getNbCarteP() <= 5)
		{
			return false;
		}

		return estColoriable;
	}

	/**
	 * Méthode pour savoir si 2 routes se coupent
	 * @param route1 de la possible intersection
	 * @param route2 de la possible intersection
	 * @return true si les routes se croient, false sinon
	 */
	public boolean intersection(Route route1, Route route2)
	{
		double ile1X = route1.getIle1().getCentreX();
		double ile1Y = route1.getIle1().getCentreY();
		double ile2X = route1.getIle2().getCentreX();
		double ile2Y = route1.getIle2().getCentreY();
		
		double ile3X = route2.getIle1().getCentreX();
		double ile3Y = route2.getIle1().getCentreY();
		double ile4X = route2.getIle2().getCentreX();
		double ile4Y = route2.getIle2().getCentreY();
		
		return Line2D.linesIntersect( ile1X, ile1Y,
		                              ile2X, ile2Y,
		                              ile3X, ile3Y,
		                              ile4X, ile4Y);
	}

	/**
	 * Méthode pour lancer un scénario
	 * @param num du scénario a lancer
	 */
	public void lancerScenario(int num)
	{
		this.estScenario = true;
		this.joueur.viderPioche();

		this.joueur.setNbCartesPrimaires(0);

		
		Decomposeur dec;

		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "./donnees/scenarios/Scenario" + num + ".data" ), "UTF8" );

			boolean estSurParametre = false;
			boolean estSurIle       = false;
			boolean estSurCarte     = false;
			while ( sc.hasNextLine() )
			{
				dec = new Decomposeur(sc.nextLine());
				
				// detection des Parametres
				if (dec.getString(0).equals( "[PARAMETRE]"))
				{
					estSurParametre = true;
					estSurCarte     = false;
					estSurIle       = false;
					dec = new Decomposeur(sc.nextLine());
				}

				// detection des CARTES
				if (dec.getString(0).equals( "[CARTES]"))
				{
					estSurParametre = false;
					estSurCarte     = true;
					estSurIle       = false;
					dec = new Decomposeur(sc.nextLine());
				}

				// detection des ILES
				if (dec.getString(0).equals( "[ILES]"))
				{	
					estSurParametre = false;
					estSurCarte     = false;
					estSurIle       = true;

					this.joueur.tirerCarte();

					dec = new Decomposeur(sc.nextLine());
				}

				if (estSurParametre)
				{
					this.joueur.setCouleur(dec.getString(0));
					this.setIleDbt();

					dec = new Decomposeur(sc.nextLine());
					this.bifurcation = dec.getInt(0);
					System.out.println("BIFURCATION : " + bifurcation);
				}

				if (estSurCarte)
				{
					this.joueur.ajouterPioche( new Carte(dec.getString(0).equals("primaire"),dec.getString(1)) );
				}

				if (estSurIle)
				{
					for (Ile ile : this.lstIle)
					{
						if (ile.getNom().equals(dec.getString(0)))
						{
							selectionner(ile);
							System.out.println("(" + this.ileSelect.getNom() + " > " + ile.getNom() +  ") " + this.ileDepart.getNom() + " ---> " + this.ileArrive.getNom());
						}
							
					}
					
				}
			}
			sc.close();

			
		}
		catch (Exception e){ e.printStackTrace(); }

		System.out.println("APRES LECTURE : (" + this.ileSelect.getNom() + ") " + this.ileDepart.getNom() + " ---> " + this.ileArrive.getNom());
		this.ctrl.majIHM();
	}

	/**
	 * Méthode pour afficher le score du {@link Joueur} sur {@link PanelJoueur}
	 */
	public void afficherScore(int nbRegionsRouge, int maxRouge, int nbRegionsBleu, int maxBleu, int bonusRoute, int bonusIle)
	{
		this.ctrl.afficherScore( nbRegionsRouge, maxRouge, nbRegionsBleu, maxBleu, bonusRoute, bonusIle);
	}

	/**
	 * Méthode pour afficher graphiquement la bifurcation sur {@link FramePlateau}
	 */
	public void afficherBifurcation()
	{
		this.ctrl.afficherBifurcation();
	}


	/**
	 * Méthode pour mettre fin à partie
	 */
	public void terminerPartie()
	{
		this.ctrl.terminerPartie();
	}


	/*----------------------------*/
	/* Methodes d'affichage       */
	/*----------------------------*/

	/**
	 * Methode d'affichage du metier
	 * @return retourne l'affichage de toutes les Ile, Route, Region
	 */
	public String toString()
	{
		String sRet = "";

		for ( Ile ile : lstIle )
		{
			sRet += ile.toString()    + '\n';
		}

		for ( Route route : lstRoutes )
		{
			sRet += route.toString()  + '\n';
		}

		for ( Region region : lstRegions )
		{
			sRet += region.toString() + '\n';
		}

		return sRet;
	}
	
}
