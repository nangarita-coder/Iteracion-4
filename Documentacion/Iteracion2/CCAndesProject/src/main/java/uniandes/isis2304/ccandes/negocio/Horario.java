package uniandes.isis2304.ccandes.negocio;

public class Horario implements VOHorario {
	
	public Horario() {
		this.id = 0;
		this.hora_apertura = 0;
		this.hora_cierre = 0;

	}

	public Horario(long id, int hora_apertura, int hora_cierre) {
		this.id = id;
		this.hora_apertura = hora_apertura;
		this.hora_cierre = hora_cierre;
	}

	private long id;
	
	private int hora_apertura;
	
	private int hora_cierre;

	public long getId() {
		return id;
	}


	public int getHoraapertura() {
		return hora_apertura;
	}

	public int getHoracierre() {
		return hora_cierre;
	}

	public void setIdhorario(long id) {
		this.id = id;
	}


	public void setHoraapertura(int hora_apertura) {
		this.hora_apertura = hora_apertura;
	}

	public void setHoracierre(int hora_cierre) {
		this.hora_cierre = hora_cierre;
	}
	

}
