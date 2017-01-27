import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProjectModel{

	private Connection _conn;

	//Constructeur pour connexion à la BDD
	public ProjectModel(){

		String nomUser = "root"; // Utilisateur de la BD
		String passwd = ""; // Password de l'utilisateur de la BD
		String url = "jdbc:mysql://localhost/"; // Serveur de la BD
		String nomBase = "tai"; // Nom de la BD sur laquelle nous allons acceder
		_conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			_conn=DriverManager.getConnection(url+nomBase, nomUser, passwd);
			System.out.println("Je me connecte a la base de donnees: " + nomBase);
		}
		catch (SQLException ex1)
		{
			System.out.println("J'ai détecté une erreur de type SQL: " + ex1.getMessage());
		}
		catch (Exception ex2)
		{
			System.out.println("J'ai détecté une erreur de type lang: " + ex2.getMessage());
		}
		System.out.println("Base de donnée connecté");
	}

	//Fonction de validation login/mdp
	public boolean estValide(String log, String mdp){
		boolean trouve= false;
		try
		{
			String requete = new String("SELECT id, nom, login FROM membre WHERE login=? AND mot_de_passe=MD5(?);");

			PreparedStatement stmt = _conn.prepareStatement(requete);
			stmt.setString(1, log);
			stmt.setString(2, mdp);
			ResultSet rs = stmt.executeQuery();

			if (rs.next ()) /* s'il y a au moins un enregistrement */
			{
				trouve = true;

			}

			rs.close();
			stmt.close();
		}
		catch (SQLException ex3)
		{
			while (ex3 != null)
			{
				System.out.println(ex3.getSQLState());
				System.out.println(ex3.getMessage());
				System.out.println(ex3.getErrorCode());
				ex3=ex3.getNextException();
			}
		}
		return trouve;
	}

	//Fonction permettant de retourner les infos de la BDD (pour les utiliser dans la view)
	public ResultSet chercherProjet(){
		ResultSet err = null;


		try
		{
			String requete = new String("SELECT id, nom, avancement, debut, fin, budget FROM liste_projets;");

			PreparedStatement stmt = _conn.prepareStatement(requete);

			ResultSet rs = stmt.executeQuery();

			return rs;



		}
		catch (SQLException ex3)
		{
			while (ex3 != null)
			{
				System.out.println(ex3.getSQLState());
				System.out.println(ex3.getMessage());
				System.out.println(ex3.getErrorCode());
				ex3=ex3.getNextException();
			} return err;
		}

	}

	public ResultSet quelProjet(int id)
	{
		ResultSet err = null;
		try
		{

			String requete = new String("SELECT id, nom FROM liste_projets WHERE id="+Integer.toString(id)+";");

			PreparedStatement stmt = _conn.prepareStatement(requete);

			ResultSet rs = stmt.executeQuery();

			return rs;



		}
		catch (SQLException ex3)
		{
			while (ex3 != null)
			{
				System.out.println(ex3.getSQLState());
				System.out.println(ex3.getMessage());
				System.out.println(ex3.getErrorCode());
				ex3=ex3.getNextException();
			} return err;
		}

	}

	public ResultSet chercherCategories(int id){
		ResultSet err = null;
		try
		{


			String requete = new String("SELECT categorie.id, categorie.nom, categorie.avancement, etape.nom FROM categorie, etape WHERE etape.id_liste_projets="+Integer.toString(id)+" AND etape.id_categorie=categorie.id;");

			PreparedStatement stmt = _conn.prepareStatement(requete);

			ResultSet rs = stmt.executeQuery();

			return rs;



		}
		catch (SQLException ex3)
		{
			while (ex3 != null)
			{
				System.out.println(ex3.getSQLState());
				System.out.println(ex3.getMessage());
				System.out.println(ex3.getErrorCode());
				ex3=ex3.getNextException();
			} return err;
		}
	}


	public ResultSet chercherEtape(int id){
		ResultSet err = null;


		try
		{
			String requete = new String("SELECT nom FROM etape WHERE id_liste_projets="+Integer.toString(id)+" AND date_fin<NOW() ORDER BY date_fin DESC LIMIT 1 ;");

			PreparedStatement stmt = _conn.prepareStatement(requete);

			ResultSet rs = stmt.executeQuery();

			return rs;



		}
		catch (SQLException ex3)
		{
			while (ex3 != null)
			{
				System.out.println(ex3.getSQLState());
				System.out.println(ex3.getMessage());
				System.out.println(ex3.getErrorCode());
				ex3=ex3.getNextException();
			} return err;
		}

	}

	public int chercherIdCategorie(String nom) {
		int id=0;
		try
		{
			String requete = new String("SELECT categorie.id FROM categorie WHERE categorie.nom='"+nom+"' LIMIT 1;");

			PreparedStatement stmt = _conn.prepareStatement(requete);

			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				id=rs.getInt("id");
			}
			return id;
		}
		catch (SQLException ex3)
		{
			while (ex3 != null)
			{
				System.out.println(ex3.getSQLState());
				System.out.println(ex3.getMessage());
				System.out.println(ex3.getErrorCode());
				ex3=ex3.getNextException();
			} ;return 5;
		}
	}

	public void changerBudget(int changeBudget) {
	
		try
		{
			String requete = new String("UPDATE liste_projets SET budget='"+changeBudget+"' WHERE id=1;");

			PreparedStatement stmt = _conn.prepareStatement(requete);

			stmt.executeUpdate();
			
		}
		catch (SQLException ex3)
		{
			while (ex3 != null)
			{
				System.out.println(ex3.getSQLState());
				System.out.println(ex3.getMessage());
				System.out.println(ex3.getErrorCode());
				ex3=ex3.getNextException();
			}
		}
		
	}
	public ResultSet chercherResponsable(int id)
	{
		ResultSet err = null;
		try
		{

			String requete = new String("SELECT id, nom FROM liste_projets WHERE id="+Integer.toString(id)+";");

			PreparedStatement stmt = _conn.prepareStatement(requete);

			ResultSet rs = stmt.executeQuery();

			return rs;



		}
		catch (SQLException ex3)
		{
			while (ex3 != null)
			{
				System.out.println(ex3.getSQLState());
				System.out.println(ex3.getMessage());
				System.out.println(ex3.getErrorCode());
				ex3=ex3.getNextException();
			} return err;
		}

	}
	
	
}
