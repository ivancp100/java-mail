
import gui.VentanaCrearCorreo;
import logica.Gmail;

/**
 * 
 */
public class Lanzador {

	public static void main(String[] args) {
		new VentanaCrearCorreo(new Gmail()).lanzarVentana();
	}

}