/*
 * 
 * Cette classe a éte trouvée sur internet
 * 
 * 
 * 
 * */
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.Properties;

public class EnvoyerMail extends JFrame {

	private static final long serialVersionUID = 1L;

	public EnvoyerMail(ProjectController projectController){

		super();
		this.setTitle("Envoyer un E-Mail");
		this.setSize(1450,200);


/* L'adresse IP de votre serveur SMTP */
String smtpServer = "86.65.151.7";/*:5050*/

/* L'adresse de l'expéditeur */
String from = "l.maman@net.estia.fr";

/* L'adresse du destinataire */
String dest="l.maman";
String to = dest+"@net.estia.fr";

/* L'objet du message */
String objet = "Objet";

/* Le corps du mail */
String texte = "Texte du mail";

Properties props = System.getProperties();
props.put("mail.smtp.host", smtpServer);
/* Session encapsule pour un client donné sa connexion avec le serveur de mails.*/
Session session = Session.getDefaultInstance(props, null);

/* Création du message*/
Message msg = new MimeMessage(session);

try {
      msg.setFrom(new InternetAddress(from));
      msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to, false));
      msg.setSubject(objet);
      msg.setText(texte);
      msg.setHeader("X-Mailer", "LOTONtechEmail");
      Transport.send(msg);
}
catch (AddressException e) {
      e.printStackTrace();
} 
catch (MessagingException e) {
      e.printStackTrace();
}
}
	
	public void afficher(boolean ouiounon){
		setVisible(ouiounon);
	}
}