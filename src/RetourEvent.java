import java.awt.event.*;
import java.io.IOException;

//Event pour Liste = liste de tous les projets de la BDD

public class RetourEvent implements ActionListener {
	private ProjectController _projectController;
	
	public RetourEvent(ProjectController ProjectController){
		this._projectController=ProjectController;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Clicked");
		try {

			this._projectController.boutonRetour();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
