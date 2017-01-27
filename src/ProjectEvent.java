import java.awt.event.*;
import java.io.IOException;


// Event pour Project = log/mdp

public class ProjectEvent implements ActionListener {
	private ProjectController _projectController;

	public ProjectEvent(ProjectController ProjectController){
		this._projectController=ProjectController;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Clicked");
		try {
			this._projectController.boutonValider();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
