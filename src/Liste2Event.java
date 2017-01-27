import java.awt.event.*;
import java.io.IOException;

//Event pour Liste = liste de tous les projets de la BDD

public class Liste2Event implements ActionListener {
	private ProjectController _projectController;
	private int _id;
	public Liste2Event(ProjectController ProjectController, int id){
		this._projectController=ProjectController;
		_id = id ;


	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Clicked"  + _id );
		try {
			_projectController.setID(_id);
			this._projectController.boutonDetails();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
