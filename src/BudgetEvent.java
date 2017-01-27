import java.awt.event.*;
import java.io.IOException;

public class BudgetEvent implements ActionListener {
	private ProjectController _projectController;
	private ListeView _listeView;
	private ProjectModel _projectModel;

	public BudgetEvent(ProjectController ProjectController){
		this._projectController=ProjectController;
	}

	public void actionPerformed(ActionEvent arg0) {
		_projectController=new ProjectController();
		_listeView = new ListeView(_projectController);
		_projectModel = new ProjectModel();
		
		System.out.println("budget:"+_listeView.getchangeBudget());
		System.out.println("Budget changé !");

		
		int budg = Integer.parseInt(_listeView.getchangeBudget());
		_projectModel.changerBudget(budg);
		System.out.println(budg);
try {
	this._projectController.boutonChangerBudget();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}

}
