import javax.swing.*;

import java.awt.*;


@SuppressWarnings("serial")
public class ProjectView extends JFrame {

	private JTextField _projectTextField ;
	private JPasswordField _mdpTextField ;

	public ProjectView(ProjectController projectController){
		super("Connexion");

		// pan est un panneau
		Container pan = this.getContentPane();
		pan.setPreferredSize(new Dimension(250, 100));
		pan.setLayout(new GridLayout(3,2));

		//Cr�ation des boutons

		JLabel login ;
		login = new JLabel("Login");
		pan.add(login);

		_projectTextField = new JTextField();
		pan.add(_projectTextField);

		JLabel mdp ;
		mdp = new JLabel("Mot de passe");
		pan.add(mdp);

		_mdpTextField = new JPasswordField();
		pan.add(_mdpTextField);

		JLabel vide ; 
		vide = new JLabel("");
		pan.add(vide);

		JButton valider ; 
		valider = new JButton("Valider");
		pan.add(valider);
		valider.addActionListener(new ProjectEvent(projectController));

		this.pack();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//Rend visible ou non la fenetre
	public void afficher(boolean ouiounon){
		setVisible(ouiounon);
	}

	//R�cupere le login
	public String getProject(){
		return (_projectTextField.getText());
	}

	//R�cup�re le mdp
	public String getPassword(){
		// new string pour cr�er une nouvelle chaine de caract�re
		return new String(_mdpTextField.getPassword());
	}

	//Affiche un message popup
	protected void afficherMessage(String msg)
	{
		JOptionPane.showMessageDialog(this,
				msg,
				"Information",
				JOptionPane.INFORMATION_MESSAGE);
	}

	//D�truit et cache la fenetre
	public void destroyFrame()
	{
		this.pack();
		this.setVisible(false);
		this.dispose();
	}


}
