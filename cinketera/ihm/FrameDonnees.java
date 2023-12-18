package ihm;

import controleur.Controleur;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 * @author Célia ANTUNES
 * @author Axel BUREAUX
 * @author Antoine CARON
 * @author Tom GOUREAU
 * 
 * @since 18.0.2.1 
 */
public class FrameDonnees extends JFrame
{
	Controleur ctrl;

	PanelDonnees  panelTable;

	/**
	 * Constructeur de la {@link JFrame}, qui construit et fait apparaitre le fenetre
	 * @param ctrl du jeu
	 */
	public FrameDonnees(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle  ( "Tableau de bord"  );
		this.setSize   ( 500, 250 );
		this.setLocation( 1000, 200 );

		/*-------------------------------*/
		/* CrÃ©ation des composants       */
		/*-------------------------------*/
		this.panelTable  = new PanelDonnees  (this.ctrl);


		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.add ( this.panelTable,  BorderLayout.CENTER );

		this.setVisible ( true );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Méthode pour faire la mise à jour des CLients
	 */
	public void majListeClients() { this.panelTable.majListeClients(); }

	/**
	 * Méthode pour obtnir le client actif
	 * @return le numéro du client actif
	 */
	public int getClientActif () { return this.panelTable.getClientActif(); }

}