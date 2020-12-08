package uniandes.isis2304.ccandes.negocio;

import java.sql.Timestamp;


public class Visita implements VOVisita {
	
	public Visita() {
		this.idvisita = 0;
		this.entrada = new Timestamp(0);
		this.salida = new Timestamp(0);
		this.temperatura = 0;
		this.emailpersona = "";
		this.espacioid = 0;	}

	public Visita(int idvisita, Timestamp entrada, Timestamp salida, int temperatura, String emailpersona,
			int espacioid) {
		this.idvisita = idvisita;
		this.entrada = entrada;
		this.salida = salida;
		this.temperatura = temperatura;
		this.emailpersona = emailpersona;
		this.espacioid = espacioid;
	}

	private int idvisita;
	
	private Timestamp entrada;
	
	private Timestamp salida;
	
	private int temperatura;
	
	private String emailpersona;
	
	private int espacioid;

	public int getIdvisita() {
		return idvisita;
	}

	public Timestamp getEntrada() {
		return entrada;
	}

	public Timestamp getSalida() {
		return salida;
	}

	public int getTemperatura() {
		return temperatura;
	}

	public String getEmailpersona() {
		return emailpersona;
	}

	public int getEspacioid() {
		return espacioid;
	}

	public void setIdvisita(int idvisita) {
		this.idvisita = idvisita;
	}

	public void setEntrada(Timestamp entrada) {
		this.entrada = entrada;
	}

	public void setSalida(Timestamp salida) {
		this.salida = salida;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}

	public void setEmailpersona(String emailpersona) {
		this.emailpersona = emailpersona;
	}

	public void setEspacioid(int espacioid) {
		this.espacioid = espacioid;
	}
	
	
}
