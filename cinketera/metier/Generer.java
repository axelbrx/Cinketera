package metier;

import java.util.List;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import iut.algo.Decomposeur;

/**
 * Classe static utilisée pour genere la {@link List} des {@link Ile}, {@link Route} et {@link Region}
 * 
 * @author Célia ANTUNES
 * @author Axel BUREAUX
 * @author Antoine CARON
 * @author Tom GOUREAU
 * 
 * @see Ile
 * @see Route
 * @see Region
 * 
 * @since 18.0.2.1 
 */
public class Generer
{
	/**
	 * Méthode statique pour generer toutes les {@link Ile} à partir du ficher {@code donnees/Iles.data}
	 * @return la {@link List} des {@link Ile}
	 */
	public static List<Ile> genererIles()
	{
		List<Ile> lstIles = new ArrayList<Ile>();
		Decomposeur dec;

		String nom;
		String coul;
		int    centreX;
		int    centreY;
		int    imageX;
		int    imageY;

		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "./donnees/Iles.data" ), "UTF8" );

			while ( sc.hasNextLine() )
			{
				dec = new Decomposeur ( sc.nextLine() );

				nom     = dec.getString( 0 );
				coul    = dec.getString( 1 );
				centreX = dec.getInt   ( 2 );
				centreY = dec.getInt   ( 3 );
				imageX  = dec.getInt   ( 4 );
				imageY  = dec.getInt   ( 5 );

				lstIles.add (new Ile(nom, coul, centreX, centreY, imageX, imageY));

			}
			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }
		return lstIles;
	}

	/**
	 * Méthode statique pour generer toutes les {@link Route} à partir du ficher {@code donnees/Routes.data}
	 * @param lstIles la {@link List} des {@link Ile}
	 * @return la {@link List} des {@link Route}
	 */
	public static List<Route> genererRoutes(List<Ile> lstIles)
	{
		List<Route> lstRoutes = new ArrayList<Route>();
		Decomposeur dec;

		Ile ile1 = null;
		Ile ile2 = null;

		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "./donnees/Routes.data" ), "UTF8" );

			while ( sc.hasNextLine() )
			{
				dec = new Decomposeur ( sc.nextLine() );

				for ( Ile ile : lstIles)
				{
					if ( ile.getNom().equals(dec.getString(0)) ) 
					{
						ile1 = ile;
					}

					if ( ile.getNom().equals(dec.getString(1)) ) 
					{
						ile2 = ile;
					}
				}

				lstRoutes.add( new Route( ile1, ile2, dec.getInt(2)));

			}
			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }
		return lstRoutes;
	}

	/**
	 * Méthode statique pour generer toutes les {@link Region} à partir du ficher {@code donnees/Regions.data}
	 * @param lstIles la {@link List} des {@link Ile}
	 * @return la {@link List} des {@link Region}
	 */
	public static List<Region> genererRegions(List<Ile> lstIles)
	{
		List<Region> lstRegions = new ArrayList<Region>();

		String   nom;
		Region   reg;
		String[] tabS;

		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "./donnees/Regions.data" ), "UTF8" );

			while ( sc.hasNextLine() )
			{
				tabS = sc.nextLine().split("\t");

				nom = tabS[0];
				reg = new Region ( nom );

				for ( int cpt = 1; cpt<tabS.length; cpt++)
				{
					for (Ile ile : lstIles)
					{
						if ( ile.getNom().equals(tabS[cpt]) )
							reg.ajouterIle( ile );
					}
				}

				lstRegions.add ( reg );

			}
			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }

		return lstRegions;
	}
}
