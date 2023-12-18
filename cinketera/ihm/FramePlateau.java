package ihm;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.List;

import java.util.GregorianCalendar;

import controleur.*;

import java.awt.BorderLayout;

import javax.swing.*;

/**
 * 
 * @author Célia ANTUNES
 * @author Axel BUREAUX
 * @author Antoine CARON
 * @author Tom GOUREAU
 * 
 * @see Controleur
 * 
 * @since 18.0.2.1 
 */
public class FramePlateau extends JFrame
{
	private Controleur   ctrl;
	private PanelPlateau panelPlateau;
	private PanelJoueur  panelJoueur;

	/**
	 * Constructeur de {@code FramePlateau}
	 * @param ctrl {@code Controleur} 
	 */
	public FramePlateau(Controleur ctrl)
	{

		this.ctrl = ctrl;
		this.panelPlateau = new PanelPlateau(this.ctrl);
		this.panelJoueur  = new PanelJoueur (this.ctrl);

		this.setSize(1500,750);
		this.setLocation( 10,10 );
		this.setTitle("Cinketera");
		//this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(this.panelPlateau, BorderLayout.CENTER);
		this.add(this.panelJoueur , BorderLayout.EAST  );

		this.setVisible(true);
	}

	/**
	 * Méthode pour terminer la partie
	 */
	public void terminerPartie()
	{
		JOptionPane.showMessageDialog(this, "Partie terminée. \nVotre score est de " + this.ctrl.getScore() + " points.", "FIN DE PARTIE", 1);
		
		GregorianCalendar gcFin = new GregorianCalendar();
		GregorianCalendar gcDbt = this.ctrl.getGregorianCalendar();

		List<String> lstFichier = new ArrayList<String>(); 

		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "./donnees/TableauBord.data" ) );

			while ( sc.hasNextLine() )
			{
				lstFichier.add(sc.nextLine().toString());
			}
			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }

		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream ("./donnees/TableauBord.data "));

			for (String s : lstFichier)
			{
				pw.println(s);
			}

			pw.println("-----------------------------------------------------------------------");
			pw.println ("date : "   + gcDbt.get(gcDbt.YEAR) + "/" + (gcDbt.get(gcDbt.MONTH)+1) + "/" + gcDbt.get(gcDbt.DAY_OF_MONTH) + " " + gcDbt.get(gcDbt.HOUR_OF_DAY) + "h" + gcDbt.get( gcDbt.MINUTE ) +
			            " duree : " + (( gcFin.get( gcFin.HOUR ) - gcDbt.get(gcDbt.HOUR) ) * 60 + gcFin.get( gcFin.MINUTE ) - gcDbt.get( gcDbt.MINUTE ) ) +
						" score : " + this.ctrl.getScore() );

			for (String [] sTab : this.ctrl.getLstSave())
			{
				pw.println("manche : " + sTab[0] + " |tour : " + sTab[1] + " |coup : " + sTab[2]);
			}

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

	/**
	 * Méthode pour obtenir le nombre de {@link Carte} passées
	 * @return le nombre de {@link Carte} passées
	 */
	public int getNbCrtPass() {return this.panelJoueur.getNbCrtPass();}



	/**
	 * Méthode pour afficher le score du {@code Joueur}
	 * @param score {@code String}
	 */
	public void afficherScore(int nbRegionsRouge, int maxRouge, int nbRegionsBleu, int maxBleu, int bonusRoute, int bonusIle)
	{
		this.panelJoueur.afficherScore( nbRegionsRouge,  maxRouge,  nbRegionsBleu,  maxBleu,  bonusRoute,  bonusIle);
	}

	/**
	 * Méthode pour afficher la bifurcation
	 */
	public void afficherBifurcation()
	{
		JOptionPane.showMessageDialog(this, "Bifurcation disponible ! (Double-clic)","BIFURCATION", 1);
	}

}
