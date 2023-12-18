package ihm;


import java.util.List;
import javax.swing.*;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;

import controleur.*;
import metier.Carte;

/**
 * @author Célia ANTUNES
 * @author Axel BUREAUX
 * @author Antoine CARON
 * @author Tom GOUREAU
 * 
 * @see Controleur
 * 
 * @since 18.0.2.1 
 */
class PanelJoueur extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private int nbRegionsRouge, maxRouge, nbRegionsBleu, maxBleu, bonusIle, bonusRoute;

	private JButton btnPasser;
	private int     nbCrtPass;

	/**
	 * Constructeur du {@code PanelJoueur}
	 * @param ctrl de cinketera, utilisé pour obtenir les {@code Carte} du {@code Joueur}
	 */
	public PanelJoueur(Controleur ctrl)
	{
		this.setLayout(new BorderLayout());
		this.ctrl = ctrl;

		/*
		 *  Création des composants
		 */

		this.btnPasser = new JButton("Passer le tour");
		this.btnPasser.setPreferredSize(new Dimension(400, 40));

		 /*
		  *  Ajout des composants
		  */

			this.add(this.btnPasser, BorderLayout.SOUTH);

		/*
		 *  Activation des composants
		 */

			this.btnPasser.addActionListener(this);
	}

	public int getNbCrtPass() { return this.nbCrtPass;}


	/**
	 * {@inheritDoc}
	 * Méthode utilisée pour afficher les {@code Carte} de la pioche, de la main et dela défausse, ainsi que le score.
	 */
	public void paintComponent (Graphics g)
	{
		super.paintComponent ( g );
		Graphics2D g2 = (Graphics2D) g;


		// Affichage de la carte actuelle
		Carte main = this.ctrl.getMain();
		String type;
		Image img;

		if (main != null)
		{
			g2.drawImage(this.getToolkit().getImage("./donnees/images/cartes/indice_primaire.png"), 28, 28, 50*3+4,80*3+4 , this );


			if (main.getEstPrimaire()) { type = "bord_noir" ; }
			else                    { type = "bord_blanc"; }

			img = this.getToolkit().getImage ( "./donnees/images/cartes/" + type + "_fond_" + main.getCouleur() + ".png");
			g2.drawImage( img, 30, 30, 50*3,80*3 , this );
			g2.drawString(main.toString(), 205, 160);
		}

		
		// Affichage de la pioche
		img =  this.getToolkit().getImage ( "./donnees/images/cartes/face_cachee.png");

		List<Carte> pioche = this.ctrl.getPioche();
		for (int cpt = pioche.size() ; cpt > 0; cpt--)
		{
			g2.drawImage( img, 190 + cpt*10, 185, 50,80 , this );
		}


		// Affichage du compteur de cartes primaires possédées
		int nbCartesP = this.ctrl.getNbCarteP();

		g2.drawString("Cartes primaires jouées", 30, 390);
		for (int cpt = 0; cpt < nbCartesP; cpt++)
		{
			g2.drawImage( this.getToolkit().getImage("./donnees/images/cartes/indice_primaire.png") , 30 + cpt*(20+10), 400, 25, 40, this );
		}
		
		for (int cpt = 0; cpt < 10 - nbCartesP; cpt++)
		{
			g2.drawImage( this.getToolkit().getImage("./donnees/images/cartes/indice_primaire_vide.png") , 30 + nbCartesP*(20+10) + cpt*(20+10), 400, 25, 40, this );
		}

		
		// Affichage de la liste des cartes disponibles
		List<Carte> lstPioche = this.ctrl.getPioche();

		for (int cpt = 0; cpt < lstPioche.size(); cpt++)
		{
			Carte carte = lstPioche.get(cpt);

			if (carte.getEstPrimaire()) { type = "bord_noir" ; }
			else                        { type = "bord_blanc"; }

			
			img = this.getToolkit().getImage ( "./donnees/images/cartes/" + type + "_fond_" + carte.getCouleur() + ".png");

		g2.drawImage( this.getToolkit().getImage("./donnees/images/cartes/indice_primaire.png") , 30 + cpt*(20+10)-1, 300-1, 25+2, 40+2, this );
			g2.drawImage( img, 30 + cpt*(20+10), 300, 25, 40, this );

		}



		// Affichage des infos du joueur
		g2.drawString("Informations du joueur", 30, 485);
		g2.setColor(this.ctrl.getCoulJoueur());
		g2.fillRect(30, 495, 100, 40);

		g2.setColor(Color.BLACK);
		g2.drawRect(30, 495, 100, 40);

		g2.drawString("Score : " + this.ctrl.getScore(), 30, 550);


		g2.drawString("Nombre de régions visitées par ROUGE : " + this.nbRegionsRouge, 30, 580);
		g2.drawString("Maximum d'îles visitées par ROUGE       : " + this.maxRouge, 30, 595);

		g2.drawString("Nombre de régions visitées par BLEU     : " + this.nbRegionsBleu , 30, 610);
		g2.drawString("Maximum d'îles visitées par BLEU           : " + this.maxBleu, 30, 625);

		g2.drawString("Bonus par passage de routes hardues    : " + this.bonusRoute, 30, 640);
		g2.drawString("Bonus de double voie sur une île              : " + this.bonusIle, 30, 655);

		repaint();
	}

	/**
	 * Méthode pour afficher le score
	 */
	public void afficherScore(int nbRegionsRouge, int maxRouge, int nbRegionsBleu, int maxBleu, int bonusRoute, int bonusIle)
	{
		this.nbRegionsRouge = nbRegionsRouge;
		this.nbRegionsBleu  = nbRegionsBleu;
		this.maxRouge       = maxRouge;
		this.maxBleu        = maxBleu;
		this.bonusIle       = bonusIle;
		this.bonusRoute     = bonusRoute;
	}

	/**
	 * {@inheritDoc}
	 * Utilisé pour savoir si le {@link Joueur} passe le tour ou
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnPasser) 
		{ 
			this.ctrl.tirerCarte(); 
			if (this.ctrl.getNbCarteP() >= 5)
				this.nbCrtPass++;

			this.ctrl.majIHM();
		}
	}

}
