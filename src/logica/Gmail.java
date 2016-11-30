package logica;

import java.util.*;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Gmail extends CorreoElectronico {
	
	public Gmail(){
		super();
	}

	@Override
	public boolean iniciarSesion(String usr, String pswd) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session s = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usr, pswd);
			}
		  });
		
		Transport transport;
		try {
		    transport = s.getTransport("smtp");
		    transport.connect("smtp.gmail.com", usr, pswd);
		    transport.close();
		    usuario = usr;
		    sesionSMTP = s;
	    	return true;	    	
		} catch (Exception e) {
		    return false;
		}
	}

	@Override
	public boolean enviarMensaje(Mensaje mensaje) {

		try {
			Message message = new MimeMessage(sesionSMTP);
			message.setFrom(new InternetAddress(mensaje.getOrigen()));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(mensaje.getDestino()));
			message.setSubject(mensaje.getAsunto());
			message.setText(mensaje.getContenido());

			Transport.send(message);			
			return true;
		} catch (MessagingException e) {
			return false;
		}		
	}

}