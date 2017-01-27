import java.io.IOException;

public class ProjectController {

	private ProjectView _projectView;
	private ProjectModel _projectModel;
	private ListeView _listeView;
	private DetailsView _detailsView;
	//private EnvoyerMail _envoyerMail;
	private int id;

	public ProjectController(){
		_projectView=new ProjectView(this);
		_listeView= new ListeView(this);
		_detailsView= new DetailsView(this);
		_projectModel=new ProjectModel();
		//_envoyerMail=new EnvoyerMail(this);
	}

	public void start(){
		_projectView.afficher(true);
	}

	//Fonction liée à ProjectView
	public void boutonValider() throws IOException
	{
		if(_projectModel.estValide(_projectView.getProject(), _projectView.getPassword())){
			_projectView.afficherMessage("Login correct");
			_projectView.destroyFrame();
			_listeView.afficher(true);
		}
		else 
			_projectView.afficherMessage("Login incorrect"
					+ "      Cliquer sur ok pour réessayer");

	}

	public void boutonDetails() throws IOException
	{
		_listeView.destroyFrame();
		_detailsView.afficher(true);
	}

	//Fonction liée à ListeView
	public void boutonDeconnexion() throws IOException
	{
		_listeView.destroyFrame();
		_projectView.afficher(true);
	}

	public void boutonRetour() throws IOException
	{
		_detailsView.destroyFrame();
		_listeView.afficher(true);
	}
/*	public void boutonMail() throws IOException{
		_envoyerMail.afficher(true);
	}*/

	//doit permettre de récupérer l'ID et permet de savoir quel projet est selectionné
	public void setID(int id){
		this.id=id;
		_detailsView.mettreAJour(this.id);
	}
/*public String setMail(int id){
	String res;
	res=_projectModel.chercherResponsable(id)+"@net.estia.fr";
	return res;
}*/

public void boutonChangerBudget() throws IOException
{
	_listeView.destroyFrame();
	_listeView.afficher(true);
	
	
}
}
