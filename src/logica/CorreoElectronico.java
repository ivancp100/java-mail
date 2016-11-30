package logica;


import javax.mail.Session;


public abstract class CorreoElectronico {


	protected String usuario;	
	protected Session sesionSMTP;
	
	
	public String getUsuario() {		
		return usuario;
	}
	
	public abstract boolean iniciarSesion(String usr, String pswd);	
	public abstract boolean enviarMensaje(Mensaje mensaje);

}