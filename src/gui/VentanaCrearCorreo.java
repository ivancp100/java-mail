package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logica.CorreoElectronico;
import logica.Mensaje;


public class VentanaCrearCorreo extends JFrame implements ActionListener{

	private JTextField txtDestino;
	private JTextField txtAsunto;
	private JTextArea txtContenido;
	private JButton btnSalir;
	private JButton btnEnviar;	
	private CorreoElectronico correo;
	
	public VentanaCrearCorreo(CorreoElectronico ce) {				
		correo = ce;
		txtDestino = new JTextField();
		txtAsunto = new JTextField();		
		txtContenido = new JTextArea(50, 30);
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this);
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(this);
		
		//CREANDO PANEL MENSAJE
		JPanel pnlMensaje =  new JPanel();
		pnlMensaje.setLayout( new BoxLayout(pnlMensaje, BoxLayout.PAGE_AXIS) );
		pnlMensaje.setBorder(new TitledBorder("Mensaje"));
		pnlMensaje.add( new JLabel("Destino: ") );
		pnlMensaje.add( txtDestino );
		pnlMensaje.add( new JLabel("Asunto: ") );
		pnlMensaje.add( txtAsunto );
		pnlMensaje.add( new JLabel("Contenido: ") );
		JScrollPane scrollContenido = new JScrollPane( txtContenido );
		pnlMensaje.add( scrollContenido );
		
		//CONFIGURANDO VENTANA
		this.setTitle("Nuevo Mensaje");
		this.setLayout( new BorderLayout() );
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(800, 600);
		
		//AGREGANDO PANELES Y OTROS		
		JPanel panel2 = new JPanel();
		panel2.add( btnSalir );
		panel2.add( btnEnviar );		
		this.add( pnlMensaje, BorderLayout.CENTER );
		this.add( panel2, BorderLayout.SOUTH );		
	}
	
	
	public void lanzarVentana() {
		this.setLocationRelativeTo(null);		
		iniciarSesion();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == btnEnviar ){
			enviarCorreo();
		}else if( e.getSource() == btnSalir ){
			System.exit(0);
		}
	}
	
	
	private void enviarCorreo() {
		Mensaje m = new Mensaje(correo.getUsuario(), txtDestino.getText(), txtAsunto.getText(), txtContenido.getText());
		boolean success = correo.enviarMensaje(m);
		if(success)
			JOptionPane.showMessageDialog(null, "Se ha enviado correctamente", "Mensaje enviado", JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al enviar el mensaje, intente de nuevo", "Mensaje fallido", JOptionPane.ERROR_MESSAGE);
			
		
	}
	
	private void iniciarSesion(){
		String usr = "";
		String pswd = "";		
		do {
			usr = JOptionPane.showInputDialog("Ingrese su correo electronico");
			if (usr == null) 
				System.exit(0);
			
			pswd = JOptionPane.showInputDialog("Ingrese su contraseña");
			if (pswd == null)
				System.exit(0);
			
		} while (!correo.iniciarSesion(usr, pswd));
		this.setVisible(true);
	}

}