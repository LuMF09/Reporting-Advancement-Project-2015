import javax.swing.*;

import java.awt.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListeView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel _idLabel, _avancementLabel,_debutLabel, _finLabel, _budgetLabel,_etapeLabel,numProjet,nomProjet,avancementProjet,videProjet,etapeProjet,debutProjet,finProjet,budgetProjet,_videLabel1,_videLabel2,_videLabel3,_videLabel4,_videLabel5,_videLabel6,_videLabel7,_videLabel8,_videLabel9,_videLabel10,_videLabel11;
	private JProgressBar barre;
	private JButton _decoButton, _nomButton,_changeBudget1,_changeBudget2,_changeBudget3,_changeBudget4;
	private ProjectModel _projectModel;
	private ResultSet rs;
	private JTextField _budget1TextField,_budget2TextField,_budget3TextField,_budget4TextField;
	private int avancement, budget;
	private Date debut,fin;
	private String _nom;
	private int id,i=1;
//	private ProjectController _projectController;
	
	public ListeView(ProjectController projectController){

		super();
		this.setTitle("Liste des projets");
		this.setSize(1450,200);
		_projectModel= new ProjectModel();

		Container c = this.getContentPane();
		c.setLayout(new GridLayout(6,10));
		
		numProjet = new JLabel("Projet n°");
		c.add(numProjet);
		nomProjet = new JLabel("Nom du projet");
		c.add(nomProjet);
		avancementProjet = new JLabel("Avancement");
		c.add(avancementProjet);
		videProjet = new JLabel("");
		c.add(videProjet);
		etapeProjet = new JLabel("Etape en cours");
		c.add(etapeProjet);
		debutProjet = new JLabel("Début du projet");
		c.add(debutProjet);
		finProjet = new JLabel("Fin du projet");
		c.add(finProjet);
		budgetProjet = new JLabel("Budget");
		c.add(budgetProjet);
		_videLabel10 = new JLabel("");
		_videLabel11 = new JLabel("");
		c.add(_videLabel10);
		c.add(_videLabel11);

		try {		
			rs= _projectModel.chercherProjet();
			while(rs.next()) /* s'il y a au moins un enregistrement */
			{
				id=rs.getInt("id");
				_idLabel = new JLabel("Projet "+Integer.toString(id)+":");
				c.add(_idLabel);

				_nom= rs.getString("nom");
				_nomButton= new JButton(_nom);
				c.add(_nomButton);
				_nomButton.addActionListener(new Liste2Event(projectController, id));


				avancement = rs.getInt("avancement");
				barre= new JProgressBar(0,100);
				barre.setValue(avancement);
				c.add(barre);

				_avancementLabel = new JLabel("("+Integer.toString(avancement)+"%)");
				c.add(_avancementLabel);
				
				ResultSet etape= _projectModel.chercherEtape(id);
				
				while(etape.next()){
				
				String etapeCours=etape.getString("nom");
				_etapeLabel=new JLabel(etapeCours);
				c.add(_etapeLabel);}

				debut = rs.getDate("debut");
				_debutLabel = new JLabel(debut.toString());
				c.add(_debutLabel);

				fin = rs.getDate("fin");
				_finLabel = new JLabel(fin.toString());
				c.add(_finLabel);

				budget = rs.getInt("budget");
				_budgetLabel= new JLabel(budget+"€");
				if(budget <=0){
					_budgetLabel.setForeground(Color.red);
					
				}
				else{
				_budgetLabel.setForeground(Color.green);
					
				}
				c.add(_budgetLabel);
  
				if(i==1){
					_budget1TextField = new JTextField();
					_changeBudget1 = new JButton("Changer Budget");
					c.add(_budget1TextField);
					c.add(_changeBudget1);
					_changeBudget1.addActionListener(new BudgetEvent(projectController));
				}
				if(i==2){
					_budget2TextField = new JTextField ("");
					_changeBudget2 = new JButton("Changer Budget");
					c.add(_budget2TextField);
					c.add(_changeBudget2);
				}
				if(i==3){
					_budget3TextField = new JTextField ("");
					_changeBudget3 = new JButton("Changer Budget");
					c.add(_budget3TextField);
					c.add(_changeBudget3);
				}
				if(i==4){
					_budget4TextField = new JTextField ("");
					_changeBudget4 = new JButton("Changer Budget");
					c.add(_budget4TextField);
					c.add(_changeBudget4);
				}
				i++;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_videLabel1 = new JLabel("");
		_videLabel2 = new JLabel("");
		_videLabel3 = new JLabel("");
		_videLabel4 = new JLabel("");
		_videLabel5 = new JLabel("");
		_videLabel6 = new JLabel("");
		_videLabel7 = new JLabel("");
		_videLabel8 = new JLabel("");
		_videLabel9 = new JLabel("");
		
		c.add(_videLabel5);
		c.add(_videLabel1);
		c.add(_videLabel2);
		c.add(_videLabel3);
		c.add(_videLabel4);
		c.add(_videLabel6);
		c.add(_videLabel7);
		c.add(_videLabel8);
		c.add(_videLabel9);
		

		_decoButton = new JButton("Déconnexion");
		c.add(_decoButton);
		_decoButton.addActionListener(new ListeEvent(projectController));

	}


	//Affiche ou non la fenetre
	public void afficher(boolean ouiounon){
		setVisible(ouiounon);
	}
	public String getchangeBudget(){
		return (_budget1TextField != null ? _budget1TextField.getText(): "444");
	}

	//Détruit et cache la fenetre
	public void destroyFrame()
	{
		this.setVisible(false);
		this.dispose();
	}





}
