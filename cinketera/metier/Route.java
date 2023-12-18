package metier;

import java.awt.Color;

/**
 * Class utilisée pour représenter les routes du jeu
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
public class Route
{
	private static int nbrRoutes = 0;

	private int   num;

	private Ile   ile1;
	private Ile   ile2;
	private Color coul;
	private int   bonus;

	/*----------------------------*/
	/* Constructeur               */
	/*----------------------------*/

	/**
	 * Construceur de {@code Route}
	 * @param ile1 formant un des boute de la {@code Route}
	 * @param ile2 formant un des boute de la {@code Route}
	 * @param bonus reçus en passant par la {@code Route}, 0 ou 1
	 */
	public Route(Ile ile1, Ile ile2, int bonus)
	{
		this.num   = Route.nbrRoutes++;
		this.ile1  = ile1;
		this.ile2  = ile2;
		this.coul  = null;
		this.bonus = bonus;

		this.ile1.ajouterRoute(this);
		this.ile2.ajouterRoute(this);
	}

	/*----------------------------*/
	/* Getteurs                   */
	/*----------------------------*/

	/**
	 * Getteur de {@code Ile1}
	 * @return {@code Ile1}, formant un des boute de la {@code Route}
	 */
	public Ile getIle1() { return this.ile1; }

	/**
	 * Getteur de {@code Ile2}
	 * @return {@code Ile2}, formant un des boute de la {@code Route}
	 */
	public Ile getIle2() { return this.ile2; }

	/**
	 * Getteur de {@code couleur}
	 * @return {@code couleur}, {@link Color} de la {@code Route}
	 */
	public Color getCouleur() { return this.coul; }

	/**
	 * Getteur de {@code bonus}
	 * @return {@code bonus} qu'un {@link Joueur} obtient en passant par la {@code Route}
	 */
	public int getBonus() { return this.bonus; }

	/**
	 * Getteur de {@code num}
	 * @return {@code num}, numéro d'identification
	 */
	public int getNum() { return this.num; }

	/**
	 * Méthode pour obtenir l'{@link Ile} de l'autre coté de la {@code Route}
	 * @param ile de départ
	 * @return l'{@link Ile} en face si la {@code Route} possede l'{@link Ile} de départ est sur la {@code Route}
	 */
	public Ile getAutre(Ile ile) 
	{
		if (ile == this.ile1)
			return this.ile2;
		
		if (ile == this.ile2)
			return this.ile1;

		return null;
	}
	
	/*----------------------------*/
	/* Setteurs                   */
	/*----------------------------*/

	/**
	 * Setteur de {@code couleur}
	 * @param coul nouvelle {@code couleur}
	 */
	public void setCouleur(Color coul ) { this.coul = coul; }

	/*----------------------------*/
	/* Autre méthodes             */
	/*----------------------------*/

	/**
	 * Méthode pour savoir si 2 {@code Route} ont une {@link Ile} commune
	 * @param autreRoute 
	 * @return true si les 2 {@code Route} ont une {@link Ile} commune, false sinon
	 */
	public boolean aIleCommune( Route autreRoute )
	{
		return this.ile1 == autreRoute.ile1 ||
		       this.ile1 == autreRoute.ile2 ||
		       this.ile2 == autreRoute.ile2 ||
		       this.ile2 == autreRoute.ile1    ;
	}

	/**
	 * Méthode pour savoir si une {@code Route} possede une {@link Ile} en fonction de son {@code nom}
	 * @param nomIle , possible {@code nom} d'une {@link Ile} de {@code this}
	 * @return true si l'{@link Ile} est trouvée, false sinon
	 */
	public boolean possedeIle( String nomIle )
	{
		return nomIle.equals( this.ile1.getNom() ) || nomIle.equals( this.ile2.getNom() );
	}

	/*----------------------------*/
	/* Méthodes d'affichage       */
	/*----------------------------*/

	/**
	 * Méthode d'affichage principale
	 * @return le {@code nom} des 2 {@link Ile}
	 */
	public String toString()
	{
		return this.ile1.getNom() + '\t' + this.ile2.getNom();
	}

	/**
	 * Méthode d'affichage
	 * @return {@code nom}, le {@link Ile#nom} des 2 {@link Ile} et {@code coul}
	 */
	public String toStringNum()
	{
		return this.num + '\t' + this.toString() + this.coul;
	}
}