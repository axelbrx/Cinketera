package ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.Controleur;
import metier.Carte;

/**
 * @author Célia ANTUNES
 * @author Axel BUREAUX
 * @author Antoine CARON
 * @author Tom GOUREAU
 * 
 * @since 18.0.2.1 
 */
public class FrameMenu extends JFrame implements ActionListener
{

	private Controleur ctrl;
	private FramePlateau framePlateau;

	private JPanel    panelChoix;

	private JButton   btnJ1;
	private JButton   btnScenario;

	private JComboBox<String> ddlstScenario;

	/**
	 * Constructeur de la {@link FrameMenu}, qui construit et fait apparaitre la fenetre du menu
	 */
	public FrameMenu(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.framePlateau = null;

		this.panelChoix = new JPanel();
		this.panelChoix.setLayout(new GridLayout(4,1, 8, 8));

		
		this.btnJ1 = new JButton("Solo");
		this.btnScenario = new JButton("Lancer un scénario");

		this.ddlstScenario = new JComboBox<String>();
		this.ddlstScenario.addItem("Scenario 1 - Début de partie");
		this.ddlstScenario.addItem("Scenario 2 - Les premières fonctionnalités");
		this.ddlstScenario.addItem("Scenario 3 - Situation de blocage");
		this.ddlstScenario.addItem("Scenario 4 - Tentative de cycle");
		this.ddlstScenario.addItem("Scenario 5 - Route déjà coloriée");
		this.ddlstScenario.addItem("Scenario 6 - Avenants");
		this.ddlstScenario.addItem("Scenario 7 - Fin de partie");

		this.add(new JLabel(new ImageIcon("./donnees/images/titre.png")), BorderLayout.NORTH);
		this.add(this.panelChoix, BorderLayout.CENTER);

		this.panelChoix.add(this.btnJ1);
		this.panelChoix.add(this.btnScenario, BorderLayout.CENTER);
		this.panelChoix.add(this.ddlstScenario);

		this.btnJ1.addActionListener(this);
		this.btnScenario.addActionListener(this);

		this.setTitle("Cinketera");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLocation( 100,100);
		this.pack();
		this.setVisible(true);
	}

	/**
	 * {@inheritDoc}
	 * Méthode pour savoir quel bouton est cliqué
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnJ1)
		{
			this.setVisible(false);
			this.framePlateau = new FramePlateau(ctrl);
			this.ctrl.lancerPartie();
		}

		if (e.getSource() == this.btnScenario)
		{
			this.setVisible(false);
			this.framePlateau = new FramePlateau(ctrl);
			this.ctrl.lancerScenario(this.ddlstScenario.getSelectedIndex()+1);
		}
	}

	/**
	 * Méthode pour savoir le nombre de carte passées
	 * @return numéro de la {@link Carte}
	 */
	public int getNbCrtPass() {return this.framePlateau.getNbCrtPass();}

	/**
	 * Méthode pour finir la partie
	 */
	public void terminerPartie() { this.framePlateau.terminerPartie(); }

	/**
	 * Méthode pour afficher le score
	 */
	public void afficherScore(int nbRegionsRouge, int maxRouge, int nbRegionsBleu, int maxBleu, int bonusRoute, int bonusIle)
	{
		this.framePlateau.afficherScore( nbRegionsRouge,  maxRouge,  nbRegionsBleu,  maxBleu,  bonusRoute,  bonusIle);
		this.framePlateau.afficherScore( nbRegionsRouge,  maxRouge,  nbRegionsBleu,  maxBleu,  bonusRoute,  bonusIle);
		
	}


	/**
	 * Méthode pour afficher la bifurcation
	 */
	public void afficherBifurcation() { this.framePlateau.afficherBifurcation(); }

	/**
	 * Méthode pour mettre a jour la {@link JFrame}
	 */
	public void majIHM() { this.framePlateau.repaint();}
}
