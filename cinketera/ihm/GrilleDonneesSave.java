package ihm;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import controleur.Controleur;

/**
 * @author Célia ANTUNES
 * @author Axel BUREAUX
 * @author Antoine CARON
 * @author Tom GOUREAU
 * 
 * @since 18.0.2.1 
 */
public class GrilleDonneesSave extends AbstractTableModel
{
	private Controleur ctrl;

	private String[]   tabEntetes;
	private Object[][] tabDonnees;

	/**
	 * Constructeur de la {@code GrilleDonneesSave}
	 * @param ctrl du jeu
	 */
	public GrilleDonneesSave (Controleur ctrl)
	{
		this.ctrl = ctrl;

		List<String[]> lstActions = ctrl.getLstSave();

		tabDonnees = new Object[lstActions.size()][3];

		for ( int lig=0; lig<lstActions.size(); lig++)
		{
			tabDonnees[lig][0] = lstActions.get(lig)[0];
			tabDonnees[lig][1] = lstActions.get(lig)[1];
			tabDonnees[lig][2] = lstActions.get(lig)[2];
		}

		this.tabEntetes = new String[]   {"manche", "tour", "action"};
		this.fireTableRowsInserted(lstActions.size()-1, lstActions.size()-1);

	}

	// Méthodes permettant de définir l'aspect du JTable
	public int    getColumnCount()                 { return this.tabEntetes.length;           }
	public int    getRowCount   ()                 { return this.tabDonnees.length;           }
	public String getColumnName (int col)          { return this.tabEntetes[col];             }
	public Object getValueAt    (int row, int col) { return this.tabDonnees[row][col];        }
	public Class  getColumnClass(int c)            { return this.getValueAt(0, c).getClass(); }

	/**
	 * {@inheritDoc}
	 * Méthode pour savoir si la cellule est editable ou nom
	 * @param row ligne de la cellule
	 * @param col collone de la cellule
	 * @return true si la cellule est editable, false sinon
	 */
	public boolean isCellEditable(int row, int col)
	{
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setValueAt(Object value, int row, int col) {}


}