import javax.swing.*;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class DetailsView extends JFrame {

	private ProjectModel _projectModel;
	private ProjectController _pc;
	private ResultSet rs,rs2;
	private JLabel _projetLabel, _nomLabel, _avancementLabel, _etapeLabel;
	private JButton _ajoutButton, _envoyerMail, _ajouterEmploye,_retour;
	private JProgressBar barre;
	private String nom;
	private int _avancement;
	private Calendrier cal;
	//	private JPanel sousPanelNorth, pan;

	public DetailsView(ProjectController projectController){

		this.setTitle("Détails");
		this.setSize(1500,800);
		_projectModel= new ProjectModel();
		_pc = projectController;

	}


	//Affiche ou non la fenetre
	public void afficher(boolean ouiounon){
		setVisible(ouiounon);
	}

	//Détruit et cache la fenetre
	public void destroyFrame()
	{
		this.pack();
		this.setVisible(false);
		this.dispose();
	}


	public void mettreAJour(int id/*id du projet*/) {
//int ide;//cela va etre l'id de la categorie
		Container pan = this.getContentPane();
		pan.setLayout(new BorderLayout());

		// création de sous panel

		JPanel sousPanelNorth = new JPanel();
		sousPanelNorth.setLayout(new GridLayout(1,2));
		pan.add(sousPanelNorth, BorderLayout.NORTH);

		try {		
			rs= _projectModel.quelProjet(id);


			if(rs.next()) /* s'il y a au moins un enregistrement */
			{
				id = rs.getInt("id");
				String nom = rs.getString("nom");
				_projetLabel = new JLabel("Projet "+Integer.toString(id)+" : "+nom);
				sousPanelNorth.add(_projetLabel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal= new Calendrier();
		sousPanelNorth.add(cal);
int n=6;
		JPanel sousPanelCenter = new JPanel();
		sousPanelCenter.setLayout(new GridLayout(n,6));
		pan.add(sousPanelCenter, BorderLayout.CENTER);

		JLabel categories = new JLabel("Catégorie ");
		sousPanelCenter.add(categories);

		JLabel avancement=new JLabel("Avancement");
		JLabel vide2=new JLabel("");
		JLabel etapeEnCours=new JLabel("Etape en cours");
		JLabel vide4=new JLabel("");
		JLabel vide5=new JLabel("");
	//	JLabel vide6=new JLabel("");

		sousPanelCenter.add(avancement);
		sousPanelCenter.add(vide2);
		sousPanelCenter.add(etapeEnCours);
		sousPanelCenter.add(vide4);
		sousPanelCenter.add(vide5);
		//sousPanelCenter.add(vide6);


		try {		
			rs2= _projectModel.chercherCategories(id);
			while(rs2.next()) /* s'il y a au moins un enregistrement */
			{

				nom= rs2.getString("nom");
				_nomLabel= new JLabel(nom);
				sousPanelCenter.add(_nomLabel);
				System.out.println(nom);

				
				_avancement = rs2.getInt("avancement");
				barre= new JProgressBar(0,100);
				barre.setValue(_avancement);
				sousPanelCenter.add(barre);

				_avancementLabel = new JLabel("("+Integer.toString(_avancement)+"%)");
				sousPanelCenter.add(_avancementLabel);
				System.out.println(_avancement);
				
				//ide=_projectModel.chercherIdCategorie(nom);
				//ResultSet etape= _projectModel.chercherEtapeCategorie(ide);
				
				String etapeCours=rs2.getString("etape.nom");
				_etapeLabel= new JLabel(etapeCours);
				sousPanelCenter.add(_etapeLabel);
				System.out.println(etapeCours);
				
			/*	while(etape.next()){
				
				String etapeCours=etape.getString("nom");
				_etapeLabel=new JLabel(etapeCours);
				sousPanelCenter.add(_etapeLabel);
				System.out.println(etapeCours);
				}*/
				

				_envoyerMail = new JButton("Envoyer un mail au responsable");
				sousPanelCenter.add(_envoyerMail);
				//_projectModel.chercherResponsable(ide);
				//_envoyerMail.addActionListener(new MailEvent(_pc, ide));

				_ajouterEmploye = new JButton("Affecter employé");
				sousPanelCenter.add(_ajouterEmploye);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel sousPanelSouth = new JPanel();
		sousPanelSouth.setLayout(new GridLayout(1,2));
		pan.add(sousPanelSouth, BorderLayout.SOUTH);

		_ajoutButton= new JButton("Ajouter une catégorie");
		_ajoutButton.addActionListener(new RetourEvent(_pc));
		
		_retour = new JButton("Retour");

		sousPanelSouth.add(_ajoutButton);
		sousPanelSouth.add(_retour);
		_retour.addActionListener(new RetourEvent(_pc));
	}
}
