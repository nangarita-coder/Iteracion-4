package uniandes.isis2304.ccandes.negocio;

public class Horario implements VOHorario {
	
	public Horario() {
		this.idhorario = 0;
		this.dia = 0;
		this.horaapertura = 0;
		this.horacierre = 0;

	}

	public Horario(long idhorario, int dia, int horaapertura, int horacierre) {
		this.idhorario = idhorario;
		this.dia = dia;
		this.horaapertura = horaapertura;
		this.horacierre = horacierre;
	}

	private long idhorario;
	
	private int dia;
	
	private int horaapertura;
	
	private int horacierre;

	public long getIdhorario() {
		return idhorario;
	}

	public int getDia() {
		return dia;
	}

	public int getHoraapertura() {
		return horaapertura;
	}

	public int getHoracierre() {
		return horacierre;
	}

	public void setIdhorario(long idhorario) {
		this.idhorario = idhorario;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public void setHoraapertura(int horaapertura) {
		this.horaapertura = horaapertura;
	}

	public void setHoracierre(int horacierre) {
		this.horacierre = horacierre;
	}
	

}
