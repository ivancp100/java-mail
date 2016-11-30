package logica;



public class Mensaje {	

	private String id;
	private String origen;
	private String destino;
	private String asunto;
	private String contenido;
	
	public Mensaje(String origen, String destino, String asunto, String contenido) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.asunto = asunto;
		this.contenido = contenido;
	}

	public String getId() {
		return id;
	}

	public String getOrigen() {
		return origen;
	}

	public String getDestino() {
		return destino;
	}

	public String getAsunto() {
		return asunto;
	}

	public String getContenido() {
		return contenido;
	}


}