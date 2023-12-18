package ihm;


import java.awt.*;
import javax.swing.*;

/**
 * @author Célia ANTUNES
 * @author Axel BUREAUX
 * @author Antoine CARON
 * @author Tom GOUREAU
 * 
 * @since 18.0.2.1 
 */
public class PanelDonnees extends JPanel
{
	controleur.Controleur   ctrl;
	JTable       tblGrilleDonnees;
	JScrollPane  spGrilleDonnees;

	/**
	 * Constructeur du {@code PanelDonnees}
	 * @param ctrl du jeu
	 */
	public PanelDonnees (controleur.Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout ( new BorderLayout() );

		/*-------------------------------*/
		/* CrÃ©ation des composants       */
		/*-------------------------------*/
		this.tblGrilleDonnees = new JTable ( new GrilleDonneesSave(ctrl) );
		this.tblGrilleDonnees.setFillsViewportHeight(true);
		this.tblGrilleDonnees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

 		this.spGrilleDonnees   = new JScrollPane( this.tblGrilleDonnees );

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.add ( this.spGrilleDonnees, BorderLayout.CENTER );
	}

	/**
	 * Méthode pour mettre à jour la liste des clients
	 */
	public void majListeClients ()
	{
		int hauteur = this.tblGrilleDonnees.getRowHeight();
		System.out.println ( hauteur );
		this.tblGrilleDonnees.setModel( new GrilleDonneesSave(ctrl) );
	}

	/**
	 * Méthode pour obtenir le client actif
	 * @return le numéro du client
	 */
	public int getClientActif()
	{
		return this.tblGrilleDonnees.getSelectedRow();
	}



}