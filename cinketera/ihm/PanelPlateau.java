package ihm;

import java.util.List;
import javax.swing.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.awt.geom.Point2D;

import controleur.*;
import metier.Ile;
import metier.Route;

/**
 * @author Célia ANTUNES
 * @author Axel BUREAUX
 * @author Antoine CARON
 * @author Tom GOUREAU
 * 
 * @see Controleur
 * 
 * @since 18.0.2.1 s
 */
class PanelPlateau extends JPanel implements MouseListener
{
	private Controleur ctrl;

	/**
	 * Constructeur du {@code PanelPlateau}
	 * @param ctrl du jeu, utilisé pour obtenir les {@code Region}, {@code Ile} et {@code Route}
	 */
	public PanelPlateau(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setBackground(new Color(133, 197, 222));

		//GererSouris listener = new GererSouris();

		this.addMouseListener( this );
	}


	/**
	 * {@inheritDoc}
	 * Méthode utilisée pour afficher les {@code Region}, {@code Ile} et {@code Route}
	 */
	public void paintComponent (Graphics g)
	{
		super.paintComponent ( g );
		Graphics2D g2 = (Graphics2D) g;

		// Affichage des séparations des Region
		g2.setColor(new Color(95, 186, 222));
		g2.setStroke(new BasicStroke(2));
		g2.drawLine(0,340,610, 340);
		g2.drawLine(610,340, this.getWidth(), 0);
		g2.drawLine(610,340, this.getWidth(), this.getHeight());

		g2.drawLine(340,0, 340, this.getHeight());

		g2.setStroke(new BasicStroke(1));
		
		// Affichage des routes
		List<Route> lstRoute = ctrl.getLstRoute();
		for (Route route : lstRoute)
		{
			if (route.getBonus() != 0)
			{
				g2.setColor(new Color(68, 191, 23));
				g2.setStroke(new BasicStroke(12));
				g2.drawLine(route.getIle1().getCentreX(), route.getIle1().getCentreY(), route.getIle2().getCentreX(), route.getIle2().getCentreY());
			}
			g2.setStroke(new BasicStroke(5));
			g2.setColor(new Color(235,209,197));
			
			if (route.getCouleur() != null)
				g2.setColor(route.getCouleur());

			g2.drawLine(route.getIle1().getCentreX(), route.getIle1().getCentreY(), route.getIle2().getCentreX(), route.getIle2().getCentreY());
		}

		Ile ileSelect = this.ctrl.getIleSelect();
		// Affichage des iles possibles
		if (ileSelect != null)
		{
			for (Route route : ileSelect.getLstRoutes())
			{
				if (this.ctrl.estColoriable(route))
				{
					Ile ile1 = route.getIle1();
					Ile ile2 = route.getIle2();

					Image imgPossible1 = this.getToolkit().getImage ( "./donnees/images/bordures_jaunes/" + ile1.getNom() + ".png");
					Image imgPossible2 = this.getToolkit().getImage ( "./donnees/images/bordures_jaunes/" + ile2.getNom() + ".png");
					
					g2.drawImage( imgPossible1,  ile1.getImageX(), ile1.getImageY(), (int) (imgPossible1.getWidth(this)/1.25), (int) (imgPossible1.getHeight(this)/1.25) , this );
					g2.drawImage( imgPossible2,  ile2.getImageX(), ile2.getImageY(), (int) (imgPossible2.getWidth(this)/1.25), (int) (imgPossible2.getHeight(this)/1.25) , this );
				}
			}
		}

		// Affichage de l'ile sélectionnée
		if (ileSelect != null)
		{
			Image imgIleSelect = this.getToolkit().getImage ( "./donnees/images/bordures/" + ileSelect.getNom() + ".png");
			g2.drawImage( imgIleSelect,  ileSelect.getImageX(), ileSelect.getImageY(), (int) (imgIleSelect.getWidth(this)/1.25), (int) (imgIleSelect.getHeight(this)/1.25) , this );
		}



		


		


		// Affichage des Iles
		g2.setColor(Color.WHITE);

		List<Ile> lstIle = ctrl.getLstIle();
		for (Ile ile : lstIle)
		{
			Image img = this.getToolkit().getImage ( "./donnees/images/iles/" + ile.getNom() + ".png");
			g2.drawImage( img,  ile.getImageX(), ile.getImageY(), (int) (img.getWidth(this)/1.25), (int) (img.getHeight(this)/1.25) , this );
			g2.setColor(Color.BLACK);
			g2.drawString(ile.getNom(), ile.getCentreX()-4, ile.getCentreY()+2);
			g2.setColor(Color.WHITE);
			g2.drawString(ile.getNom(), ile.getCentreX()-5, ile.getCentreY()+1);
			
		}
	}

	/**
	 * {@inheritDoc}
	 * permet de sélectionner une ile en fonction de la position en utilisant {@code selectionner(Metier)}
	 */
	public void mouseClicked(java.awt.event.MouseEvent e) 
	{
			int x = e.getX();
			int y = e.getY();

			System.out.println(x + " : " + y);

			List<Ile> lstIle = this.ctrl.getLstIle();
	
			for ( Ile ile : lstIle )
			{
				if ( Point2D.distance(x, y, ile.getCentreX(), ile.getCentreY()) < 40 )
				{
					System.out.println("Sélection : " + ile.getNom());
					this.ctrl.selectionner(ile);
					
				}
			}
			repaint();
	}

	/**
	 * {@inheritDoc}
	 */
	public void mousePressed (java.awt.event.MouseEvent e) { }

	/**
	 * {@inheritDoc}
	 */
	public void mouseReleased(java.awt.event.MouseEvent e) { }

	/**
	 * {@inheritDoc}
	 */
	public void mouseEntered (java.awt.event.MouseEvent e) { }

	/**
	 * {@inheritDoc}
	 */
	public void mouseExited  (java.awt.event.MouseEvent e) { }

}
