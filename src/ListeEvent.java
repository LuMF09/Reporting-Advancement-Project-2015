import java.awt.event.*;
import java.io.IOException;

//Event pour Liste = liste de tous les projets de la BDD

public class ListeEvent implements ActionListener {
	private ProjectController _projectController;

	public ListeEvent(ProjectController ProjectController){
		this._projectController=ProjectController;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Clicked");
		try {
			this._projectController.boutonDeconnexion();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
